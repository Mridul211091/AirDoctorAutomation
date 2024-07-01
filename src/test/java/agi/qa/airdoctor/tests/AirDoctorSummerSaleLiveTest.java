package agi.qa.airdoctor.tests;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import agi.qa.airdoctor.base.BaseTest;
import agi.qa.airdoctor.constants.AppConstants;
import agi.qa.airdoctor.utils.ExcelUtil;

public class AirDoctorSummerSaleLiveTest extends BaseTest {

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {

		// affiliatePage = loginPage.clickShopNow();
		//affiliatePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// softAssert.assertEquals(loginPage.getLoginSuccessText(),AppConstants.LOGIN_SUCCESS_TEXT);
		// pdpPage= loginPage.clickBuyNow();

	}

	/*
	 * @DataProvider public Object[][] getDataFromExcel() { return
	 * ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME); }
	 */

	@DataProvider
	public Object[][] getAffiliateLinkFromExcel() {
		return ExcelUtil.getTestData(AppConstants.AFFILIATE_LINK);
	}

	@Test
	public void LandingPageBannerTest(String url) throws InterruptedException {
		String bannerText = affiliatePage.getBannerText(url);
		Assert.assertEquals(bannerText, AppConstants.AD_AFFILIATE_PAGE_BANNER_TEXT);
	}

	 @Test(dataProvider="getAffiliateLinkFromExcel") 
	  public void AffiliateLinkTest(ITestContext testContext,String url) throws Exception { 
	  //setup(); 
	try {
	  url = url.trim();
	  softAssert = new SoftAssert();
	  summersalePage = summersalePage.getaffiliateURL(url);	 
	  summersalePage.clickShopNow();
	  Thread.sleep(3000);
	  
	  String ad3500productnameText = summersalePage.get3500ProductnameText();
	  String ad3500productpriceText = summersalePage.get3500ProductpriceText();
	  String ad3500smartproductpriceText = summersalePage.get3500smartProductpriceText();
	  softAssert.assertEquals(ad3500productnameText,AppConstants.AD_3500_PRODUCT_NAME);  
	  softAssert.assertEquals(ad3500productpriceText,AppConstants.AD_3500_PRICES);  
	  softAssert.assertEquals(ad3500smartproductpriceText,AppConstants.AD_3500_SMART_PRICES);
	  
	  String ad5500_3500_2000productnameText = summersalePage.getad5500_3500_2000ProductnameText();
	  String ad5500_3500_2000productpriceText = summersalePage.getad5500_3500_2000ProductpriceText();
	  String ad5500_3500_2000smartproductpriceText = summersalePage.getad5500_3500_2000smartProductpriceText();
	  softAssert.assertEquals(ad5500_3500_2000productnameText,AppConstants.AD_5500_3500_2000_PRODUCT_NAME);  
	  softAssert.assertEquals(ad5500_3500_2000productpriceText,AppConstants.AD_5500_3500_2000_PRICES);  
	  softAssert.assertEquals(ad5500_3500_2000smartproductpriceText,AppConstants.AD_5500_3500_2000_SMART_PRICES);
	  
	  String ad5500_2000_productnameText = summersalePage.getad5500_2000_ProductnameText();
	  String ad5500_2000_productpriceText = summersalePage.getad5500_2000_ProductpriceText();
	  String ad5500_2000_smartproductpriceText = summersalePage.getad5500_2000_smartProductpriceText();
	  softAssert.assertEquals(ad5500_2000_productnameText,AppConstants.AD_5000_2000_PRODUCT_NAME);  
	  softAssert.assertEquals(ad5500_2000_productpriceText,AppConstants.AD_5000_2000_PRICES);  
	  softAssert.assertEquals(ad5500_2000_smartproductpriceText,AppConstants.AD_5000_2000_SMART_PRICES);
	  
	  String ad3500_2000_productnameText = summersalePage.getad3500_2000_ProductnameText();
	  String ad3500_2000_productpriceText = summersalePage.getad3500_2000_ProductpriceText();
	  String ad3500_2000_smartproductpriceText = summersalePage.getad3500_2000_smartProductpriceText();
	  softAssert.assertEquals(ad3500_2000_productnameText,AppConstants.AD_3500_2000_PRODUCT_NAME);  
	  softAssert.assertEquals(ad3500_2000_productpriceText,AppConstants.AD_3500_2000_PRICES);  
	  softAssert.assertEquals(ad3500_2000_smartproductpriceText,AppConstants.AD_3500_2000_SMART_PRICES);
	  	  
	  String ad3500_2500_productnameText = summersalePage.getad3500_2500_ProductnameText();
	  String ad3500_2500_productpriceText = summersalePage.getad3500_2500_ProductpriceText();
	  softAssert.assertEquals(ad3500_2500_productnameText,AppConstants.AD_3500_2500_PRODUCT_NAME);  
	  softAssert.assertEquals(ad3500_2500_productpriceText,AppConstants.AD_3500_2500_PRICES);  
	  	  
	  
	  softAssert.assertAll(); 
	}
	finally {
	tearDown();
	setup();
	}
	 }
	
}


