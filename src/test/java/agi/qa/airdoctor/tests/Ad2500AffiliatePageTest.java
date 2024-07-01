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

public class Ad2500AffiliatePageTest extends BaseTest {

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
		return ExcelUtil.getTestData(AppConstants.CUSTOM_AFFILIATE_LINK);
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
	  affiliatePage = affiliatePage.getaffiliateURL(url);	 
	  String bannerText = affiliatePage.getBannerText(url);
	  softAssert.assertEquals(bannerText,AppConstants.AD_CUSTOMAFFILIATE2_PAGE_BANNER_TEXT);  
	  softAssert.assertAll(); 
	}
	finally {
	tearDown();
	setup();
	}
	 }
	
}


