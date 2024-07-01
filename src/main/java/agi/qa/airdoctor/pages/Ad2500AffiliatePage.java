package agi.qa.airdoctor.pages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class Ad2500AffiliatePage {

	// Page class/Page Library/Page Object
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil JsUtil;
	private Map<String, String> productMap = new HashMap<String, String>();

	// 1. Private By Locators

	// AD3500 locators
	private By AD3500increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_135514']//input[@value='+']");
	private By ad3500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='135528']");
	private By regularPrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy AirDoctor 3500')]/following-sibling::div[contains(@class, 'main-price-wrapper')]/p[@class='earth_day_price']/span[contains(text(), 'Regular Price:')]/del/span/bdi");
	private By salePrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy AirDoctor 3500')]/following-sibling::div[contains(@class, 'main-price-wrapper')]/p[@class='earth_day_price']/span/b[contains(text(), 'Sale Price: ')]/span[@class='woocommerce-Price-amount amount']/bdi");
	private By emailonlyPrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy AirDoctor 3500')]/following-sibling::div[@class='main-price-wrapper aff-promotion-price']//p[@class='earth_day_price']/span[@class='orange-price-txt']/span[@class='woocommerce-Price-amount amount']/bdi");
	private By ad3500addToCart = By.xpath("//a[@id='iot_productaddtocart_135514']");

	// Buy 2 AD3500 locators
	private By buy2AD3500increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_286676']//input[@value='+']");
	private By buy2Ad3500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='286678']");
	private By buy2ad3500addToCart = By.xpath("//a[@id='iot_productaddtocart_286676']");
	private By buy2AD3500regularPrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy 2 AD3500')]/following-sibling::div[contains(@class, 'main-price-wrapper')]/p[@class='earth_day_price']/span[contains(text(), 'Regular Price:')]/del/span/bdi");
	private By buy2AD3500salePrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy 2 AD3500')]/following-sibling::div[contains(@class, 'main-price-wrapper')]/p[@class='earth_day_price']/span/b[contains(text(), 'Sale Price: ')]/span[@class='woocommerce-Price-amount amount']/bdi");
	private By buy2AD3500emailonlyPrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy 2 AD3500')]/following-sibling::div[@class='main-price-wrapper aff-promotion-price']//p[@class='earth_day_price']/span[@class='orange-price-txt']/span[@class='woocommerce-Price-amount amount']/bdi");

	// Buy AD3500 And Get An AD5500 For Only $250!
	// *[@id='nu-custom-heading-title' and contains(text(), 'Buy AD3500') and
	// contains(span, 'Get an AD5500 for only
	// $250!')]/following-sibling::div[@class='main-price-wrapper
	// aff-promotion-price']/p[@class='earth_day_price']/span[contains(@class,
	// 'orange-price-txt')]/span[@class='woocommerce-Price-amount amount']/bdi
	private By buyAD3500withAD5500increasequantitybtn = By
			.xpath("//div[@id='iotproduct_varinfo_286686']//input[@value='+']");
	private By buyAD3500withAD5500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='286688']");
	private By buyAD3500withAD5500addToCart = By.xpath("//a[@id='iot_productaddtocart_286686']");

	// Buy AD3500 And Get An AD2000 For Only $249!
	private By buyAD3500withAD2000increasequantitybtn = By
			.xpath("//div[@id='iotproduct_varinfo_286690']//input[@value='+']");
	private By buyAD3500withAD2000UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='286692']");
	private By buyAD3500withAD2000addToCart = By.xpath("//a[@id='iot_productaddtocart_286690']");

	// Buy AirDoctor 5500 And Receive $300 Off
	private By buyAD5500increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_135600']//input[@value='+']");
	private By buyAD5500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='135604']");
	private By buyAD5500addToCart = By.xpath("//a[@id='iot_productaddtocart_135600']");

	// Buy AirDoctor 2000 And Receive $110 Off
	private By buyAD2000increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_135592']//input[@value='+']");
	private By buyAD2000UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='135596']");
	private By buyAD2000addToCart = By.xpath("//a[@id='iot_productaddtocart_135592']");

	//
	private By cartText = By.xpath("//button[@class='btn btn-checkout']//span[@id='prc']");
	private By listOfProductsinCart = By.xpath("//tr[@class='cart_item ']/td/a[@title='Remove this item']");
	private By ordernumber = By.xpath("//li[contains(text(), 'Order number')]/strong");

	private By logoutLink = By.linkText("Logout");
	// private By shopNow =By.xpath("//span[normalize-space()='SHOP NOW']");

	private By shopNow = By
			.xpath("//a[@href='https://airdoctorazstg.wpengine.com/purifiers/']//span[contains(text(),'shop now')]");
	// a[@href='https://airdoctorazstg.wpengine.com/purifiers/']
	private By bannerText = By.xpath("//*[@id=\"wrapper\"]/div[1]/div/div[1]/div/p");
	private By customaffiliatebannerText = By.xpath("//*[@id=\"wide-nav\"]/div/div[1]/ul/li/p");
	// private By bannerText =By.xpath("//ul[contains(@class,'nav header-bottom-nav
	// nav-center mobile-nav')]/li/p");
	// private By Model2500Text =By.xpath("//h5[normalize-space()='\"WALL-MOUNTED OR
	// FLOOR STANDING AIR PURIFIER\"']");
	// private By Model2500Text = By.xpath("//div[@class='product_text-inner-col
	// mob-hide-heading']/h3[span[@class='orange_new' and text()='New!
	// ']][text()='AirDoctor 2500']");
	private By Model2500Text = By.xpath(
			"//div[@class='product_text-inner-col mob-hide-heading' and not(contains(@style, 'display: none'))]/h3[1][contains(., 'AirDoctor 2500')]");
	// section[@class='section product-listing-section
	// product_section_130445']//div[2]//div[1]//h3[1]
	private By Model2500AddToCartBtn = By.xpath(
			"//h3[text()='AirDoctor 2500']/following::a[@class='button secondary btn-pri single_add_to_cart_button_sales'][1]");
	private By Model1000AddToCartBtn = By.xpath(
			"//h3[text()='AirDoctor 1000']/following::a[@class='button secondary btn-pri single_add_to_cart_button_sales'][1]");
	private By Model3500AddToCartBtn = By.xpath(
			"//h3[text()='AirDoctor 3500']/following::a[@class='single_add_to_cart_button pdpf-btn button secondary btn-pri'][1]");
	private By Model5500AddToCartBtn = By.xpath(
			"//h3[text()='AirDoctor 5500']/following::a[@class='single_add_to_cart_button pdpf-btn button secondary btn-pri'][1]");
	private By Model2000AddToCartBtn = By.xpath(
			"//h3[text()='AirDoctor 2000']/following::a[@class='single_add_to_cart_button pdpf-btn button secondary btn-pri'][1]");
	private By Model2500plusQuantityBtn = By
			.xpath("//h3[contains(text(), 'AirDoctor 2500')]/following::input[@class='plus button is-form'][1]");
	private By Model1000plusQuantityBtn = By
			.xpath("//h3[contains(text(), 'AirDoctor 1000')]/following::input[@class='plus button is-form'][1]");
	private By Model3500plusQuantityBtn = By.xpath(
			"//h3[contains(text(), 'AirDoctor 3500')]/following::input[@class='ux-quantity__button ux-quantity__button--plus button plus is-form'][1]");
	private By Model5500plusQuantityBtn = By.xpath(
			"//h3[contains(text(), 'AirDoctor 5500')]/following::input[@class='ux-quantity__button ux-quantity__button--plus button plus is-form'][1]");
	private By Model2000plusQuantityBtn = By.xpath(
			"//h3[contains(text(), 'AirDoctor 2000')]/following::input[@class='ux-quantity__button ux-quantity__button--plus button plus is-form'][1]");
	private By checkoutBtn = By.xpath(
			"//li[@class='html custom html_topbar_right']//a[@href='https://airdoctorazstg.wpengine.com/checkout/']");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By select7in1Model = By.xpath("//a[normalize-space()='7-in-1-saw model']");
	private By onePay = By.xpath("//*[@id=\'wc-option-pay-full\']");
	private By onePay7in1 = By.xpath("//*[@id='product-2155']/div/div[1]/div/div[2]/form/div/div[2]/ul[1]/li[2]/label");
	private By threePay7in1 = By
			.xpath("//*[@id='product-2155']/div/div[1]/div/div[2]/form/div/div[2]/ul[1]/li[1]/label");
	private By select3000Model = By.xpath("(//a[normalize-space()='platinum 3000 model'])[1]");
	private By noThanks = By.xpath("//*[@id=\"no_thanks\"]");
	private By firstName = By.id("billing_first_name");
	private By emailField = By.id("billing_email");
	private By lastName = By.id("billing_last_name");
	private By addressone = By.id("billing_address_1");
	private By addresstwo = By.id("billing_address_2");
	private By city = By.id("billing_city");
	private By zipcode = By.id("billing_postcode");
	private By phone = By.id("billing_phone");
	private By dropdownBtn = By.xpath("//*[@id=\"billing_state_field\"]/span/span/span[1]/span/span[2]");
	private By stateDropdown = By.id("select2-billing_state-container");
	private By statetextfield = By.cssSelector("input[role='combobox']");
	private By stateoption = By.cssSelector("#select2-billing_state-results:first-child li");
	private By termscheckbox = By.xpath("//input[@id='terms']");
	private By placeorderbtn = By.name("woocommerce_checkout_place_order");
	private By placeorderadbtn = By.id("place_order");
	private By popupdonebtn = By.id("wdc_popup");
	private By credicardradiobtn = By.id("payment_method_cybersource_credit_card");
	private By cardnumberfield = By.id("wc-cybersource-credit-card-account-number-hosted");
	private By worldpaycardnumberfield = By.id("WC_Gateway_Worldpay-card-number");
	private By cardexpiryfield = By.id("wc-cybersource-credit-card-expiry");
	private By worldpaycardexpiryfield = By.id("WC_Gateway_Worldpay-card-expiry");
	private By cardseccodefield = By.id("wc-cybersource-credit-card-csc-hosted");
	private By worldpaycardseccodefield = By.id("WC_Gateway_Worldpay-card-cvc");
	private By subtotalvalue = By.xpath("//th[text()='Subtotal:']/following-sibling::td");
	private By taxvalue = By.xpath("//th[text()='Tax:']/following-sibling::td");
	private By flatrate = By.xpath("//th[text()='Shipping:']/following-sibling::td");
	private By finaltotal = By.xpath("//th[text()='Total:']/following-sibling::td");
	private By backtohomepage = By.xpath("//*[@id=\"logo\"]/a");
	private By logout = By.xpath("//*[@id='main']/div[2]/div/div/div[2]/div/div/p[1]/a");
	// *[@id="main"]/div[2]/div/div/div[2]/div/div/p[1]/a
	private By loginIcon = By.xpath("//i[@class='icon-user']");
	private By usenewcard = By.id("wc-cybersource-credit-card-use-new-payment-method");
	private By paymentmethodtab = By.xpath("//*[@id=\"my-account-nav\"]/li[5]/a");
	private By logouttab = By.xpath("//*[@id='my-account-nav']/li[8]/a");
	private By deletebtn = By.xpath("//*[@id=\"main\"]/div[2]/div/div/div[2]/div/div/table/tbody/tr/td[6]/a[3]");

	// 2. Public Page Class Const...
	public Ad2500AffiliatePage(WebDriver driver) {
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

	public Ad2500AffiliatePage getaffiliateURL(String url) {
		driver.get(url);
		return new Ad2500AffiliatePage(driver);
	}
	@Step("This is to validate the banner on landing page")
	public String getBannerText(String url) {
		JsUtil.setzoomlevelByJS(driver);
		Allure.addAttachment(url, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = eleUtil.doGetElementText(bannerText);
		System.out.println("Landing page banner text : " + title);
		return title;
	}
	
	@Step("This is to validate the banner on landing page")
	public String getCustomAffilateBannerText(String url){
		JsUtil.setzoomlevelByJS(driver);
		Allure.addAttachment(url, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		eleUtil.waitForElementToBeClickable(customaffiliatebannerText, 5);
		String title = eleUtil.doGetElementText(customaffiliatebannerText);
		System.out.println("Landing page banner text : " + title);
		 return title;
	}
	
	

	public void clickShopNow() {
		eleUtil.doActionsClick(shopNow);
	}

	public String getModelText() {
		String title = eleUtil.doGetElementText(Model2500Text);
		System.out.println("Model Text is : " + title);
		return title;
	}

	public String getCartText() {
		String checkoutText = eleUtil.doGetElementText(cartText);
		System.out.println("Cart Button Text : " + checkoutText);
		return checkoutText;
	}

	public LoginPage removecartproducts() throws InterruptedException {
		List<WebElement> productremovebuttonList = eleUtil.getElements(listOfProductsinCart);
		try {
			
		int i = productremovebuttonList.size();
		for(i=0 ;i< productremovebuttonList.size();i++) {
			eleUtil.clickWhenReady(listOfProductsinCart, TimeUtil.DEFAULT_MEDIUM_TIME);
			driver.navigate().refresh();
			Thread.sleep(10000);
			//productremovebuttonList = eleUtil.getElements(listOfProductsinCart);
		}
			
		/*
		 * for (WebElement e : productremovebuttonList) { String element = e.getText();
		 * String attribute =e.getTagName(); if(productremovebuttonList.size()>=1)
		 * e.click(); Thread.sleep(5000); driver.navigate().refresh();
		 * Thread.sleep(5000); productremovebuttonList =
		 * eleUtil.getElements(listOfProductsinCart); }
		 */
		
		}
		
		    catch (Exception ex) {
			System.out.println("Can not click on element"+ ex);
			throw ex;
			
		}
		eleUtil.clickWhenReady(backtohomepage, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new LoginPage(driver);
	}
	
	public void clearCart() throws Exception {
		Thread.sleep(10000);
		getCartText();
		
		try {
			if (!getCartText().equalsIgnoreCase("")) {
				eleUtil.clickWhenReady(checkoutBtn, TimeUtil.DEFAULT_MEDIUM_TIME);
				Thread.sleep(10000);
				removecartproducts();
			}
			else {
			System.out.println("Cart is empty");	
			}
		}
		catch (Exception ex) {
			System.out.println("Clearing Cart failed");
			throw ex;
			
		}
	}
		
	
	
	

	public Map<String, String> getorderdetails() throws InvalidFormatException, IOException {
		productMap.put("subtotal", eleUtil.getElement(subtotalvalue).getText());
		productMap.put("tax", eleUtil.getElement(taxvalue).getText());
		productMap.put("Shipping", eleUtil.getElement(flatrate).getText());
		productMap.put("total", eleUtil.getElement(finaltotal).getText());
		productMap.put("OrderID", eleUtil.getElement(ordernumber).getText());
		System.out.println("product Details: \n" + productMap);
		return productMap;
	}

	public void selectupsells(String Upsell_1, String Upsell_1_Option_Quantity) throws InterruptedException {

		if (Upsell_1.equals("YES")) {
			if (Upsell_1_Option_Quantity.equals("2")) {
				WebElement staticDropdown = driver.findElement(By.id("wps_upsell_quantity_field"));
				Select dropdown = new Select(staticDropdown);
				dropdown.selectByVisibleText("2");
				System.out.println("Upsell_1_Option_Quantity");
				Thread.sleep(1000);
			} else if (Upsell_1_Option_Quantity.equals("3")) {
				WebElement staticDropdown = driver.findElement(By.id("wps_upsell_quantity_field"));
				Select dropdown = new Select(staticDropdown);
				dropdown.selectByVisibleText("3");
				System.out.println("Upsell_1_Option_Quantity");
				Thread.sleep(1000);
			}

			driver.findElement(By.xpath("//a[contains(text(),'ADD TO CART')]")).click();

		} else if (Upsell_1.equals("NO")) {
			driver.findElement(By.xpath("(//a[normalize-space()='No, thank you'])[1]")).click();
		} else {
			System.out.println("Incorrect Upsell-1 Options");
			// Fail - incorrect Upsell-1 option
		}

	}

	public void getThankYoPageURL() throws URISyntaxException {
		String originalUrl = driver.getCurrentUrl();
		String username = "airdoctorazstg";
		String password = "36f32412";

		try {
			URI uri = new URI(originalUrl);
			String newUrl = uri.getScheme() + "://" + username + ":" + password + "@" + uri.getHost();
			if (uri.getPort() != -1) {
				newUrl += ":" + uri.getPort();
			}
			newUrl += uri.getPath();
			if (uri.getQuery() != null) {
				newUrl += "?" + uri.getQuery();
			}
			System.out.println("New URL: " + newUrl);
			driver.get(newUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// return url;
	}

	public void checkout(String email, String firstname, String lastname, String addone, String addtwo, String cty,
			String state, String zip, String phonenumber) throws InterruptedException, Exception {
		eleUtil.doSendKeys(emailField, email);
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(lastName, lastname);
		eleUtil.doSendKeys(addressone, addone);
		eleUtil.doSendKeys(addresstwo, addtwo);
		eleUtil.doSendKeys(city, cty);
		JsUtil.scrollIntoView(eleUtil.getElement(dropdownBtn));
		eleUtil.clickWhenReady(dropdownBtn, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(statetextfield, state);
		eleUtil.clickWhenReady(stateoption, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.SendKeys(zipcode, zip);
		eleUtil.doSendKeys(phone, phonenumber);
		/*
		 * JsUtil.scrollIntoView(eleUtil.getElement(cardnumberfield));
		 * Thread.sleep(3000); eleUtil.doActionsSendKeys(cardnumberfield,
		 * AppConstants.CARD_NUMBER); eleUtil.doActionsSendKeys(cardexpiryfield,
		 * AppConstants.CARD_EXPIRY);
		 * JsUtil.scrollIntoView(eleUtil.getElement(placeorderbtn)); Thread.sleep(5000);
		 * eleUtil.doActionsSendKeys(cardseccodefield, AppConstants.SECURITY_CODE);
		 * JsUtil.scrollIntoView(eleUtil.getElement(placeorderbtn));
		 */
		Thread.sleep(5000);
		eleUtil.doActionsClick(termscheckbox);
		// eleUtil.doClickcheckbox(termscheckbox,TimeUtil.DEFAULT_MEDIUM_TIME);
		Thread.sleep(3000);
		eleUtil.doActionsClick(placeorderadbtn);
		eleUtil.clickWhenReady(popupdonebtn, TimeUtil.DEFAULT_MEDIUM_TIME);
	}

	public void selectModel(String ModelName, String ProductQuantity) throws InterruptedException, Exception {
		try {
			if (ModelName.equalsIgnoreCase("AirDoctor 3500")||ModelName.equalsIgnoreCase("AirDoctor 3500") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(AD3500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(ad3500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}
			if (ModelName.equalsIgnoreCase("AirDoctor 3500") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);;
				eleUtil.scrollTiView(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(ad3500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AirDoctor 3500") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(AD3500increasequantitybtn);
				eleUtil.doActionsClick(AD3500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(ad3500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AirDoctor 3500 iot") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doClickcheckbox(ad3500UpgradeToIotCheckbox, TimeUtil.DEFAULT_LONG_TIME);
				// eleUtil.doActionsClick(ad3500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.doActionsClick(ad3500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}

			if (ModelName.equalsIgnoreCase("AirDoctor 3500 iot") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doClickcheckbox(ad3500UpgradeToIotCheckbox, TimeUtil.DEFAULT_LONG_TIME);
				Thread.sleep(3000);
				eleUtil.doActionsClick(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(ad3500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AirDoctor 3500 iot") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doClickcheckbox(ad3500UpgradeToIotCheckbox, TimeUtil.DEFAULT_LONG_TIME);
				Thread.sleep(3000);
				eleUtil.doActionsClick(AD3500increasequantitybtn);
				eleUtil.doActionsClick(AD3500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(ad3500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy 2 AD3500") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}
			if (ModelName.equalsIgnoreCase("Buy 2 AD3500") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				// JsUtil.scrollIntoView(eleUtil.getElement(buy2ad3500addToCart));
				Thread.sleep(3000);
				eleUtil.doActionsClick(buy2AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy 2 AD3500") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buy2AD3500increasequantitybtn);
				eleUtil.doActionsClick(buy2AD3500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy 2 AD3500 iot") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				Thread.sleep(10000);
				// eleUtil.doClickcheckbox(buy2Ad3500UpgradeToIotCheckbox,
				// TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(buy2Ad3500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				Thread.sleep(2000);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				Thread.sleep(6000);
				eleUtil.doActionsClick(checkoutBtn);
			}

			if (ModelName.equalsIgnoreCase("Buy 2 AD3500 iot") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buy2Ad3500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				eleUtil.doActionsClick(buy2AD3500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy 2 AD3500 iot") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				Thread.sleep(6000);
				eleUtil.scrollTiView(buy2AD3500increasequantitybtn);
				eleUtil.doActionsClick(buy2Ad3500UpgradeToIotCheckbox);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buy2AD3500increasequantitybtn);
				eleUtil.doActionsClick(buy2AD3500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				eleUtil.doActionsClick(buy2ad3500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD5500") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD5500") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD5500") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD3500withAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD5500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD5500 iot") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(10000);
				eleUtil.doActionsClick(buyAD3500withAD5500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				Thread.sleep(6000);
				eleUtil.doActionsClick(checkoutBtn);
			}

			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD5500 iot") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD5500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD5500 iot") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				Thread.sleep(6000);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD5500UpgradeToIotCheckbox);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD3500withAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD5500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				eleUtil.doActionsClick(buyAD3500withAD5500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD2000") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD2000") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD2000") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD3500withAD2000increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD2000increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD2000 iot") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(10000);
				eleUtil.doActionsClick(buyAD3500withAD2000UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				Thread.sleep(6000);
				eleUtil.doActionsClick(checkoutBtn);
			}

			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD2000 iot") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD2000UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("Buy AD3500 And AD2000 iot") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				Thread.sleep(6000);
				eleUtil.scrollTiView(buyAD3500withAD2000increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD3500withAD2000UpgradeToIotCheckbox);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD3500withAD2000increasequantitybtn);
				eleUtil.doActionsClick(buyAD3500withAD2000increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				eleUtil.doActionsClick(buyAD3500withAD2000addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD5500") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD5500addToCart);
				eleUtil.doActionsClick(buyAD5500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}
			if (ModelName.equalsIgnoreCase("AD5500") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD5500addToCart);
				eleUtil.doActionsClick(buyAD5500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD5500") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD5500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD5500addToCart);
				eleUtil.doActionsClick(buyAD5500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD5500 iot") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				Thread.sleep(10000);
				eleUtil.doActionsClick(buyAD5500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD5500addToCart);
				eleUtil.doActionsClick(buyAD5500addToCart);
				Thread.sleep(6000);
				eleUtil.doActionsClick(checkoutBtn);
			}

			if (ModelName.equalsIgnoreCase("AD5500 iot") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD5500UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD5500increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD5500addToCart);
				eleUtil.doActionsClick(buyAD5500addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD5500 iot") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				Thread.sleep(6000);
				eleUtil.scrollTiView(buyAD5500increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD5500UpgradeToIotCheckbox);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD5500increasequantitybtn);
				eleUtil.doActionsClick(buyAD5500increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD5500addToCart);
				eleUtil.doActionsClick(buyAD5500addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD2000") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD2000addToCart);
				eleUtil.doActionsClick(buyAD2000addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(checkoutBtn);
			}
			if (ModelName.equalsIgnoreCase("AD2000") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD2000addToCart);
				eleUtil.doActionsClick(buyAD2000addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD2000") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD2000increasequantitybtn);
				eleUtil.doActionsClick(buyAD2000increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD2000addToCart);
				eleUtil.doActionsClick(buyAD2000addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD2000 iot") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				Thread.sleep(10000);
				eleUtil.doActionsClick(buyAD2000UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD2000addToCart);
				eleUtil.doActionsClick(buyAD2000addToCart);
				Thread.sleep(6000);
				eleUtil.doActionsClick(checkoutBtn);
			}

			if (ModelName.equalsIgnoreCase("AD2000 iot") && ProductQuantity.equals("2")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD2000UpgradeToIotCheckbox);
				Thread.sleep(5000);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				eleUtil.doActionsClick(buyAD2000increasequantitybtn);
				Thread.sleep(5000);
				eleUtil.doActionsClick(buyAD2000addToCart);
				eleUtil.doActionsClick(buyAD2000addToCart);
				Thread.sleep(4000);
				eleUtil.doActionsClick(checkoutBtn);
				Thread.sleep(4000);
			}
			if (ModelName.equalsIgnoreCase("AD2000 iot") && ProductQuantity.equals("3")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				Thread.sleep(6000);
				eleUtil.scrollTiView(buyAD2000increasequantitybtn);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD2000UpgradeToIotCheckbox);
				Thread.sleep(3000);
				eleUtil.doActionsClick(buyAD2000increasequantitybtn);
				eleUtil.doActionsClick(buyAD2000increasequantitybtn);
				System.out.println("Selected Quantity");
				Thread.sleep(2000);
				eleUtil.doActionsClick(buyAD2000addToCart);
				eleUtil.doActionsClick(buyAD2000addToCart);
				System.out.println("Clicked on Add to Cart");
				Thread.sleep(2000);
				eleUtil.waitForElementPresence(checkoutBtn, TimeUtil.DEFAULT_LONG_TIME);
				eleUtil.doActionsClick(checkoutBtn);
				System.out.println("Clicked on CheckOut");
				Thread.sleep(4000);
			}

			else {
				System.out.println("Incorrect Model");
				// Fail - Incorrect payment option
			}
		}

		catch (Exception ex) {
			System.out.println("Failed Selecting Model");
			throw ex;

		}

	}

	@Step("Entering Values in Excel sheet for Subtotal,Shipping,Tax and Overall Total")
	public void writeexcel(String subtotal, String flatrate, String tax, String total,String presubtotal, String preflatrate, String pretax, String pretotal,String orderId, int count)
			throws InvalidFormatException, IOException {
		ExcelUtil.setdata(AppConstants.STAGE_SHEET_NAME, presubtotal, preflatrate, pretax, pretotal,subtotal, flatrate, tax, total,orderId, count);
	}

	public int testMe(ITestContext testContext) {
		int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
		System.out.println("Executing count: " + currentCount);
		return currentCount;
	}

	public LoginPage logoutfromthankyoupage() throws InterruptedException {
		eleUtil.clickWhenReady(backtohomepage, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.clickWhenReady(loginIcon, TimeUtil.DEFAULT_MEDIUM_TIME);
		// JsUtil.scrollIntoView(eleUtil.getElement(logouttab));
		eleUtil.clickWhenReady(logout, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.clickWhenReady(backtohomepage, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new LoginPage(driver);
	}

}
