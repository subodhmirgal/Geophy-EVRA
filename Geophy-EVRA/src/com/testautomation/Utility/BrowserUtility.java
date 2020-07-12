package com.testautomation.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*Author- Subodh M
 * This class maintains browser invoke methods based on value set in browser-config file
 * 
 */
public class BrowserUtility {
	
	public static WebDriver OpenBrowser(WebDriver driver, String browserName, String url) throws InterruptedException {
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.geckodriver.driver", "usr/local/bin/geckodriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.geckodriver.driver", "usr/local/bin/geckodriver");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
		}
		return null;
	}

}
