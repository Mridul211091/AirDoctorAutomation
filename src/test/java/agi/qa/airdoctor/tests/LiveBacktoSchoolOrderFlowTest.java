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
import agi.qa.airdoctor.utils.ExcelUtillive;

public class LiveBacktoSchoolOrderFlowTest extends BaseTest {
	
	

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {
	
		
		 
	}
	
	
	  @DataProvider public Object[][] getDataFromExcel() { 
	   return ExcelUtillive.getTestData(AppConstants.BACK_TO_SCHOOL_LIVE_SHEET_NAME); 
	   }
	 
	
	  @Test(dataProvider = "getDataFromExcel") 
	  public void placeOrder(ITestContext testContext,String ModelName,String ProductQuantity,String ModeltwoName,String ProducttwoQuantity, String email, 
	  String firstname, String lastname, String addone, String addtwo, String cty ,String state,String zipcode,
	  String phonenumber,String Upsell1,String presubtotal, String preflatrate, String pretax, 
	  String prefinaltotal) throws InterruptedException, Exception {
	try {
	//  livebacktoorderflowPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	  livebacktoorderflowPage.clearcookiepopup();
	  livebacktoorderflowPage.clearCart();
	  livebacktoorderflowPage.clickShopNow();
	  softAssert = new SoftAssert();
	  int currenttest= livebacktoorderflowPage.testMe(testContext);
	  Thread.sleep(3000);
	  livebacktoorderflowPage.selectModel(ModelName, ProductQuantity,ModeltwoName,ProducttwoQuantity); 
	  Thread.sleep(5000);
	  livebacktoorderflowPage.checkout(email,firstname, lastname, addone, addtwo, cty, state,zipcode,phonenumber); 
	  Map<String, String>  preOrderDetailsMap = livebacktoorderflowPage.getpreorderdetails();
	  softAssert.assertEquals(preOrderDetailsMap.get("preordersubtotal"), presubtotal);
	  System.out.println("=============================================================");
	  System.out.println("Expected PreSubtotal: "+presubtotal+"|| Actual Subtotal: "+preOrderDetailsMap.get("preordersubtotal"));
	  softAssert.assertEquals(preOrderDetailsMap.get("preorderShipping"), preflatrate);
	  System.out.println("Expected PreShipping: "+preflatrate+"|| Actual Shipping: "+preOrderDetailsMap.get("preorderShipping"));
	  softAssert.assertEquals(preOrderDetailsMap.get("preordertax"), pretax);
	  System.out.println("Expected Pretax: "+pretax+" || Actual tax: "+preOrderDetailsMap.get("preordertax"));
	  softAssert.assertEquals(preOrderDetailsMap.get("preordertotal"), prefinaltotal);
	  System.out.println("Expected Pretotal: "+prefinaltotal+" || Actual total: "+preOrderDetailsMap.get("preordertotal"));
	  livebacktoorderflowPage.writeexcel(preOrderDetailsMap.get("preordersubtotal"),preOrderDetailsMap.get("preorderShipping"),preOrderDetailsMap.get("preordertax"),preOrderDetailsMap.get("preordertotal"),currenttest); 
	  softAssert.assertAll();
	}
	finally {
	tearDown();
	setup();
	}
	 
	  }
	 
}

