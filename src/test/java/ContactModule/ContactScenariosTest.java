package ContactModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilies.ExcelFileUtility;
import GenericUtilies.JavaUtility;
import GenericUtilies.WebDriverUtility;
import POMUtilities.ContactInfoPomPage;
import POMUtilities.CreateNewContactPomPage;
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;
import POMUtilities.contactPomPage;

@Listeners(ListenersUtility.Listeners.class)
public class ContactScenariosTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.IRetryAnalyzerUtility.class)
	public void createcontTest() throws Exception {

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		ListenersUtility.Listeners.test.log(Status.INFO, "Generated Random Number");

		// Fetch data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String conname = exutil.fetchDataFromExcelFile("Cont_Data", 1, 2) + randomNum;
		exutil.closeExcelWorkbook();
		ListenersUtility.Listeners.test.log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to Home Page");

		HomePomPage home = new HomePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHeader(), "Home");
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Home Page");

		// Identify contact tab and click
		home.getConTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to contact tab");

		// Identify create plus icon and click
		contactPomPage contact = new contactPomPage(driver);
		contact.getConplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new contact page");

		// Identify conname TF and enter conname
		CreateNewContactPomPage newcont = new CreateNewContactPomPage(driver);
		newcont.getLastnameTF(conname);

		// Identify save btn and click
		newcont.getSavebtn();
		ListenersUtility.Listeners.test.log(Status.INFO, "successfully created contact");

		// Identify header in contact info page and validate
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String actualLastname = coninfo.getVerifyLastname();

		Assert.assertEquals(actualLastname, conname);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified contact name");

		// Identify contact tab and click
		home.getConTab();
		ListenersUtility.Listeners.test.log(Status.PASS, "Navigated to contact tab");

		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + conname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the contact");

		soft.assertAll();

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.IRetryAnalyzerUtility.class)
	public void createCont_suppDate() throws Exception {

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		ListenersUtility.Listeners.test.log(Status.INFO, "Generated Random Number");

		// Fetch data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String conname = exutil.fetchDataFromExcelFile("Cont_Data", 4, 2) + randomNum;
		exutil.closeExcelWorkbook();
		ListenersUtility.Listeners.test.log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to Home page");

		HomePomPage home = new HomePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHeader(), "Home");
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Home Page");

		// Identify contact tab and click
		home.getConTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to contact tab");

		// Identify create plus icon and click
		contactPomPage contact = new contactPomPage(driver);
		contact.getConplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new contact page");

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
		ListenersUtility.Listeners.test.log(Status.INFO, "successfully Created contact with support date");

		// Identify header in contact info page and validate
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String actualLastname = coninfo.getVerifyLastname();
		Assert.assertEquals(actualLastname, conname);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Contact name");

		// Validate supp start date
		String actualStrtDate = coninfo.getVerifyStrtDate();
		Assert.assertEquals(actualStrtDate, startDate);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Start date");

		// Validate supp end date
		String actualEndDate = coninfo.getVerifyEndDate();
		Assert.assertEquals(actualEndDate, enddate);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified End date");

		// Identify contact tab and click
		home.getConTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to contact tab");

		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + conname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the contact");

		soft.assertAll();

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.IRetryAnalyzerUtility.class)
	public void createContwithOrg() throws Exception {

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		ListenersUtility.Listeners.test.log(Status.INFO, "Generated ramdom number");

		// Fetch data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("Cont_Data", 7, 2) + randomNum;
		String conname = exutil.fetchDataFromExcelFile("Cont_Data", 7, 3) + randomNum;
		exutil.closeExcelWorkbook();
		ListenersUtility.Listeners.test.log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to Home page");

		HomePomPage home = new HomePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHeader(), "Home");
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Home page");

		// Identify org link and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to org page");

		// Identify create plus icon and click
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new org page");

		// Identify orgname TF and enter orgname
		CreateNewOrgPomPage neworg = new CreateNewOrgPomPage(driver);
		neworg.getOrgnameTF(orgname);

		// Identify save btn and click
		neworg.getSavebtn();
		ListenersUtility.Listeners.test.log(Status.INFO, "Successfully created org name for contact");

		// Identify header in org info page and validate
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String actualOrgname_orginfo = orginfo.getVerifyOrgname();

		Assert.assertEquals(actualOrgname_orginfo, orgname);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified org name");

		// Identify contact tab and click
		home.getConTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to contact tab");

		// Identify create plus icon and click
		contactPomPage contact = new contactPomPage(driver);
		contact.getConplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new contact page");

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
		ListenersUtility.Listeners.test.log(Status.INFO, "Successfully created contact with org");

		// Identify header in contact info page and validate
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String actualLastName = coninfo.getVerifyLastname();
		Assert.assertEquals(actualLastName, conname);

		// Validate org name in contact info page
		String actualorgname_coninfo = coninfo.getVerifyOrgName();
		Assert.assertEquals(actualorgname_coninfo, orgname);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Contact name and org name");

		// Identify contact tab and click
		home.getConTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to contact tab");

		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + conname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the org");

		// Identify org link and click
		home.getOrgTab();
		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the contact created with org name");

		soft.assertAll();

	}

}
