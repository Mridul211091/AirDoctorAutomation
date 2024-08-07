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

public class CheckLinksTest extends BaseTest {

	//@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {

	}

	@DataProvider
	public Object[][] getAffiliateLinkFromExcel() {
		return ExcelUtil.getTestData(AppConstants.INVALID_LINK);
	}

	 @Test(dataProvider="getAffiliateLinkFromExcel") 
	  public void CheckLinkTest(String url,String validateurl,String devurl) throws Exception { 
	  //setup(); 
	try {
		checklinkPage.getLinksFromPage(url, validateurl);
		checklinkPage.generateReport();
	}
	finally {
	tearDown();
	setup();
	}
	 }
	
}


