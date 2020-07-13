package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.StepDef.TestHooks;

/*Author - Subodh M
 * This is Page Object class for Report Page
 * Please add here new objects created on EVRA Report page.
 */

public class ReportPage {
	private WebDriver driver;
	
	public ReportPage(WebDriver driver) {
		this.driver = TestHooks.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//table/tr[1]/td[2]")
	public WebElement VerifyNumOfUnits;

	@FindBy(how = How.XPATH, using = "//table/tr[2]/td[2]")
	public WebElement VerifyYearOfConstruction;

	@FindBy(how = How.XPATH, using = "//table/tr[3]/td[2]")
	public WebElement VerifyNoi;

	@FindBy(how = How.XPATH, using = "//table/tr[5]/td[2]")
	public WebElement VerifyOccupancy;

	public WebElement VerifyNumOfUnits() {

		return VerifyNumOfUnits;

	}

	public WebElement VerifyYearOfConstruction() {

		return VerifyYearOfConstruction;

	}

	public WebElement VerifyNoi() {

		return VerifyNoi;

	}

	public WebElement VerifyOccupancy() {

		return VerifyOccupancy;

	}
}
