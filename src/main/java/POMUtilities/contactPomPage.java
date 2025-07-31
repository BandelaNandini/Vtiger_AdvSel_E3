package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class contactPomPage {

	// Declaration
	@FindBy(linkText = "Contacts")
	private WebElement header;

	@FindBy(xpath = "//img[@title=\"Create Contact...\"]")
	private WebElement Conplusicon;

	// initialization
	public contactPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getConplusicon() {
		Conplusicon.click();
	}

}
