package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPomPage {

	// Declaration
	@FindBy(linkText = "Organizations")
	private WebElement header;

	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement orgplusicon;

	// initialization
	public OrgPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getOrgplusicon() {
		orgplusicon.click();
	}

}
