package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLable;

	@FindBy(name = "title")
	WebElement dropDown;

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='surname']")
	WebElement lastName;

	@FindBy(name = "client_lookup")
	WebElement companyName;

	// xpath not correct
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveButton;

	// a[contains(text(),'david
	// chris')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id'
	// and @value='52738143'] WebElement contactsLabel;
	/*
	 * @FindBy(
	 * xpath="a[contains(text(),'david chris')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id' and @value='52738143']"
	 * ) WebElement contactsCheckbox;
	 */

	// here in the above x path we are clicking on davis chris checkbox
	// but in future if we want to click on another checkbox how would you click
	// so it is not good practice and its not compulsory to write finddby for all
	// the webelements
	// so comment findby

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactsLable() {
		return contactsLable.isDisplayed();
	}

	public void selectContacts(String nameOfContact) {
		driver.findElement(By.xpath("//a[text()='" + nameOfContact
				+ "']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//child::input[@type='checkbox']"))
				.click();
		// find dynamic x path without value attribute
	}

	public void createNewContact(String title, String ftName, String ltName, String company) {
		Select select = new Select(dropDown);
		select.selectByVisibleText(title);
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		companyName.sendKeys(company);
		saveButton.click();

	}

}
