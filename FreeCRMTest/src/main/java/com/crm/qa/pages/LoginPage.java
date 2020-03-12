package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	// here we have to define object repository of logine page
	// i.e. pagefactory of logine page @findBy
	// @findby predefined symbol below

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginbtn;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement Sinuplink;

	@FindBy(xpath = "//a[@class='navbar-brand']//child::img")
	WebElement crmlogo;

	// until now we have created different WebElement of login page using @FindBy()
	// now we have to initialize page factory or object repository
	// Initialize in class constructor
	// how to initialize elements with the help of PageFactory
	// and initElements(driver, pageClassToProxy) method-initialize element with driver
	//
	public LoginPage() {
		// Initialize current class object (this) with driver
		//this means poiting to current class object
		PageFactory.initElements(driver, this);
	}
	
	//define actions of loginpage
	
	//1.verify the title of the page
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	
	//2.verify logo image of crm page
	public boolean validateCRMImage() {
		return crmlogo.isDisplayed();
	}
	//3.login to home page
	public HomePage login(String usrn,String passw) {
		username.sendKeys(usrn);
		password.sendKeys(passw);
		loginbtn.click();
		
		//by clicking login from login page we enter on home page 
		//so return type of this method is home page class object
		
		return new HomePage();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
