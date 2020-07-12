package com.testautomation.StepDef;

/*Author- Subodh M
 * This Class maintains all Step definition for EVRA feature tests
 * Refer Page Object Package for all page Web elements
 */
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects.LoginPage;
import com.testautomation.PageObjects.SearchPropertyPage;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EVRAStepDef extends ExtentReportListener {

	private WebDriver driver;
	PropertiesFileReader obj = new PropertiesFileReader();

	public EVRAStepDef() {
		this.driver = TestHooks.getDriver();
	}

	@Given("^Open Chrome browser with URL$")
	public void open_Chrome_browser_with_URL() throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "Open Chrome browser with URL");
			Properties properties = obj.getProperty();
			driver = BrowserUtility.OpenBrowser(driver, properties.getProperty("browser.name"),
					properties.getProperty("browser.baseURL"));

			logInfo.pass("Open " + properties.getProperty("browser.name") + " with URL");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@Given("^I navigate to EVRA login page$")
	public void i_navigate_to_EVRA_login_page() throws Throwable {

		ExtentTest logInfo = null;
		try {
			Properties properties = obj.getProperty();
			driver.get(properties.getProperty("browser.baseURL"));
			Thread.sleep(3000);
			logInfo = test.createNode(new GherkinKeyword("When"), "I navigate to EVRA login page");
			logInfo.pass("I Navigated to EVRA Login Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@When("I enter the Email \"(.*)\"$")
	public void i_enter_the_Email(String email) {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("When"), "I enter the Email");
			new LoginPage(driver).Email(email);
			logInfo.pass("Email Id Entered");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@And("I enter the password \"(.*)\"$")
	public void i_enter_the_password(String password) {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("And"), "I enter the password");
			new LoginPage(driver).Password(password);
			logInfo.pass("Password Entered");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^I click on LOG IN button$")
	public void i_click_on_LOG_IN_button() {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("And"), "Click LoginIn Button");
			new LoginPage(driver).NavigateToSearchPage();
			logInfo.pass("I Navigated to Search Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@Then("the following text must be visible \"(.*)\"$")
	public void the_following_text_must_be_visible(String welcomMsg) {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Validate Welcom Message");
			String expectedMessage = welcomMsg;
			String actualMessage = new LoginPage(driver).getWelcomMessage();
			Assert.assertEquals(actualMessage, expectedMessage, "Welcome Message Verification Failed");
			logInfo.pass("Validated Welcome Message Successfully");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@Then("^Error message \"(.*)\" must be displayed$")
	public void errorMessageThereWasAnErrorWithYourEMailOrPasswordPleaseTryEnteringYourLoginCredentialsAgainMustBeDisplayed(
			String erroMsg) throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Validate Password Error Message");
			String expectedMessage = erroMsg;
			WebElement actualMessage = new LoginPage(driver).getPwdErrorMsge(erroMsg);
			Assert.assertEquals(actualMessage.getText(), expectedMessage, "Password Error Message not displayed");
			logInfo.pass("Validated Password Error Message Successfully");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@Then("^the link to \"([^\"]*)\" with text \"([^\"]*)\"  must be visible$")
	public void theLinkToWithTextMustBeVisible(String link, String linkText) throws Throwable {

		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Validate Forgot Password Link");
			String actualLink = new LoginPage(driver).ForgotPwdLink();
			Assert.assertEquals(actualLink, link, "Forgot Password Link Text Validation failed");
			String expectedLinkText = linkText;
			WebElement actualLinkText = new LoginPage(driver).getForgotPwdLinkText(linkText);
			Assert.assertEquals(actualLinkText.getText(), expectedLinkText,
					"Forgot Password Link Text Validation failed");
			logInfo.pass("Forgot Password Link Text Validated Successfully");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@And("^I enter address as \"([^\"]*)\"$")
	public void iEnterAddressAs(String address) throws Throwable {
		ExtentTest logInfo = null;
		try {
			new SearchPropertyPage(driver).EnterAddress(address);
			logInfo = test.createNode(new GherkinKeyword("And"), "I enter address");
			logInfo.pass("I entered address");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^I enter Net operating income as (\\d+)$")
	public void iEnterNetOperatingIncomeAs(int noi) throws Throwable {
		ExtentTest logInfo = null;
		try {
			new SearchPropertyPage(driver).NetOpIncome(noi);
			logInfo = test.createNode(new GherkinKeyword("And"), "I enter Net operating income");
			logInfo.pass("I entered Net operating income");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^I enter Number of units as (\\d+)$")
	public void iEnterNumberOfUnitsAs(int UnitNum) throws Throwable {
		ExtentTest logInfo = null;
		try {
			new SearchPropertyPage(driver).NumOfUnits(UnitNum);
			logInfo = test.createNode(new GherkinKeyword("And"), "I enter Number of units");
			logInfo.pass("I entered Number of units");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@And("^I enter Year of Construction as (\\d+)$")
	public void iEnterYearOfConstructionAs(int year) throws Throwable {
		ExtentTest logInfo = null;
		try {
			new SearchPropertyPage(driver).YearOfConstruction(year);
			logInfo = test.createNode(new GherkinKeyword("And"), "I enter Year of Construction");
			logInfo.pass("I enter Year of Construction");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^I enter Occupancy as (\\d+)$")
	public void iEnterOccupancyAs(int occupancy) throws Throwable {
		ExtentTest logInfo = null;
		try {
			new SearchPropertyPage(driver).Occupancy(occupancy);
			logInfo = test.createNode(new GherkinKeyword("And"), "I enter Occupancy %");
			logInfo.pass("I enter Occupancy %");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^Run Valuation button must be enabled$")
	public void runValuationButtonMustBeEnabled() throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Run Valuation Button must be Enabled");
			Boolean actualStatus = new SearchPropertyPage(driver).RunValuationbtnStatus();
			Assert.assertFalse(actualStatus);
			logInfo.pass("Run Valuation Button must be Enabled");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^I click on the button RUN VALUATION$")
	public void iClickOnTheButtonRUNVALUATION() throws Throwable {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("And"), "Click RUN EVALUATION Button");
			new SearchPropertyPage(driver).RunValuation_btn();
			logInfo.pass("Click RUN EVALUATION Button");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@Then("^Property \"([^\"]*)\" must be displayed on report page$")
	public void propertyMustBeDisplayedOnReportPage(String address) throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Property must be displayed on report page");
			String expectedAddress = address;
			WebElement actualAddress = new SearchPropertyPage(driver).verifyAddress(address);
			String listOfWords = actualAddress.getText();
			String[] b = listOfWords.split("\\s+");
			String lastWord = b[b.length - 1];
			String addressOnreportPage = listOfWords.substring(0, listOfWords.indexOf(lastWord)).trim(); // Added
			addressOnreportPage = addressOnreportPage.substring(0, addressOnreportPage.length() - 1);
			Assert.assertEquals(addressOnreportPage.toLowerCase(), expectedAddress.toLowerCase(),
					"Address Verfied on Report Page");

			logInfo.pass("Address Verfied on Report Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@Then("^Run Valuation search in progress$")
	public void runValuationSearchInProgress() throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Property Valuation is in Progress");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"downloadCSVLink\"]/span")));
			logInfo.pass("Property Valuation is in Progress");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}

	}

	@And("^Run Valuation button must be disabled$")
	public void runValuationButtonMustBeDisabled() throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Run Valuation Button must be Disabled");
			Boolean actualStatus = new SearchPropertyPage(driver).RunValuationbtnStatus();
			Assert.assertTrue(actualStatus);
			logInfo.pass("Run Valuation Button must be Disabled");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

	@And("^I click on \"([^\"]*)\" button$")
	public void iClickOnButton(String btntext) throws Throwable {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "I click on " + btntext);
			WebElement clickLink = new LoginPage(driver).clickHeaderLinks(btntext);
			clickLink.click();
			logInfo.pass("I click on" + btntext);
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}
	}

}
