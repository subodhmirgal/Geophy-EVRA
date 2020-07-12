package com.testautomation.StepDef;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

public class TestHooks extends ExtentReportListener {
	private static WebDriver driver;
	PropertiesFileReader obj = new PropertiesFileReader();

	/*
	 * This method launch browser Get Feature,Scenario Name for Reporting
	 */

	@Before
	public void SetUpForLogin(Scenario scenario) {
		try {
			Properties properties = obj.getProperty();
			driver = BrowserUtility.OpenBrowser(driver, properties.getProperty("browser.name"),
					properties.getProperty("browser.baseURL"));

			String getInput = scenario.getId().split(";")[0];
			String getFeatureNameOne = getInput.substring(getInput.lastIndexOf("/") + 1);
			String[] getFeatureFileNameTwo = getFeatureNameOne.split(":");
			String getFeatureName = getFeatureFileNameTwo[0];
			System.out.println(getFeatureName);
			test = extent.createTest(getFeatureName);
			String scenarioName = scenario.getName();
			test = test.createNode(scenarioName);

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	@After
	public void teardown(Scenario scenario) {
		try {
			System.out.println("Completed execution for the scenario :" + scenario.getName());
			driver.quit();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

}
