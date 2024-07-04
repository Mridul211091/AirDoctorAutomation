package agi.qa.airdoctor.pages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

import agi.qa.airdoctor.constants.AppConstants;
import agi.qa.airdoctor.utils.ElementUtil;
import agi.qa.airdoctor.utils.ExcelUtil;
import agi.qa.airdoctor.utils.JavaScriptUtil;
import agi.qa.airdoctor.utils.TimeUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

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

	public CheckLinkPage getaffiliateURL(String url) {
		driver.get(url);
		return new CheckLinkPage(driver);
	}

	@Step("Fetch all links starting from {startUrl}")
	public static void fetchAllLinks(String startUrl) {
		toVisitUrls.add(startUrl);

		while (!toVisitUrls.isEmpty()) {
			String currentUrl = toVisitUrls.iterator().next();
			toVisitUrls.remove(currentUrl);
			if (!visitedUrls.contains(currentUrl)) {
				visitedUrls.add(currentUrl);
				System.out.println("Visiting: " + currentUrl);
				Allure.step("Visiting: " + currentUrl);
				Set<String> newUrls = getLinksFromPage(currentUrl);
				toVisitUrls.addAll(newUrls);
			}

		}

	}

	@Step("Get links from page {url}")
	public static Set<String> getLinksFromPage(String allurl) {
		Set<String> urls = new HashSet<>();
		driver.get(allurl);
		try {
			Thread.sleep(3000); // Wait for the page to load
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Document doc = Jsoup.parse(driver.getPageSource());
		Elements links = doc.select("a[href], img[src], video[src], script[src], link[href]");

		for (Element link : links) {
			String href = link.hasAttr("href") ? link.attr("abs:href") : link.attr("abs:src");
			if (!href.isEmpty()) {
				urls.add(href);
				if (href.contains(MAIN_DOMAIN)) {
					internalLinks.add(href);
					System.out.println("Internal link: " + href);
					Allure.step("Internal link: " + href);
				} else {
					externalLinks.add(href);
					System.out.println("External link: " + href);
					Allure.step("External link: " + href);
					checkIfLinkIsBroken(href);
				}
			}
		}

		return urls;
	}

}
