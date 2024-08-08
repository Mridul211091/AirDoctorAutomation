package agi.qa.airdoctor.pages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import agi.qa.airdoctor.utils.ExcelUtillive;
import agi.qa.airdoctor.utils.JavaScriptUtil;
import agi.qa.airdoctor.utils.TimeUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class ADSiteMergeOrderFlowPage {

	// Page class/Page Library/Page Object
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil JsUtil;
	private Map<String, String> postorderdetailsMap = new HashMap<String, String>();
	private Map<String, String> preorderdetailsMap = new HashMap<String, String>();

	// 1. Private By Locators
	private By UpdradetoIotbtn = By.xpath("//a[text()='UPGRADE ME FOR $50']");
	private By buyUpsell = By.xpath("//a[normalize-space()='Continue']");
	private By cancelUpsell = By.xpath("//a[normalize-space()='No Thanks']");
	
	private By cookiebannerbtn= By.cssSelector("div > div > div.dg-main-actions > button.dg-button.accept_all");
	private By cookie= By.cssSelector(".sc-dcJsrY.hNTnAz");
	private By shadowroot= By.xpath("//aside[@class='dg-consent-banner theme-neutral position-bottom visible']");

	//Buy AD5500, AD3500, and AD2000 and receive $900 off!
	private By ad5500_3500_2000_productnametext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//h3[@id='nu-custom-heading-title'] | /h3/span[@class='bfcm-offer-text']");
	private By ad5500_3500_2000_productregularpricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l1']/span[@class='regular_sos_price']");
	private By ad5500_3500_2000_productsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l1']/span[@class='sale_sos_price']");
	private By ad5500_3500_2000_productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By ad5500_3500_2000_smartproductregularpricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l1']/span[@class='regular_sos_price']");
	private By ad5500_3500_2000_smartproductsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l1']/span[@class='sale_sos_price']");
	private By ad5500_3500_2000_smartproductsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301717']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l2']");
	
	private By ad5500_3500_2000_addToCart = By.xpath("//a[@id='iot_productaddtocart_301717']");
	private By ad5500_3500_2000_UpgradeToIotCheckbox = By.xpath("//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='301719']");

	//Buy AD5500 and AD2000 and receive $550 off!
	private By ad5500_2000_productnametext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//h3[@id='nu-custom-heading-title'] | /h3/span[@class='bfcm-offer-text']");
	private By ad5500_2000_productregularpricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l1']/span[@class='regular_sos_price']");
	private By ad5500_2000_productsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l1']/span[@class='sale_sos_price']");
	private By ad5500_2000_productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By ad5500_2000_smartproductregularpricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l1']/span[@class='regular_sos_price']");
	private By ad5500_2000_smartproductsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l1']/span[@class='sale_sos_price']");
	private By ad5500_2000_smartproductsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252267']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l2']");
	
	private By ad5500_2000_addToCart = By.xpath("//a[@id='iot_productaddtocart_252267']");
	private By ad5500_2000_UpgradeToIotCheckbox = By.xpath("//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='252269']");
	
	
	//Buy AD3500 and AD2000 and receive $450 off!
	private By ad3500_2000_productnametext = By.xpath("//section[contains(@class,'product_section_286690')]//div[contains(@class,'product_text-inner-col mobile-show-heading')]//h3[@id='nu-custom-heading-title']");
	private By ad3500_2000_productregularpricetext = By.xpath("//section[contains(@class,'product_section_286690')]//p[1]//span[1]//del[1]//span[1]//bdi[1]");
	private By ad3500_2000_productsalepricetext = By.xpath("//section[@class='section product-listing-section memorial_day_sale  product_section_286690']//div[1]//div[1]//div[1]//div[2]//div[1]//div[4]//p[1]//span[1]//ins[1]//span[1]//bdi[1]");
	private By ad3500_2000_productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252279']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By ad3500_2000_smartproductregularpricetext = By.xpath("//section[contains(@class,'product_section_286690')]//p[contains(@class,'earth_day_price_iot')]//del[contains(@aria-hidden,'true')]//bdi[1]");
	private By ad3500_2000_smartproductsalepricetext = By.xpath("//section[contains(@class,'product_section_286690')]//p[contains(@class,'earth_day_price_iot')]//ins[contains(@aria-hidden,'true')]");
	private By ad3500_2000_smartproductsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_252279']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l2']");
	
	private By ad3500_2000_addToCart = By.xpath("//a[@id='iot_productaddtocart_286690']");
	private By ad3500_2000_UpgradeToIotCheckbox = By.xpath("//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='286692']");
	private By ad3500_2000_increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_286690']//input[@value='+']");

	
	
	//Buy AD3500 and AD2500 and receive $485 off!
	private By ad3500_2500_productnametext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301720']//h3[@id='nu-custom-heading-title'] | /h3/span[@class='bfcm-offer-text']");
	private By ad3500_2500_productregularpricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301720']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l1']/span[@class='regular_sos_price']");
	private By ad3500_2500_productsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301720']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l1']/span[@class='sale_sos_price']");
	private By ad3500_2500_productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_301720']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By ad3500_2500_addToCart = By.xpath("//a[@id='iot_productaddtocart_301720']");
	
