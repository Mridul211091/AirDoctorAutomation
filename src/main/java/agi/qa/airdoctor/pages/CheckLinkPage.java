package agi.qa.airdoctor.pages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import agi.qa.airdoctor.constants.AppConstants;
import agi.qa.airdoctor.utils.ElementUtil;
import agi.qa.airdoctor.utils.ExcelUtil;
import agi.qa.airdoctor.utils.JavaScriptUtil;
import agi.qa.airdoctor.utils.TimeUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

public class CheckLinkPage {

	// Page class/Page Library/Page Object
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil JsUtil;
	private static Set<String> visitedUrls = new HashSet<>();
	private static Set<String> toVisitUrls = new HashSet<>();
	private static Set<String> externalLinks = new HashSet<>();
	private static Set<String> internalLinks = new HashSet<>();
	private static Set<String> brokenLinks = new HashSet<>();
	
	 private static final String SPREADSHEET_ID = "your_spreadsheet_id"; // Replace with your Google Spreadsheet ID
	    private static final String APPLICATION_NAME = "Google Sheets API Java"; // Name of your application
	    private static final String CREDENTIALS_FILE_PATH = "/path/to/your/credentials.json"; // Path to your OAuth2 credentials file
	    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

	// 2. Public Page Class Const...
	public CheckLinkPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		JsUtil = new JavaScriptUtil(driver);
	}

	public String getProductDisplayPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.AD_AFFILIATE_PAGE_TITLE, 5);
		System.out.println("Landing page title : " + title);
		return title;
	}

	public String getPurifierPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.AD_AFFILIATE_PURIFIER_PAGE_TITLE, 5);
		System.out.println("Prifier page title : " + title);
		return title;
	}

	public String getProductPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.PRODUCT_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("product page url : " + url);
		return url;
	}

	public CheckLinkPage getURL(String url) {
		driver.get(url);
		return new CheckLinkPage(driver);
	}

	@Step("Fetch all links starting from {startUrl}")
	public void fetchAllLinks(String startUrl,String validateurl) {
		toVisitUrls.add(startUrl);

		while (!toVisitUrls.isEmpty()) {
			String currentUrl = toVisitUrls.iterator().next();
			toVisitUrls.remove(currentUrl);
			if (!visitedUrls.contains(currentUrl)) {
				visitedUrls.add(currentUrl);
				System.out.println("Visiting: " + currentUrl);
				Allure.step("Visiting: " + currentUrl);
				Set<String> newUrls = getLinksFromPage(currentUrl,validateurl);
				toVisitUrls.addAll(newUrls);
			}

		}
	}

	@Step("Get links from page {url}")
	public Set<String> getLinksFromPage(String allurl,String validateurl) {
		Set<String> urls = new HashSet<>();
		driver.get(allurl);
		try {
			Thread.sleep(3000); // Wait for the page to load
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	     
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

		Document doc = Jsoup.parse(driver.getPageSource());
		Elements allElements = doc.getAllElements();
		
		for (Element element : allElements) {
            // Get all attributes of the current element
            for (org.jsoup.nodes.Attribute attribute : element.attributes()) {
                String attributeValue = attribute.getValue();
                // Check if attribute value resembles a URL (basic check)
                if (isValidUrl(attributeValue)) {
                    String absUrl = element.absUrl(attribute.getKey());
                    if (!absUrl.isEmpty()) {
                        urls.add(absUrl);
                        if (absUrl.contains(validateurl)) {
                            internalLinks.add(absUrl);
                            System.out.println("Invalid link: " + absUrl);
                            Allure.step("Internal link: " + absUrl);
                        } else {
                            externalLinks.add(absUrl);
                            System.out.println("Valid link: " + absUrl);
                            Allure.step("Valid link: " + absUrl);
                            checkIfLinkIsBroken(absUrl);
                        }
                    }
                }
            }
        }

		 Elements metaTags = doc.select("meta[property][content]");
	        for (Element metaTag : metaTags) {
	            String property = metaTag.attr("property");
	            if (property.equals("og:image")) {
	                String content = metaTag.attr("content");
	                if (isValidUrl(content)) {
	                    urls.add(content);
	                    externalLinks.add(content); // Assuming meta tags are external by default
	                    System.out.println("Valid link (meta): " + content);
	                    Allure.step("Valid link (meta): " + content);
	                    checkIfLinkIsBroken(content);
	                }
	            }
	        }
	 // Extract URLs from CSS files linked in the page
    Elements cssLinks = doc.select("link[rel=stylesheet]");
    for (Element cssLink : cssLinks) {
        String cssUrl = cssLink.absUrl("href");
        if (!cssUrl.isEmpty()) {
            System.out.println("CSS file link: " + cssUrl);
            Allure.step("CSS file link: " + cssUrl);
            checkCssFile(cssUrl);
        }
    }

    return urls;
}
	
	   private static boolean isValidUrl(String url) {
	        try {
	            new URL(url).toURI();
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
		
	@Step("Check if link {link} is broken")
    public static void checkIfLinkIsBroken(String link) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 400) {
                brokenLinks.add(link);
                System.out.println("Broken link: " + link + " (Status: " + statusCode + ")");
                Allure.step("Broken link: " + link + " (Status: " + statusCode + ")");
            }
        } catch (IOException e) {
            brokenLinks.add(link);
            System.out.println("Broken link: " + link + " (IOException)");
            Allure.step("Broken link: " + link + " (IOException)");
        }
    }
	
	
	 @Step("Check CSS file at {cssUrl}")
	    public static void checkCssFile(String cssUrl) {
		 try (CloseableHttpClient client = HttpClients.createDefault()) {
	            HttpGet request = new HttpGet(cssUrl);
	            HttpResponse response = client.execute(request);

	            // Check if the response is successful
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode >= 200 && statusCode < 300) {
	                // Parse CSS content to find URLs
	                String cssContent = org.apache.commons.io.IOUtils.toString(response.getEntity().getContent(), "UTF-8");
	                Document cssDoc = Jsoup.parse(cssContent);

	                // Select all URLs in the CSS file
	                Elements urlElements = cssDoc.select("[src], [href]");
	                for (Element urlElement : urlElements) {
	                    String url = urlElement.attr("src");
	                    if (url.isEmpty()) {
	                        url = urlElement.attr("href");
	                    }
	                    if (isValidUrl(url)) {
	                        System.out.println("Found URL in CSS: " + url);
	                        Allure.step("Found URL in CSS: " + url);
	                        checkIfLinkIsBroken(url);
	                    }
	                }
	            } else {
	                brokenLinks.add(cssUrl);
	                System.out.println("Broken CSS file: " + cssUrl + " (Status: " + statusCode + ")");
	                Allure.step("Broken CSS file: " + cssUrl + " (Status: " + statusCode + ")");
	            }
	        } catch (IOException e) {
	            brokenLinks.add(cssUrl);
	            System.out.println("Broken CSS file: " + cssUrl + " (IOException)");
	            Allure.step("Broken CSS file: " + cssUrl + " (IOException)");
	        }
	    }

	@Step("Generate report for found links")
    public  void generateReport() {
        Allure.step("Invalid links found:");
        for (String link : internalLinks) {
            Allure.addAttachment("Internal link", link);
        }

        Allure.step("Valid links found:");
        for (String link : externalLinks) {
            Allure.addAttachment("Valid link", link);
        }

        Allure.step("Broken links found:");
        for (String link : brokenLinks) {
            Allure.addAttachment("Broken link", link);
        }

        // Summary
        Allure.addAttachment("Total invalid links", String.valueOf(internalLinks.size()));
        Allure.addAttachment("Total valid links", String.valueOf(externalLinks.size()));
        Allure.addAttachment("Total broken links", String.valueOf(brokenLinks.size()));
       
	}
	
	/*
	 * public static void writeToGoogleSheet() { // Build a new authorized API
	 * client service. Sheets service = null; try { service = getSheetsService(); }
	 * catch (IOException | GeneralSecurityException e) { e.printStackTrace();
	 * return; } // Create a new sheet createNewSheet(service, "Link Report");
	 * 
	 * // Define range and data to write String range = "Link Report!A1:B1";
	 * List<List<Object>> values = Arrays.asList( Arrays.asList("Link Type",
	 * "Link URL"), convertToRowData(internalLinks, "Internal"),
	 * convertToRowData(externalLinks, "External"), convertToRowData(brokenLinks,
	 * "Broken") );
	 * 
	 * // Create the ValueRange object ValueRange body = new
	 * ValueRange().setValues(values);
	 * 
	 * // Write data to the new sheet try { UpdateValuesResponse result =
	 * service.spreadsheets().values() .update(SPREADSHEET_ID, range, body)
	 * .setValueInputOption("RAW") .execute();
	 * System.out.printf("%d cells updated.", result.getUpdatedCells()); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 */
	
}
