package com.automationninjatutorial.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privecyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privecyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']//following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']//following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']//following-sibling::div")
	private WebElement emailTextNotEnteredWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']//following-sibling::div")
	private WebElement telephoneNumberNotEnteredWarning;
	
	@FindBy(xpath="//input[@id='input-password']//following-sibling::div")
	private WebElement passwordTextNotEnteredWarning;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this); //this is equal to this.HomePage
	}
	
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		passwordConfirmField.sendKeys(confirmPasswordText);
	}
	
	public void selectprivecyPolicyField() {
		privecyPolicyField.click();
	}
	
	public AccountSuccessPage clickContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	
	public String retriveDuplicateEmailWarningMessage() {
		String retrieveDuplicateEmailWarningText = duplicateEmailWarning.getText();
		return retrieveDuplicateEmailWarningText;
	}
	
	public String retriveFirstNameWarning() {
		String retriveFirstNameWarningText = firstNameWarning.getText();
		return retriveFirstNameWarningText;
	}
	
	public String retriveLastNameWarning() {
		String retriveLastNameWarningText = lastNameWarning.getText();
		return retriveLastNameWarningText;
	}
	
	public String retriveEmailWarning() {
		String retriveEmailWarningText = emailTextNotEnteredWarning.getText();
		return retriveEmailWarningText;
	}
	
	public String retriveTelephoneWarning() {
		String retriveTelephoneWarningText = telephoneNumberNotEnteredWarning.getText();
		return retriveTelephoneWarningText;
	}
	
	public String retrivePasswordWarning() {
		String retrivePasswordWarningText = passwordTextNotEnteredWarning.getText();
		return retrivePasswordWarningText;
	}
	
	public String retrivePrivecyPolicyWarning() {
		String retrivePrivecyPolicyWarningText = privecyPolicyWarning.getText();
		return retrivePrivecyPolicyWarningText;
	}
	
	
	
	
	
	

}
