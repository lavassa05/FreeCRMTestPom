package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener_1;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener_1 webeventlistner;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Selenium projects\\"
					+ "feb2020\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void intialization() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

			driver = new ChromeDriver();// launch browser

		} else if (browserName.equals("FF")) {

			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

			driver = new ChromeDriver();// launch browser

		}

		e_driver = new EventFiringWebDriver(driver);
		webeventlistner = new WebEventListener_1();
		e_driver.register(webeventlistner);
		driver = e_driver;//assigning eventfiredriver object refrence to wedriver object refrence

		driver.manage().window().maximize();// maximize window
		driver.manage().deleteAllCookies();// delet all cookies

		// dynamic wait
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}
}
