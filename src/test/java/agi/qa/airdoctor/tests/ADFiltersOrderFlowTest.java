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

public class ADFiltersOrderFlowTest extends BaseTest {
	
	

	@BeforeClass()
	public void affilatePageSetup() throws InterruptedException {
	
		
		 
	}	
	  @DataProvider public Object[][] getDataFromExcel() { 
	   return ExcelUtil.getTestData(AppConstants.FILTER_EVERFLOW_TEST); 
	   }
	 
	
	  @Test(dataProvider = "getDataFromExcel") 
	  public void placeOrder(ITestContext testContext,String ModelName,String ProductQuantity,String email, 
	  String firstname, String lastname, String addone, String addtwo, String cty ,String state,String zipcode,
	  String phonenumber,String Upsell1,String presubtotal, String preflatrate, String pretax, 
	  String prefinaltotal,String subtotal, String flatrate, String tax, 
	  String finaltotal) throws InterruptedException, Exception {
	try {
	  adfilterpage.clearcookiepopup();
      adfilterpage.clickFilters();
	  softAssert = new SoftAssert();
	  int currenttest= adfilterpage.testMe(testContext);
	  Thread.sleep(3000);
	  adfilterpage.selectModel(ModelName, ProductQuantity); 
	  Thread.sleep(5000);
	  adfilterpage.checkout(email,firstname, lastname, addone, addtwo, cty, state,zipcode,phonenumber); 
	  Map<String, String>  preOrderDetailsMap = adfilterpage.getpreorderdetails();
	  softAssert.assertEquals(preOrderDetailsMap.get("preordersubtotal"), presubtotal);
	  System.out.println("=============================================================");
	  System.out.println("Expected PreSubtotal: "+presubtotal+"|| Actual Subtotal: "+preOrderDetailsMap.get("preordersubtotal"));
	  softAssert.assertEquals(preOrderDetailsMap.get("preorderShipping"), preflatrate);
	  System.out.println("Expected PreShipping: "+preflatrate+"|| Actual Shipping: "+preOrderDetailsMap.get("preorderShipping"));
	  softAssert.assertEquals(preOrderDetailsMap.get("preordertax"), pretax);
	  System.out.println("Expected Pretax: "+pretax+" || Actual tax: "+preOrderDetailsMap.get("preordertax"));
	  softAssert.assertEquals(preOrderDetailsMap.get("preordertotal"), prefinaltotal);
	  System.out.println("Expected Pretotal: "+prefinaltotal+" || Actual total: "+preOrderDetailsMap.get("preordertotal"));
	  adfilterpage.placeOrder();
	  Thread.sleep(15000);
	  adfilterpage.SelectUpsell(Upsell1);
	  Thread.sleep(5000);
	  adfilterpage.getThankYoPageURL();
	  Map<String, String>  productActDetailsMap = adfilterpage.getpostorderdetails();
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
	  adfilterpage.writeexcel(preOrderDetailsMap.get("preordersubtotal"),preOrderDetailsMap.get("preorderShipping"),preOrderDetailsMap.get("preordertax"),preOrderDetailsMap.get("preordertotal"),productActDetailsMap.get("subtotal"),productActDetailsMap.get("Shipping"),productActDetailsMap.get("tax"),productActDetailsMap.get("total"),productActDetailsMap.get("OrderID"),currenttest); 
	  softAssert.assertAll();
	}
	finally {
	tearDown();
	setup();
	}
	 
	  }
	 
}