//
	 
    private By sm3500addToCart = By.xpath("//a[@class='button secondary regular_add_to_cart']");
    private By sm3500smartaddToCart = By.xpath("//a[@class='button secondary iot_add_to_cart']");
	private By sm3500UpgradeToIotCheckbox = By.xpath("//input[@name='upgrade_to_iot_chkbox_tv_lp']");
	private By sm3500increasequantitybtn = By.xpath("//input[@value='+']");

	
	
	
	// AD3500 locators
	private By AD3500increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_135514']//input[@value='+']");
	private By ad3500addToCart = By.xpath("//a[@id='iot_productaddtocart_135514']");
	private By ad3500UpgradeToIotCheckbox = By.xpath(
			"//section[@class='section product-listing-section memorial_day_sale non_memorialday_bundle product_section_135514']//input[@name='upgrade_to_iot_chkbox']");

	private By ad3500productnametext = By.xpath("//section[contains(@class,'product_section_135514')]//div[contains(@class,'product_text-inner-col mobile-show-heading')]//h3[@id='nu-custom-heading-title']");
	private By ad3500productregularpricetext = By.xpath("//section[contains(@class,'product_section_135514')]//p[1]//span[1]//del[1]//span[1]//bdi[1]");
	private By ad3500productsalepricetext = By.xpath("//section[@class='section product-listing-section memorial_day_sale non_memorialday_bundle product_section_135514']//div[1]//div[1]//div[1]//div[2]//div[1]//div[4]//p[1]//span[1]//ins[1]//span[1]//bdi[1]");
	private By ad3500productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_135514']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By ad3500smartproductregularpricetext = By.xpath("//section[contains(@class,'product_section_135514')]//p[contains(@class,'earth_day_price_iot')]//del[contains(@aria-hidden,'true')]//bdi[1]");
	private By ad3500smartproductsalepricetext = By.xpath("//section[contains(@class,'product_section_135514')]//p[contains(@class,'earth_day_price_iot')]//ins[contains(@aria-hidden,'true')]");
	private By ad3500smartproductsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_135514']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l2']");
	
	
	
	
	
	//SALE-ON-SALE Price:                                                 $329 
	

	// Buy AirDoctor 3500 And A One Year Filter Combo Pack!
	private By buy2AD3500increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_286932']//input[@value='+']");
	private By buy2Ad3500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='286934']");
	private By buy2ad3500addToCart = By.xpath("//a[@id='iot_productaddtocart_286932']");
	private By buy2AD3500regularPrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy 2 AD3500')]/following-sibling::div[contains(@class, 'main-price-wrapper')]/p[@class='earth_day_price']/span[contains(text(), 'Regular Price:')]/del/span/bdi");
	private By buy2AD3500salePrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy 2 AD3500')]/following-sibling::div[contains(@class, 'main-price-wrapper')]/p[@class='earth_day_price']/span/b[contains(text(), 'Sale Price: ')]/span[@class='woocommerce-Price-amount amount']/bdi");
	private By buy2AD3500emailonlyPrice = By.xpath(
			"//*[@id='nu-custom-heading-title' and contains(text(), 'Buy 2 AD3500')]/following-sibling::div[@class='main-price-wrapper aff-promotion-price']//p[@class='earth_day_price']/span[@class='orange-price-txt']/span[@class='woocommerce-Price-amount amount']/bdi");

	// Buy AirDoctor 5500 And A One Year Filter Combo Pack!
	private By buyAD3500withAD5500increasequantitybtn = By
			.xpath("//div[@id='iotproduct_varinfo_286997']//input[@value='+']");
	private By buyAD3500withAD5500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='286999']");
	private By buyAD3500withAD5500addToCart = By.xpath("//a[@id='iot_productaddtocart_286997']");

	// Buy AirDoctor 2000 And A One Year Filter Combo Pack!
	private By buyAD3500withAD2000increasequantitybtn = By
			.xpath("//div[@id='iotproduct_varinfo_287020']//input[@value='+']");
	private By buyAD3500withAD2000UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='287022']");
	private By buyAD3500withAD2000addToCart = By.xpath("//a[@id='iot_productaddtocart_287020']");

	// Buy AirDoctor 1000 And A One Year Filter Combo Pack!
	private By buyAD1000withFilterincreasequantitybtn = By
			.xpath("//div[@class='quantity buttons_added']/input[@id='p_quantity287023']/following-sibling::input[@value='+']");
	private By buyAD1000withFilteraddToCart = By.xpath("//section[contains(@class,'product_section_287023')]//a[contains(@class,'button secondary btn-pri single_add_to_cart_button_sales')]");
	
	
	// Buy AirDoctor 1000!
	
	private By buyAD1000productnametext = By.xpath("//section[contains(@class,'product_section_451')]//div[contains(@class,'product_text-inner-col mobile-show-heading')]//h3[@id='nu-custom-heading-title']");
	private By buyAD1000productregularpricetext = By.xpath("//section[contains(@class,'product_section_451')]//p[1]//span[1]//del[1]//span[1]//bdi[1]");
	private By buyAD1000productsalepricetext = By.xpath("//section[@class='section product-listing-section memorial_day_sale non_memorialday_bundle product_section_451']//ins[@aria-hidden='true']//bdi[1]");
		
	private By buyAD1000increasequantitybtn = By
				.xpath("//div[@class='quantity buttons_added']/input[@id='p_quantity451']/following-sibling::input[@value='+']");
	private By buyAD1000addToCart = By.xpath("//section[contains(@class,'product_section_451')]//a[contains(@class,'button secondary btn-pri single_add_to_cart_button_sales')]");
	
	// Buy AirDoctor 5500 And Receive $300 Off
	
	
	
	private By ad5500productnametext = By.xpath("//section[contains(@class,'product_section_135600')]//div[contains(@class,'product_text-inner-col mobile-show-heading')]//h3[@id='nu-custom-heading-title']");
	private By ad5500productregularpricetext = By.xpath("//section[contains(@class,'product_section_135600')]//p[1]//span[1]//del[1]//span[1]//bdi[1]");
	private By ad5500productsalepricetext = By.xpath("//section[@class='section product-listing-section memorial_day_sale non_memorialday_bundle product_section_135600']//div[1]//div[1]//div[1]//div[2]//div[1]//div[4]//p[1]//span[1]//ins[1]//span[1]//bdi[1]");
	private By ad5500productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_135600']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By ad5500smartproductregularpricetext = By.xpath("//section[contains(@class,'product_section_135600')]//p[contains(@class,'earth_day_price_iot')]//del[contains(@aria-hidden,'true')]//bdi[1]");
	private By ad5500smartproductsalepricetext = By.xpath("//section[contains(@class,'product_section_135600')]//p[contains(@class,'earth_day_price_iot')]//ins[contains(@aria-hidden,'true')]");
	private By ad5500smartproductsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_135600']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l2']");
	
	
	
	private By buyAD5500increasequantitybtn = By.xpath("//div[@id='iotproduct_varinfo_135600']//input[@value='+']");
	private By buyAD5500UpgradeToIotCheckbox = By.xpath(
			"//input[@name='upgrade_to_iot_chkbox' and preceding-sibling::input[@name='iot_variation_id']/@value='135604']");
	private By buyAD5500addToCart = By.xpath("//a[@id='iot_productaddtocart_135600']");

	// Buy AirDoctor 2000 And Receive $110 Off
	
	

	private By buyAD2000productnametext = By.xpath("//section[contains(@class,'product_section_135592')]//div[contains(@class,'product_text-inner-col mobile-show-heading')]//h3[@id='nu-custom-heading-title']");
	private By buyAD2000productregularpricetext = By.xpath("//section[contains(@class,'product_section_135592')]//p[1]//span[1]//del[1]//span[1]//bdi[1]");
	private By buyAD2000productsalepricetext = By.xpath("//section[@class='section product-listing-section memorial_day_sale non_memorialday_bundle product_section_135592']//div[1]//div[1]//div[1]//div[2]//div[1]//div[4]//p[1]//span[1]//ins[1]//span[1]//bdi[1]");
	private By buyAD2000productsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_135600']//div[@class='main-price-wrapper regular_price']/p[@class='summer_sos_price_l2']");
	
	private By buyAD2000smartproductregularpricetext = By.xpath("//section[contains(@class,'product_section_135592')]//p[contains(@class,'earth_day_price_iot')]//del[contains(@aria-hidden,'true')]//bdi[1]");
	private By buyAD2000smartproductsalepricetext = By.xpath("//section[contains(@class,'product_section_135592')]//p[contains(@class,'earth_day_price_iot')]//ins[contains(@aria-hidden,'true')]");
	private By buyAD2000smartproductsaleonsalepricetext = By.xpath("//section[@class='section product-listing-section spring_sos_sale product_section_135592']//div[@class='main-price-wrapper price_iot']/p[@class='summer_sos_price_l2']");
		
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
			.xpath("//span[normalize-space()='shop now']");
	// a[@href='https://airdoctorazstg.wpengine.com/purifiers/']
	private By bannerText = By.xpath("//strong[contains(text(),'NEW! AirDoctor 2500 Wall-Mounted Purifier Sale-on-')]");
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
	private By presubtotalvalue = By.xpath("//th[text()='Subtotal']/following-sibling::td/span/bdi");
	private By pretaxvalue = By.xpath("//th[text()='Tax']/following-sibling::td/span/bdi");
	private By preflatrate = By.xpath("//th[text()='Shipping and Processing']/following-sibling::td/ul/li/input/following-sibling::label");
	private By prefinaltotal = By.xpath("//th[text()='Total']/following-sibling::td/strong/span/bdi");
	
	
	
	
	
	private By backtohomepage = By.xpath("//*[@id=\"logo\"]/a");
	private By logout = By.xpath("//*[@id='main']/div[2]/div/div/div[2]/div/div/p[1]/a");
	// *[@id="main"]/div[2]/div/div/div[2]/div/div/p[1]/a
	private By loginIcon = By.xpath("//i[@class='icon-user']");
	private By usenewcard = By.id("wc-cybersource-credit-card-use-new-payment-method");
	private By paymentmethodtab = By.xpath("//*[@id=\"my-account-nav\"]/li[5]/a");
	private By logouttab = By.xpath("//*[@id='my-account-nav']/li[8]/a");
	private By deletebtn = By.xpath("//*[@id=\"main\"]/div[2]/div/div/div[2]/div/div/table/tbody/tr/td[6]/a[3]");

	// 2. Public Page Class Const...
	public ADSiteMergeOrderFlowPage(WebDriver driver) {
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

	public ADSiteMergeOrderFlowPage getaffiliateURL(String url) throws InterruptedException {
		driver.get(url);
		driver.findElement(shadowroot).getShadowRoot().findElement(cookiebannerbtn).click();
		Thread.sleep(2000);
		Allure.addAttachment(url, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		return new ADSiteMergeOrderFlowPage(driver);
	}
	
public void acceptcookie() throws InterruptedException {
		
	driver.findElement(shadowroot).getShadowRoot().findElement(cookiebannerbtn).click();
	Thread.sleep(2000);
	}
	

	public String getBannerText() {
		
		String title = eleUtil.doGetElementText(bannerText);
		System.out.println("Landing page banner text : " + title);
		return title;
	}
	
	public String get3500ProductnameText() {
		String title = eleUtil.doGetElementText(ad3500productnametext);
		System.out.println("AD 3500 Model Name : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 Summer Sale Price")
	public String get3500ProductpriceText() throws InterruptedException {
		Thread.sleep(3000);
		eleUtil.scrollTiView(ad3500addToCart);
		Allure.addAttachment("3500 Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad3500productregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad3500productsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad3500productsaleonsalepricetext);
		System.out.println("AD 3500 Prices : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 Smart Summer Sale Price")
	public String get3500smartProductpriceText() throws InterruptedException {
		
		eleUtil.scrollTiView(ad3500addToCart);
		Thread.sleep(5000);
		eleUtil.doActionsClick(ad3500UpgradeToIotCheckbox);
		Allure.addAttachment("3500 Smart Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad3500smartproductregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad3500smartproductsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad3500smartproductsaleonsalepricetext);
		System.out.println("AD 3500 Prices : " + title);
		return title;
	}
	
	public String getad5500_3500_2000ProductnameText() {
		String title = eleUtil.doGetElementText(ad5500_3500_2000_productnametext);
		System.out.println("AD 3500 Model Name : " + title);
		return title;
	}
	
	@Step("AirDoctor 5500,3500 and 2000 Summer Sale Price")
	public String getad5500_3500_2000ProductpriceText() throws InterruptedException {
		Thread.sleep(3000);
		eleUtil.scrollTiView(ad5500_3500_2000_addToCart);
		Allure.addAttachment("5500,3500 and 2000 Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad5500_3500_2000_productregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad5500_3500_2000_productsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad5500_3500_2000_productsaleonsalepricetext);
		System.out.println("AD 5500,3500 and 2000 Prices : " + title);
		return title;
	}
	
	@Step("AirDoctor 5500,3500 and 2000 Smart Summer Sale Price")
	public String getad5500_3500_2000smartProductpriceText() throws InterruptedException {
		
		eleUtil.scrollTiView(ad5500_3500_2000_addToCart);
		Thread.sleep(5000);
		eleUtil.doActionsClick(ad5500_3500_2000_UpgradeToIotCheckbox);
		Allure.addAttachment("5500,3500 and 2000 Smart Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad5500_3500_2000_smartproductregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad5500_3500_2000_smartproductsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad5500_3500_2000_smartproductsaleonsalepricetext);
		System.out.println("AD 5500,3500 and 2000 Prices : " + title);
		return title;
	}	
	
	@Step("AirDoctor 5500 and 2000 Summer Sale Product Name")
	public String getad5500_2000_ProductnameText() {
		String title = eleUtil.doGetElementText(ad5500_2000_productnametext);
		System.out.println("AD 5500 and 2000 Model Name : " + title);
		return title;
	}
	
	@Step("AirDoctor 5500 and 2000 Summer Sale Price")
	public String getad5500_2000_ProductpriceText() throws InterruptedException {
		Thread.sleep(3000);
		eleUtil.scrollTiView(ad5500_2000_addToCart);
		Allure.addAttachment("AD 5500 and 2000 Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad5500_2000_productregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad5500_2000_productsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad5500_2000_productsaleonsalepricetext);
		System.out.println("AD 5500 and 2000 Prices : " + title);
		return title;
	}
	
	@Step("AirDoctor 5500 and 2000 Smart Summer Sale Price")
	public String getad5500_2000_smartProductpriceText() throws InterruptedException {
		eleUtil.scrollTiView(ad5500_2000_addToCart);
		Thread.sleep(5000);
		eleUtil.doActionsClick(ad5500_2000_UpgradeToIotCheckbox);
		Allure.addAttachment("AD 5500 and 2000 Smart Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad5500_2000_smartproductregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad5500_2000_smartproductsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad5500_2000_smartproductsaleonsalepricetext);
		System.out.println("AD 5500 and 2000 Prices : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 and 2000 Summer Sale Product Name")
	public String getad3500_2000_ProductnameText() {
		String title = eleUtil.doGetElementText(ad3500_2000_productnametext);
		System.out.println("AD 3500 and 2000 Model Name : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 and 2000 Summer Sale Price")
	public String getad3500_2000_ProductpriceText() throws InterruptedException {
		Thread.sleep(3000);
		eleUtil.scrollTiView(ad3500_2000_addToCart);
		Allure.addAttachment("AD 3500 and 2000 Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad3500_2000_productregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad3500_2000_productsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad3500_2000_productsaleonsalepricetext);
		System.out.println("AD 3500 and 2000 Prices : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 and 2000 Smart Summer Sale Price")
	public String getad3500_2000_smartProductpriceText() throws InterruptedException {
		eleUtil.scrollTiView(ad5500_2000_addToCart);
		Thread.sleep(5000);
		eleUtil.doActionsClick(ad3500_2000_UpgradeToIotCheckbox);
		Allure.addAttachment("AD 3500 and 2000 Smart Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad3500_2000_smartproductregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad3500_2000_smartproductsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad3500_2000_smartproductsaleonsalepricetext);
		System.out.println("AD 3500 and 2000 Prices : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 and 2500 Summer Sale Product Name")
	public String getad3500_2500_ProductnameText() {
		String title = eleUtil.doGetElementText(ad3500_2500_productnametext);
		System.out.println("AD 3500 and 2500 Model Name : " + title);
		return title;
	}
	
	@Step("AirDoctor 3500 and 2500 Summer Sale Price")
	public String getad3500_2500_ProductpriceText() throws InterruptedException {
		Thread.sleep(3000);
		eleUtil.scrollTiView(ad3500_2500_addToCart);
		Allure.addAttachment("AD 3500 and 2500 Prices", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String title = 
		eleUtil.doGetElementText(ad3500_2500_productregularpricetext)+ "\n" +
		eleUtil.doGetElementText(ad3500_2500_productsalepricetext) + "\n" +
		eleUtil.doGetElementText(ad3500_2500_productsaleonsalepricetext);
		System.out.println("AD 3500 and 2500 Prices : " + title);
		return title;
	}
	


	@Step("Clicking on Shop Now button")
	public void clickShopNow() throws InterruptedException {
	eleUtil.doActionsClick(shopNow);
	Thread.sleep(5000);
	}
	public void clearcookiepopup() throws InterruptedException {
		driver.findElement(shadowroot).getShadowRoot().findElement(cookiebannerbtn).click();
		Thread.sleep(2000);
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

	@Step("Removing Products from Cart")
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
	
	  @Step("Getting PreSubtotal,PreTax,PreShipping,PreFinal Total from from Checkout Page")
		public Map<String, String> getpreorderdetails() throws InvalidFormatException, IOException {
			Allure.addAttachment("Pre Order Details", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
			preorderdetailsMap.put("preordersubtotal", eleUtil.getElement(presubtotalvalue).getText());
			preorderdetailsMap.put("preordertax", eleUtil.getElement(pretaxvalue).getText());
			String pretax=eleUtil.getElement(pretaxvalue).getText();
			System.out.println(eleUtil.getElement(pretaxvalue).getText());			
			preorderdetailsMap.put("preorderShipping", eleUtil.getElement(preflatrate).getText());
			preorderdetailsMap.put("preordertotal", eleUtil.getElement(prefinaltotal).getText());
			System.out.println("product Details: \n" + preorderdetailsMap);
			return preorderdetailsMap;
		}	
			
    @Step("Getting Subtotal,Tax,Shipping,Final Total and Order Numners from from Thank You Page")
	public Map<String, String> getpostorderdetails() throws InvalidFormatException, IOException {
		Allure.addAttachment("Post Order Details", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		postorderdetailsMap.put("subtotal", eleUtil.getElement(subtotalvalue).getText());
		postorderdetailsMap.put("tax", eleUtil.getElement(taxvalue).getText());
		postorderdetailsMap.put("Shipping", eleUtil.getElement(flatrate).getText());
		postorderdetailsMap.put("total", eleUtil.getElement(finaltotal).getText());
		postorderdetailsMap.put("OrderID", eleUtil.getElement(ordernumber).getText());
		System.out.println("product Details: \n" + postorderdetailsMap);
		return postorderdetailsMap;
	}

	public void SelectUpsell(String Upsell1) throws InterruptedException {
		try {
		if(Upsell1.equalsIgnoreCase("Yes")&& eleUtil.isElementExist(UpdradetoIotbtn)) 
		{
			Thread.sleep(5000);
			System.out.println(eleUtil.doGetElementText(UpdradetoIotbtn));
			System.out.println("Trying to Upgrade to iOT Upsell");
			Allure.addAttachment("iOT Upsell", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
			eleUtil.doActionsClick(UpdradetoIotbtn);
			Thread.sleep(3000);
			eleUtil.doActionsClick(buyUpsell);
			System.out.println("Upgraded to iOT Upsell");
			Thread.sleep(3000);
	}
		else if(Upsell1.equalsIgnoreCase("No")||Upsell1.equalsIgnoreCase("")&& eleUtil.isElementExist(UpdradetoIotbtn))  {
	    	Thread.sleep(5000);
	    	System.out.println("Trying to Cancel iOT Upsell");
	    	eleUtil.doActionsClick(cancelUpsell);
			Allure.addAttachment("Cancel iOT Upsell", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
	    	System.out.println("Cancelled iOT Upsell ");
	    	Thread.sleep(5000);
	    	//eleUtil.clickWhenReady(cancelUpsell, TimeUtil.DEFAULT_LONG_TIME);
			}
		else {
			Allure.addAttachment("Upsell Selection Error", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
			System.out.println("Error While Selecting Upsell");
			// Fail - Incorrect payment option
		}
		}

		catch (Exception ex) {
			System.out.println("Failed on Upsell");
			throw ex;

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
			//System.out.println("New URL: " + newUrl);
			driver.get(newUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// return url;
	}
    @Step("Filling Billing and Payment Details")
	public void checkout(String email, String firstname, String lastname, String addone, String addtwo, String cty,
			String state, String zip, String phonenumber) throws InterruptedException, Exception {
		eleUtil.doSendKeys(emailField, email);
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(lastName, lastname);
		eleUtil.doSendKeys(addressone, addone);
		Allure.addAttachment("Checkout page details", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		eleUtil.doSendKeys(addresstwo, addtwo);
		eleUtil.doSendKeys(city, cty);
		JsUtil.scrollIntoView(eleUtil.getElement(dropdownBtn));
		eleUtil.clickWhenReady(dropdownBtn, TimeUtil.DEFAULT_MEDIUM_TIME);
		Allure.addAttachment("Checkout page details", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		eleUtil.doSendKeys(statetextfield, state);
		eleUtil.clickWhenReady(stateoption, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.SendKeys(zipcode, zip);
		eleUtil.doSendKeys(phone, phonenumber);
		Allure.addAttachment("Checkout page details", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		JsUtil.scrollIntoView(eleUtil.getElement(firstName));
		Thread.sleep(5000);
		/*
		 * JsUtil.scrollIntoView(eleUtil.getElement(cardnumberfield));
		 * Thread.sleep(3000); eleUtil.doActionsSendKeys(cardnumberfield,
		 * AppConstants.CARD_NUMBER); eleUtil.doActionsSendKeys(cardexpiryfield,
		 * AppConstants.CARD_EXPIRY);
		 * JsUtil.scrollIntoView(eleUtil.getElement(placeorderbtn)); Thread.sleep(5000);
		 * eleUtil.doActionsSendKeys(cardseccodefield, AppConstants.SECURITY_CODE);
		 * JsUtil.scrollIntoView(eleUtil.getElement(placeorderbtn));
		 */
		
		
		// eleUtil.doClickcheckbox(termscheckbox,TimeUtil.DEFAULT_MEDIUM_TIME);
		
	}
    
    public void placeOrder() throws InterruptedException {
    	Thread.sleep(5000);
    	JsUtil.scrollIntoView(eleUtil.getElement(termscheckbox));
    	eleUtil.doActionsClick(termscheckbox);
    	Thread.sleep(3000);
		eleUtil.doActionsClick(placeorderadbtn);
		Allure.addAttachment("Checkout page details", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		eleUtil.clickWhenReady(popupdonebtn, TimeUtil.DEFAULT_MEDIUM_TIME);
    	}
    

	@Step("Selecting Model")
	public void selectModel(String ModelName, String ProductQuantity) throws InterruptedException, Exception {
		try {
			
			if (ModelName.equalsIgnoreCase("AD3500") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(sm3500addToCart);
				Allure.addAttachment("Select Model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
				Thread.sleep(5000);
				eleUtil.doActionsClick(sm3500addToCart);
				Thread.sleep(5000);
			}
			else if (ModelName.equalsIgnoreCase("AD3500") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);;
				eleUtil.scrollTiView(sm3500addToCart);
				Thread.sleep(3000);
				Allure.addAttachment("Select Model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500addToCart);
				Thread.sleep(5000);
				eleUtil.doActionsClick(sm3500increasequantitybtn);
				Thread.sleep(5000);
			}
			else if (ModelName.equalsIgnoreCase("AD3500") && ProductQuantity.equals("3")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);;
				eleUtil.scrollTiView(sm3500addToCart);
				Thread.sleep(3000);
				Allure.addAttachment("Select Model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500addToCart);
				Thread.sleep(5000);
				eleUtil.doActionsClick(sm3500increasequantitybtn);
				eleUtil.doActionsClick(sm3500increasequantitybtn);
				Thread.sleep(5000);
			}
			else if (ModelName.equalsIgnoreCase("AD3500i") && ProductQuantity.equals("1")) {
				System.out.println(ModelName + " with quantity " + ProductQuantity);
				eleUtil.scrollTiView(sm3500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500UpgradeToIotCheckbox);
				Allure.addAttachment("Select Model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
				Thread.sleep(5000);
				eleUtil.doActionsClick(sm3500smartaddToCart);
				Thread.sleep(5000);
			}
			else if (ModelName.equalsIgnoreCase("AD3500i") && ProductQuantity.equals("2")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);;
				eleUtil.scrollTiView(sm3500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500UpgradeToIotCheckbox);
				Allure.addAttachment("Select Model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500smartaddToCart);
				Thread.sleep(5000);
				eleUtil.doActionsClick(sm3500increasequantitybtn);
				Thread.sleep(5000);
			}
			else if (ModelName.equalsIgnoreCase("AD3500i") && ProductQuantity.equals("3")) {

				System.out.println(ModelName + " with quantity " + ProductQuantity);;
				eleUtil.scrollTiView(sm3500addToCart);
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500UpgradeToIotCheckbox);
				Allure.addAttachment("Select Model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
				Thread.sleep(3000);
				eleUtil.doActionsClick(sm3500smartaddToCart);
				Thread.sleep(5000);
				eleUtil.doActionsClick(sm3500increasequantitybtn);
				eleUtil.doActionsClick(sm3500increasequantitybtn);
				Thread.sleep(5000);
			}
									
			else {
				System.out.println("Incorrect Model");
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
	
	@Step("Entering Values in Excel sheet for Subtotal,Shipping,Tax and Overall Total")
	public void livewriteexcel(String presubtotal, String preflatrate, String pretax, String pretotal,int count)
			throws InvalidFormatException, IOException {
		ExcelUtillive.setdata(AppConstants.LIVE_SITE_MERGE_SHEET_NAME, presubtotal, preflatrate, pretax, pretotal,count);
	}


	public int testMe(ITestContext testContext) {
		int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
		System.out.println("Executing count: " + currentCount);
		return currentCount;
	}

	@Step("Logging Out from Thank You page")
	public LoginPage logoutfromthankyoupage() throws InterruptedException {
		eleUtil.clickWhenReady(backtohomepage, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.clickWhenReady(loginIcon, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.clickWhenReady(logout, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.clickWhenReady(backtohomepage, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new LoginPage(driver);
	}
    
	
}
