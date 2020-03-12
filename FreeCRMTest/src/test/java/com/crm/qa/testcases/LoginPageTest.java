package com.crm.qa.testcases;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	// created LoginPage class reference so that can be used throughout class
	LoginPage loginpage;
	HomePage homepage;

	// creating constructor
	public LoginPageTest() {
		super();
		// super call TestBase class constructor
		// to load properties file

	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		intialization();

		// create object of login page class
		// so that i can use all functions of login class
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginpage.validateLoginPageTitle();
		System.out.println(title);
		AssertJUnit.assertEquals(title,
				"CRMPRO - CRM software for customer relationship management, sales, and support.",
				"login page title is not matched");
	}

	@Test(priority = 2)
	public void crmLOgoImageTest() {
		boolean flag = loginpage.validateCRMImage();
		AssertJUnit.assertTrue(flag);
	}

	@Test(priority = 3)
	public void loginTest() {
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
