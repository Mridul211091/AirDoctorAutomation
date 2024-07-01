package agi.qa.airdoctor.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import agi.qa.airdoctor.constants.AppConstants;
import agi.qa.airdoctor.factory.DriverFactory;
import agi.qa.airdoctor.pages.Ad2500AffiliatePage;
import agi.qa.airdoctor.pages.AirDoctorMemorialDayPage;
import agi.qa.airdoctor.pages.AirDoctorStagePage;
import agi.qa.airdoctor.pages.AirDoctorSummerSaleLivePage;
import agi.qa.airdoctor.pages.IndependenceDayOrderFlowPage;
import agi.qa.airdoctor.pages.LiveIndependenceDayOrderFlowPage;
import agi.qa.airdoctor.pages.LoginPage;
import agi.qa.airdoctor.utils.ExcelUtil;




public class BaseTest {
	
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage loginPage;
	protected Ad2500AffiliatePage affiliatePage;
	protected AirDoctorSummerSaleLivePage summersalePage;
	protected AirDoctorMemorialDayPage memorialPage;
	protected AirDoctorStagePage airddoctorstg;
	protected IndependenceDayOrderFlowPage independencedayPage;
	protected LiveIndependenceDayOrderFlowPage liveindependencedayPage;
	protected SoftAssert softAssert;
	
	//@Parameters({"browser"})
	/*
	 * @BeforeTest public void setup(String browserName) { df = new DriverFactory();
	 * prop = df.initProp();
	 * 
	 * 
	 * if(browserName!=null) { prop.setProperty("browser", browserName); }
	 * 
	 * 
	 * driver = df.initDriver(prop); loginPage = new LoginPage(driver); softAssert =
	 * new SoftAssert(); }
	 */
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		//affiliatePage = new Ad2500AffiliatePage(driver);
		//summersalePage= new AirDoctorSummerSaleLivePage(driver);
		//softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

}
