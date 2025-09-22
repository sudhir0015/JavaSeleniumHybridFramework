package com.automationninjatutorial.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//These webelements are object.
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this); //this is equal to this.HomePage
	}
	
	//These methods are action
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	

}
