package com.automationninjatutorial.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationninjatutorial.qa.base.Base;

public class SearchTest extends Base{
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
    }
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidSearch() {
		
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(), "Valid HP product not displayed in search");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage, "abcd","No product message in search results is not displayed");
		//Assertion commented lines are Removed to clean commented code.
		
	}
	
	@Test(priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidSearch"})
	public void verifySearchWithoutAnyProduct() {
		
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResult"),"No product message in search results is not displayed");
		
	}

}
