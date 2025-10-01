package com.automationninjatutorial.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationninjatutorial.qa.base.Base;
import com.automationninjatutorial.qa.pages.AccountPage;
import com.automationninjatutorial.qa.pages.HomePage;
import com.automationninjatutorial.qa.pages.LoginPage;
import com.automationninjatutorial.qa.utilities.Utilities;


public class LoginTest extends Base{
	
	LoginPage loginPage;
	AccountPage accountPage;
	
	public LoginTest() {
		super();
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();		
    }
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData(){
		
		Object[][] data = Utilities.getTextDataFromExcel("Login");		
		return data;
	}
	
	@Test(priority=1, dataProvider="validCredentialSupplier")
	public void verifyLoginWithValidCredential(String email, String password) {
		
		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfomationOption(), "Login is not successful");
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredential() {		
		
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("inValidPassword"));
		Assert.assertTrue(loginPage.getWarningMessageTextForWrongPassword().contains(dataProp.getProperty("emailPasswordNotMachingWarning")), "Expected warning message not displayed");

	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.getWarningMessageTextForWrongPassword().contains(dataProp.getProperty("emailPasswordNotMachingWarning")), "Expected warning message not displayed");

	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("inValidPassword"));
		Assert.assertTrue(loginPage.getWarningMessageTextForWrongPassword().contains(dataProp.getProperty("emailPasswordNotMachingWarning")), "Expected warning message not displayed");

	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredential() {
		
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.getWarningMessageTextForWrongPassword().contains(dataProp.getProperty("emailPasswordNotMachingWarning")), "Expected warning message not displayed");
		
	}
	
}
