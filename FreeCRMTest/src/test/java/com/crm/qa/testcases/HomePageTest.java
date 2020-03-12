package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	String shtName = "Sheet1";

	public HomePageTest() {
		super();
		// calling properties file
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		// initialise driver and launch url
		intialization();
		// create obj of TestUtil to handle frames
		testutil = new TestUtil();

		// to reach homepage we have to login
		loginpage = new LoginPage();
		// login method return homepage
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	// testcases should be independent of each other avoid dependency of each-other
	// before each t.c. launch browser and login
	// @test--execute the t.c.
	// after every t.c. close the browser
	// checking that we get homepage

	@Test(priority = 1)
	public void homePageTitleTest() {
		String titleOfHomePage = homepage.verifyHomePageTitle();
		System.out.println(titleOfHomePage);
		Assert.assertEquals(titleOfHomePage, "CRMPRO", "Home Page Title Not Matched");

	}

	@Test(priority = 2)
	public void userLableTest() {
		testutil.switchToMainFrame();
		boolean flag1 = homepage.verifyUserLabel();
		Assert.assertTrue(flag1);
	}

	@Test(priority = 3)
	public void clickOnContactsTest() {
		testutil.switchToMainFrame();
		contactspage = homepage.clickOnContactsLink();
	}

	// page object model with data driven model
	//hybrid model+pom+DDT
	//not recommended keyword driven approach
	@DataProvider
	public Object[][] getCRMTestData() {
		// return type of get data is 2d array so return [][] data
		Object[][] data = TestUtil.getTestData(shtName);
		// now complete data is available in data
		return data;
	}

	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void clickOnNewContactLinkTest(String title, String firstname, String lastname, String company)
			throws InterruptedException {
		// homepage.clickOnNewContactLink("Mr.", "aaj", "kal", "tttt");
		homepage.clickOnNewContactLink(title, firstname, lastname, company);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
