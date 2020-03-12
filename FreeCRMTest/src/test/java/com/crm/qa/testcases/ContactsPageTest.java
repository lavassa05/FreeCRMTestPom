package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactsPage;

	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		// remember this sequence
		intialization();
		testutil = new TestUtil();
		contactsPage = new ContactsPage();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchToMainFrame();// before clicking on contact page frame is there
		contactsPage = homepage.clickOnContactsLink();

	}

	@Test(priority = 1)
	public void contactsPageLableTest() {
		boolean flag2contactpagelable = contactsPage.verifyContactsLable();
		System.out.println(flag2contactpagelable);
		Assert.assertTrue(flag2contactpagelable, "contact label of page is missing");

	}

	@Test(priority = 2)
	public void selectContactsCheckboxTest() {
		contactsPage.selectContacts("david john");
	}

	@Test(priority = 3)
	public void selectMultipleContactsCheckboxTest() {
		contactsPage.selectContacts("pooja shukla");// some time this element deleted by other users,exception no such
													// element
		contactsPage.selectContacts("sandip jadhav");// some time this element deleted by other users
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
