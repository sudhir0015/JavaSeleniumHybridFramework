package com.automationninjatutorial.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationninjatutorial.qa.base.Base;
import com.automationninjatutorial.qa.pages.AccountSuccessPage;
import com.automationninjatutorial.qa.pages.HomePage;
import com.automationninjatutorial.qa.pages.RegisterPage;
import com.automationninjatutorial.qa.utilities.Utilities;

public class RegisterTest extends Base{
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
				
    }
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryField() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectprivecyPolicyField();
		accountSuccessPage = registerPage.clickContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Error in Account created");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllField() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectprivecyPolicyField();
		accountSuccessPage = registerPage.clickContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Error in Account created");

	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmail() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectprivecyPolicyField();
		accountSuccessPage = registerPage.clickContinueButton();
		String actualWarningMessage = registerPage.retriveDuplicateEmailWarningMessage();
		Assert.assertTrue(actualWarningMessage.contains(dataProp.getProperty("duplicateEmailWarning")), "Warning Message not displayed");

	}
	
	@Test(priority=4)
	public void verifyRegisteringWithoutProvdingAnyValue() {
		
		registerPage.clickContinueButton();

		String actualPrivacyWarningMessage = registerPage.retrivePrivecyPolicyWarning();
		Assert.assertTrue(actualPrivacyWarningMessage.contains(dataProp.getProperty("privacyPolicyWarning")), "Privecy warning message not displayed");

		String firstNameWarning = registerPage.retriveFirstNameWarning();
		Assert.assertTrue(firstNameWarning.contains(dataProp.getProperty("firstNameWarning")), "First name warning message is not displayed");

		String lastNameWarning = registerPage.retriveLastNameWarning();
		Assert.assertTrue(lastNameWarning.contains(dataProp.getProperty("lastNameWarning")), "Last name warning message is not displayed");

		String emailMailWarning = registerPage.retriveEmailWarning();
		Assert.assertTrue(emailMailWarning.contains(dataProp.getProperty("emailWarning")), "Email warning message is not displayed");

		String telephoneWarning = registerPage.retriveTelephoneWarning();
		Assert.assertTrue(telephoneWarning.contains(dataProp.getProperty("telephoneWarning")), "Telephone warning message is not displayed");

		String passwordWarning = registerPage.retrivePasswordWarning();
		Assert.assertTrue(passwordWarning.contains(dataProp.getProperty("passwordWarning")), "Password warning message is not displayed");
					
	}

}
