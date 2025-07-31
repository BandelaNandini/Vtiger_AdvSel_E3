package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPomPage {

	// Declaration
	@FindBy(xpath = "//td[text()='Contact Information']")
	private WebElement header;

	@FindBy(id = "dtlview_Last Name")
	private WebElement verifyLastname;

	@FindBy(xpath = "//td[@id=\"mouseArea_Organization Name\"]/a[contains(@href,'Accounts')]")
	private WebElement verifyOrgName;

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement verifyStrtDate;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement verifyEndDate;

	// Initialization
	public ContactInfoPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public String getVerifyLastname() {
		return verifyLastname.getText();
	}

	public String getVerifyOrgName() {
		return verifyOrgName.getText();
	}

	public String getVerifyStrtDate() {
		return verifyStrtDate.getText();
	}

	public String getVerifyEndDate() {
		return verifyEndDate.getText();
	}

}
