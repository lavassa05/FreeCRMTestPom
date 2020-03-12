package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class mouseHoverOnContact {
	WebDriver driver;

	@BeforeMethod
	public void start() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		driver = new ChromeDriver();// launch browser

		driver.manage().window().maximize();// maximize window
		driver.manage().deleteAllCookies();// delet all cookies

		// dynamic wait
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// enter url
		driver.get("https://classic.freecrm.com/");

	}

	@Test
	public void MouseHoverElement() throws InterruptedException {
		driver.findElement(By.name("username")).sendKeys("batchautomation");
		driver.findElement(By.name("password")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Contact')]")).click();

		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText("Mr.");

		driver.findElement(By.id("first_name")).sendKeys("aaaa");
		driver.findElement(By.id("surname")).sendKeys("bbb");
		driver.findElement(By.name("client_lookup")).sendKeys("aliBABA");
		// driver.findElement(By.xpath("//*[@id=\"contactForm\"]/table/tbody/tr[1]/td/input[2]")).click();

	}

}
