package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {

	// Declaration
	@FindBy(linkText = "vtiger")
	private WebElement header;

	@FindBy(name = "user_name")
	private WebElement usernameTF;

	@FindBy(name = "user_password")
	private WebElement passwordTF;

	@FindBy(id = "submitButton")
	private WebElement login_btn;

	// Initialization
	public LoginPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getUsernameTF(String user) {
		usernameTF.sendKeys(user);
	}

	public void getPasswordTF(String password) {
		passwordTF.sendKeys(password);
	}

	public void getLogin_btn() {
		login_btn.click();
	}

	// Bussiness logic for login
	public void login(String user, String password) {
		usernameTF.sendKeys(user);
		passwordTF.sendKeys(password);
		login_btn.click();
	}

}
