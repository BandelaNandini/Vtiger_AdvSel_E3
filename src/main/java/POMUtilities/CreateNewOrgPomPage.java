package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrgPomPage {

	// Declaration
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement header;

	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	@FindBy(name = "accountname")
	private WebElement orgnameTF;

	@FindBy(id = "phone")
	private WebElement phnoTF;

	@FindBy(name = "industry")
	private WebElement industryDD;

	@FindBy(name = "accounttype")
	private WebElement typeDD;

	// Initialize
	public CreateNewOrgPomPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getSavebtn() {
		savebtn.click();
	}

	public void getOrgnameTF(String orgname) {
		orgnameTF.sendKeys(orgname);
	}

	public void getPhnoTF(String phno) {
		phnoTF.sendKeys(phno);
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}

}
