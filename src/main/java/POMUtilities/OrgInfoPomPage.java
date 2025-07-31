package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPomPage {
	
	//Declaration
	@FindBy(xpath="//td[text()='Organization Information']")
	private WebElement header;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement verifyOrgname;
	
	@FindBy(id="dtlview_Phone")
	private WebElement verifyPhno;
	
	@FindBy(id="dtlview_Industry")
	private WebElement verifyIndustry;
	
	@FindBy(id="dtlview_Type")
	private WebElement verifyType;

	//Initialization
	public OrgInfoPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public String getHeader() {
		return header.getText();
	}

	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}

	public String getVerifyPhno() {
		return verifyPhno.getText();
	}

	public String getVerifyIndustry() {
		return verifyIndustry.getText();
	}

	public String getVerifyType() {
		return verifyType.getText();
	}
	
	
	
	

}
