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
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.contactPomPage;

public class CreateContactWithSupportDateTest {

	@Test
	public void createCont_suppDate() throws Exception {
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
		String conname = exutil.fetchDataFromExcelFile("Cont_Data", 4, 2) + randomNum;
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
		// Identify contact tab and click
		home.getConTab();

		// Identify create plus icon and click
		contactPomPage contact = new contactPomPage(driver);
		contact.getConplusicon();

		// Identify conname TF and enter conname
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastnameTF(conname);

		// Identify supp strt date TF
		WebElement strtDateTF = newcon.getStrtDateTF();
		strtDateTF.clear();

		// Fetching the current date

		String startDate = jutil.getSystemCurrentDate();
		strtDateTF.sendKeys(startDate);
		System.out.println(startDate);

		// Identify supp end date TF
		WebElement enddateTF = newcon.getEndDateTF();
		enddateTF.clear();

		// Fetch the date after 30 days
		String enddate = jutil.getDateAfterSpecifiedDays(30);
		enddateTF.sendKeys(enddate);
		System.out.println(enddate);

		// Identify save btn and click
		newcon.getSavebtn();
		// Identify header in contact info page and validate
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String actualLastname = coninfo.getVerifyLastname();

		if (actualLastname.contains(conname)) {
			System.out.println("Create cont with supp date verified lastname test pass");
		} else {
			System.out.println("Create cont with supp date verified lastname test fail");

		}

		// Validate supp start date
		String actualStrtDate = coninfo.getVerifyStrtDate();
		if (actualStrtDate.contains(startDate)) {
			System.out.println("Create cont with supp date verified strt date test pass");
		} else {
			System.out.println("Create cont with supp date verified strt date test fail");
		}

		// Validate supp end date
		String actualEndDate = coninfo.getVerifyEndDate();
		if (actualEndDate.contains(enddate)) {
			System.out.println("Create cont with supp date verified end date test pass");
		} else {
			System.out.println("Create cont with supp date verified end date test fail");
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
//		// Mousehover on to admin icon
//		WebElement admin = home.getAdmin();
//
//		wutil.mousehoverOnAnElement(driver, admin);
//		// Click on signout button
//		home.getSignout();

		// Logout
		home.logout(driver);
		// Close the browser
		wutil.quitTheBrowser(driver);

	}

}
