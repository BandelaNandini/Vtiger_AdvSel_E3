package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilies.WebDriverUtility;

public class HomePomPage {

	// Declaration

	@FindBy(partialLinkText = "Home")
	private WebElement header;

	@FindBy(linkText = "Organizations")
	private WebElement orgTab;

	@FindBy(linkText = "Contacts")
	private WebElement conTab;

	@FindBy(xpath = "//span[text()=' Administrator']/../../descendant::img")
	private WebElement admin;

	@FindBy(linkText = "Sign Out")
	private WebElement signout;
	// Initialization

	public HomePomPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getOrgTab() {
		orgTab.click();
	}

	public void getConTab() {
		conTab.click();
	}

	public WebElement getAdmin() {
		return admin;
	}

	public void getSignout() {
		signout.click();
	}

	// Bussiness logic for logout
	public void logout(WebDriver driver) {
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.mousehoverOnAnElement(driver, admin);
		signout.click();
	}

}
