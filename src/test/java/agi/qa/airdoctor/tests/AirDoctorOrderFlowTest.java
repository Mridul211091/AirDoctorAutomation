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

public class AirDoctorOrderFlowTest extends BaseTest {
	
	

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {
	
		
		 
	}
	
	
	  @DataProvider public Object[][] getDataFromExcel() { 
	   return ExcelUtil.getTestData(AppConstants. EVERFLOW_TEST ); 
	   }
	 
	
	  @Test(dataProvider = "getDataFromExcel") 
	  public void placeOrder(ITestContext testContext,String ModelName,String ProductQuantity,
	  String ModeltwoName,String ProducttwoQuantity, String filter,String email, String firstname, 
	  String lastname, String addone, String addtwo, String cty ,String state,String zipcode,
	  String phonenumber,String Upsell1,String subtotal, String flatrate, String tax, 
	  String finaltotal) throws InterruptedException, Exception {
	try {
	  //loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	  airddoctorstg.clearCart();
	  airddoctorstg.clearcookiepopup();
	  airddoctorstg.clickShopNow();
	  softAssert = new SoftAssert();
	  int currenttest= airddoctorstg.testMe(testContext);
	  Thread.sleep(3000);
	  System.out.println(ModelName);
	  airddoctorstg.selectModel(ModelName, ProductQuantity,ModeltwoName,ProducttwoQuantity); 
	  Thread.sleep(5000);
	  airddoctorstg.checkout(email,firstname, lastname, addone, addtwo, cty, state,zipcode,phonenumber); 
	  Thread.sleep(15000);
	  airddoctorstg.SelectUpsell(Upsell1);
	  Thread.sleep(5000);
	  airddoctorstg.getThankYoPageURL();
	  Map<String, String>  productActDetailsMap = airddoctorstg.getorderdetails();
	  softAssert.assertEquals(productActDetailsMap.get("subtotal"), subtotal);
	  System.out.println("=============================================================");
	  System.out.println("Expected Subtotal: "+subtotal+"|| Actual Subtotal: "+productActDetailsMap.get("subtotal"));
	  softAssert.assertEquals(productActDetailsMap.get("Shipping"), flatrate);
	  System.out.println("Expected Shipping: "+flatrate+"|| Actual Shipping: "+productActDetailsMap.get("Shipping"));
	  softAssert.assertEquals(productActDetailsMap.get("tax"), tax);
	  System.out.println("Expected tax: "+tax+" || Actual tax: "+productActDetailsMap.get("tax"));
	  softAssert.assertEquals(productActDetailsMap.get("total"), finaltotal);
	  System.out.println("Expected total: "+finaltotal+" || Actual total: "+productActDetailsMap.get("total"));
	  System.out.println("=============================================================");
	  airddoctorstg.writeexcelold(productActDetailsMap.get("subtotal"),productActDetailsMap.get("Shipping"),productActDetailsMap.get("tax"),productActDetailsMap.get("total"),productActDetailsMap.get("OrderID"),currenttest); 
	  softAssert.assertAll();
	}
	finally {
	tearDown();
	setup();
	}
	 
	  }
	 
}

