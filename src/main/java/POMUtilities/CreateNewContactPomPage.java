package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPomPage {
	// Declaration
	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement header;

	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	@FindBy(name = "lastname")
	private WebElement lastnameTF;

	@FindBy(name = "support_start_date")
	private WebElement strtDateTF;

	@FindBy(name = "support_end_date")
	private WebElement endDateTF;

	@FindBy(xpath = "//img[contains(@onclick,'specific_contact_account_address')]")
	private WebElement orgPlusIcon;

	@FindBy(id = "search_txt")
	private WebElement orgSearchTF;

	@FindBy(name = "search")
	private WebElement orgSearchBtn;

	// Initialize
	public CreateNewContactPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getSavebtn() {
		savebtn.click();
	}

	public void getLastnameTF(String lastname) {
		lastnameTF.sendKeys(lastname);
	}

	public WebElement getStrtDateTF() {
		return strtDateTF;
	}

	public WebElement getEndDateTF() {
		return endDateTF;
	}

	public void getOrgPlusIcon() {
		orgPlusIcon.click();
	}

	public void getOrgSearchTF(String orgname) {
		orgSearchTF.sendKeys(orgname);
	}

	public void getOrgSearchBtn() {
		orgSearchBtn.click();
	}

}
