package ContactModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import GenericUtilies.ExcelFileUtility;
import GenericUtilies.JavaUtility;
import GenericUtilies.PropertyFileUtility;
import GenericUtilies.WebDriverUtility;
import POMUtilities.ContactInfoPomPage;
import POMUtilities.CreateNewContactPomPage;
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;
import POMUtilities.contactPomPage;

public class CreateContWithOrgTest {

	@Test
	public void createContwithOrg() throws Exception {

		// Fetch the data from properties file
		PropertyFileUtility prop = new PropertyFileUtility();
		String Browser = prop.FetchDataFromPropFile("browser");
		String URL = prop.FetchDataFromPropFile("url");
		String UN = prop.FetchDataFromPropFile("username");
		String PSWD = prop.FetchDataFromPropFile("password");
		String Time = prop.FetchDataFromPropFile("timeouts");

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();

		// Fetch data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("Cont_Data", 7, 2) + randomNum;
		String conname = exutil.fetchDataFromExcelFile("Cont_Data", 7, 3) + randomNum;
		exutil.closeExcelWorkbook();
		// Launch the Browser
		WebDriver driver = new ChromeDriver();
		WebDriverUtility wutil = new WebDriverUtility();
		// Maximize the window
		wutil.maximizetheWindow(driver);
		// IMplicit wait
		wutil.waitUntilElementIsFound(driver, Time);

		// Navigate to an appln
		wutil.navigateToAnApplication(driver, URL);

		// Login to an application
		LoginPomPage login = new LoginPomPage(driver);
		login.login(UN, PSWD);

		HomePomPage home = new HomePomPage(driver);

		// Identify org link and click
		home.getOrgTab();
		// Identify create plus icon and click
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgplusicon();

		// Identify orgname TF and enter orgname
		CreateNewOrgPomPage neworg = new CreateNewOrgPomPage(driver);
		neworg.getOrgnameTF(orgname);

		// Identify save btn and click
		neworg.getSavebtn();
		// Identify header in org info page and validate
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String actualOrgname = orginfo.getVerifyOrgname();

		if (actualOrgname.contains(orgname)) {
			System.out.println("Create org test pass");
		} else {
			System.out.println("Create org test fail");

		}

		// Identify contact tab and click
		home.getConTab();
		// Identify create plus icon and click
		contactPomPage contact = new contactPomPage(driver);
		contact.getConplusicon();

		// Identify conname TF and enter conname
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastnameTF(conname);

		// Fetch the parent window id
		String pwid = wutil.fetchParentWindowID(driver);

		// Identify org plus icon and click
		newcon.getOrgPlusIcon();
		// Fetch all the window ids
		wutil.switchToChildWindowUsingUrl(driver, "module=Accounts&action");

		newcon.getOrgSearchTF(orgname);
		newcon.getOrgSearchBtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		wutil.switchToParentWindow(driver, pwid);

		// Identify save btn and click
		newcon.getSavebtn();
		// Identify header in contact info page and validate
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String actualLastName = coninfo.getVerifyLastname();
		if (actualLastName.contains(conname)) {
			System.out.println("Create contact with org verified lastname test pass");
		} else {
			System.out.println("Create contact with org verified lastname test fail");

		}

		// Validate org name in contact info page
		String actualorgname = coninfo.getVerifyOrgName();
		if (actualorgname.contains(orgname)) {
			System.out.println("Create contact with org verified orgname test pass");
		} else {
			System.out.println("Create contact with org verified orgname test fail");
		}

		// Identify contact tab and click
		home.getConTab();
		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + conname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		// Identify org link and click
		home.getOrgTab();
		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);

//		// Mousehover on to admin icon
//		WebElement admin = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//
//		wutil.mousehoverOnAnElement(driver, admin);
//
//		// Click on signout button
//		driver.findElement(By.linkText("Sign Out")).click();

		// Logout
		home.logout(driver);
		// Close the browser
		wutil.quitTheBrowser(driver);
	}

}
