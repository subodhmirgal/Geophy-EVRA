package com.testautomation.PageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.testautomation.StepDef.TestHooks;

/*Author - Subodh M
 * This is Page Object class for SearchProperty Page
 * Please add here new objects created on EVRA SearchProperty page.
 */

public class SearchPropertyPage {
	private WebDriver driver;

	public SearchPropertyPage(WebDriver driver) {
		this.driver = TestHooks.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "address_input")
	public WebElement EnterAddress;

	@FindBy(how = How.ID, using = "noi")
	public WebElement NetOpIncome;

	@FindBy(how = How.XPATH, using = "//*[@id=\"introjsNumberOfUnits\"]/div/input")
	public WebElement NumOfUnits;

	@FindBy(how = How.XPATH, using = "//*[@id=\"introjsUnitSize\"]/div/input")
	public WebElement AvgUnitSize;

	@FindBy(how = How.XPATH, using = "//*[@id=\"introjsYearOfConstruction\"]/input")
	public WebElement YearOfConstruction;

	@FindBy(how = How.XPATH, using = "//*[@id=\"validationDetails\"]/div[6]/div/div/div/input")
	public WebElement Occupancy;

	@FindBy(how = How.ID, using = "introjsRunValuationButton")
	public WebElement RunEvaluation_btn;

	/*
	 * Method Logic-Below Method search for address of the property provided by
	 * feature.First it check for exact match but if not found then it selects first
	 * address suggested by google search
	 */
	public void EnterAddress(String address) {
		try {
			EnterAddress.sendKeys(address);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			WebElement AddressList = driver.findElement(By.xpath("//div[contains(@class,'pac-item')][1]"));
			AddressList.click();
			/*
			
			 //List<WebElement> AddressList = driver.findElements(By.xpath("//div[contains(@class,'pac-item')]"));
			for (int i = 0; i <= AddressList.size(); i++) {
				if (AddressList.get(i).getText().equals(address)) {
					AddressList.get(i).click();
					break;
				} else {
					AddressList.get(1).click();
				}
			}
*/
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void NetOpIncome(int noi_num) {
		NetOpIncome.sendKeys(String.valueOf(noi_num));
	}

	public void NumOfUnits(int numOfUnits) {
		NumOfUnits.sendKeys(String.valueOf(numOfUnits));
	}

	public void AvgUnitSize(int avgUnitSize) {
		AvgUnitSize.sendKeys(String.valueOf(avgUnitSize));
	}

	public void YearOfConstruction(int yearOfConstruction) {
		YearOfConstruction.sendKeys(String.valueOf(yearOfConstruction));
	}

	public void Occupancy(int occupancy) {
		Occupancy.sendKeys(String.valueOf(occupancy));
	}

	public Boolean RunValuationbtnStatus() {

		String classes = RunEvaluation_btn.getAttribute("class");
		boolean BtnStatus = classes.contains("button--disabled");
		return BtnStatus;
	}

	public void RunValuation_btn() {
		RunEvaluation_btn.click();
	}

	public WebElement verifyAddress(String address) {
		//String locator = "//*[text()[normalize-space(.)=" + "'" + address + "'" + "]]";
	
		String locator="//*[@id=\"page-content\"]/div/section[2]/div[2]/div[2]/h4";
		WebElement addressOnreportPage = driver.findElement(By.xpath(locator));
		return addressOnreportPage;

	}
}
