package com.testautomation.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.StepDef.TestHooks;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = TestHooks.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "email")
	public WebElement Email;

	@FindBy(how = How.ID, using = "password")
	public WebElement Password;

	@FindBy(how = How.CSS, using = "#form_login > div.flex.flex-col.float-right.items-end.my-8 > button")
	public WebElement LoginButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/section/header/div/nav/ul[2]/li/span")
	public WebElement WelcomMessage;

	@FindBy(how = How.XPATH, using = "//*[@id=\"form_login\"]/div[3]/div[2]/a")
	public WebElement ForgotPwdLink;

	public void Email(String email) {
		Email.sendKeys(email);
	}

	public void Password(String password) {
		Password.sendKeys(password);
	}

	public void NavigateToSearchPage() {
		LoginButton.click();
	}

	public String getWelcomMessage() {
		return WelcomMessage.getText();

	}

	public WebElement getPwdErrorMsge(String errMsg) {
		String locator = "//*[text()[normalize-space(.)=" + "'" + errMsg + "'" + "]]";
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		WebElement PwdErrorMsg = driver.findElement(By.xpath(locator));
		return PwdErrorMsg;

	}

	public String ForgotPwdLink() {
		return ForgotPwdLink.getAttribute("href");
	}

	public WebElement getForgotPwdLinkText(String linkText) {
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		WebElement ForgotPwdLink = driver.findElement(By.linkText(linkText));
		if (ForgotPwdLink.isDisplayed()) {
			return ForgotPwdLink;
		} else {
			return null;
		}

	}

	/*
	 * This is common method written to click links on upper side of the Page Pass
	 * link text as input e.g Logout,Search,History,Account
	 */
	public WebElement clickHeaderLinks(String btnText) {

		String locator = "//div[@class='bg-navy p-4 relative']/nav/ul/li/a[contains(.,'" + btnText + "')]";
		WebElement element = driver.findElement(By.xpath(locator));
		return element;

	}

}
