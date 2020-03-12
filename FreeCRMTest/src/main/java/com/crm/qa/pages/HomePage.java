package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	// customise x path =//html/body/table[1]/tbody/tr[1]/td/table/tbody/tr/td[1]
	
	@FindBy(xpath = "//td[contains(text(),'User: Demo User')]")
	
	//@cacheLookup available in selenium.support.PageFactory
	//  annotation store particular WebElement in cache memory
	//so every time u interacting with WebElement instead of from page it will get that element from cache memory
	//speed of framework will improve performance increases.
	//problem=some elements may corrupt or stale stale element exception if page get refreshed
	//if dom property of page changed then exception
	//so use it only when u knw path of element will not change
	@CacheLookup
	WebElement userLable;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactLink;

	// xpath =//a[@title='New Contact']
	// xpath =//*[text()='Contacts']//following::a[1]
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	// Initializing page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyUserLabel() {
		return userLable.isDisplayed();
	}

	// Remember=whenever we click on button or link
	// it always land on next page
	// so return type of that method must be landing page class.
	// and make sure you have created that class
	// page chaining=page object model

	public ContactsPage clickOnContactsLink() {
		contactLink.click();
		return new ContactsPage();
	}

	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}

	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}

	public void clickOnNewContactLink(String titleName, String frstName, String lstName, String copName)
			throws InterruptedException {
		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(contactLink).build().perform();
		Thread.sleep(2000);
		newContactLink.click();
		Thread.sleep(2000);

		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(titleName);

		driver.findElement(By.id("first_name")).sendKeys(frstName);
		driver.findElement(By.id("surname")).sendKeys(lstName);
		driver.findElement(By.name("client_lookup")).sendKeys(copName);
		driver.findElement(By.xpath("//*[@id=\"contactForm\"]/table/tbody/tr[1]/td/input[2]")).click();

	}

	// xpath of new contact==//*[text()='Contacts']//following::a[1]

}
