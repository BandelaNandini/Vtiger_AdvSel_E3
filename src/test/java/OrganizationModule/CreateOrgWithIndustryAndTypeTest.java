package OrganizationModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import GenericUtilies.ExcelFileUtility;
import GenericUtilies.JavaUtility;
import GenericUtilies.PropertyFileUtility;
import GenericUtilies.WebDriverUtility;
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;

public class CreateOrgWithIndustryAndTypeTest {

	@Test
	public void createOrg_IndAndType() throws Exception {
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
		String orgname = exutil.fetchDataFromExcelFile("Org_Data", 7, 2) + randomNum;
		String industry = exutil.fetchDataFromExcelFile("Org_Data", 7, 3);
		String type = exutil.fetchDataFromExcelFile("Org_Data", 7, 4);
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

		// Identify org Tab and click
		home.getOrgTab();
		// Identify create plus icon and click
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgplusicon();
		// Identify orgname TF and enter orgname and enter phno
		CreateNewOrgPomPage neworg = new CreateNewOrgPomPage(driver);
		neworg.getOrgnameTF(orgname);

		// Handle Industry DD
		WebElement ind_dd = neworg.getIndustryDD();
		wutil.SelectDD_UsingValue(driver, ind_dd, industry);

		// Identify Type DD
		WebElement type_dd = neworg.getTypeDD();
		wutil.SelectDD_UsingValue(driver, type_dd, type);

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

		// Validate indutry and type
		String actualIndustry = orginfo.getVerifyIndustry();

		if (actualIndustry.contains(industry)) {
			System.out.println("CreateOrgWithIndAndType Verified Industry pass");
		} else {
			System.out.println("CreateOrgWithIndAndType Verified Industry fail");

		}

		String actualType = orginfo.getVerifyType();
		if (actualType.contains(type)) {
			System.out.println("CreateOrgWithIndAndType Verified type pass");
		} else {
			System.out.println("CreateOrgWithIndAndType Verified type fail");

		}
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
//		// Click on signout button
//		driver.findElement(By.linkText("Sign Out")).click();

		// Logout
		home.logout(driver);
		// Close the browser
		wutil.quitTheBrowser(driver);

	}
}
