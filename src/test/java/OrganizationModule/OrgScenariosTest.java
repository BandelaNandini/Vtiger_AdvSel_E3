package OrganizationModule;

import java.io.IOException;

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
import POMUtilities.CreateNewOrgPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.OrgInfoPomPage;
import POMUtilities.OrgPomPage;

//Org test scenario
@Listeners(ListenersUtility.Listeners.class)
public class OrgScenariosTest extends Baseclass {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.IRetryAnalyzerUtility.class)
	public void createNewOrgTest() throws InterruptedException, IOException {

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		ListenersUtility.Listeners.test.log(Status.INFO, "Generated random number");

		// Fetch the data from Excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("Org_Data", 1, 2) + randomNum;
		exutil.closeExcelWorkbook();
		ListenersUtility.Listeners.test.log(Status.INFO, "Fetched data from excel");

		WebDriverUtility wutil = new WebDriverUtility();

		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to home page");

		HomePomPage home = new HomePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHeader(), "Home");
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Home page");

		// Identify org link and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to Org tab");

		// Identify create plus icon and click
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new org page");

		// Identify orgname TF and enter orgname
		CreateNewOrgPomPage neworg = new CreateNewOrgPomPage(driver);
		neworg.getOrgnameTF(orgname);

		// Identify save btn and click
		neworg.getSavebtn();
		ListenersUtility.Listeners.test.log(Status.INFO, "Successfully created org");

		// Identify header in org info page and validate
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String actualOrgname = orginfo.getVerifyOrgname();
		Assert.assertEquals(actualOrgname, orgname);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified Org name");

		// Identify org link and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to org tab");

		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the org");

		soft.assertAll();

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.IRetryAnalyzerUtility.class)
	public void createOrg_IndAndType() throws Exception {

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		ListenersUtility.Listeners.test.log(Status.INFO, "Generate random number");

		// Fetch data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("Org_Data", 7, 2) + randomNum;
		String industry = exutil.fetchDataFromExcelFile("Org_Data", 7, 3);
		String type = exutil.fetchDataFromExcelFile("Org_Data", 7, 4);
		exutil.closeExcelWorkbook();
		ListenersUtility.Listeners.test.log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to home page");

		HomePomPage home = new HomePomPage(driver);

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHeader(), "Home");
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified home page");

		// Identify org Tab and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to org tab");

		// Identify create plus icon and click
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new org page");

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
		ListenersUtility.Listeners.test.log(Status.INFO, "Successfully created org with ind and type");

		// Identify header in org info page and validate
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String actualOrgname = orginfo.getVerifyOrgname();
		Assert.assertEquals(actualOrgname, orgname);

		// Validate indutry and type
		String actualIndustry = orginfo.getVerifyIndustry();
		Assert.assertEquals(actualIndustry, industry);

		String actualType = orginfo.getVerifyType();
		Assert.assertEquals(actualType, type);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified orgname,Industry and Type");

		// Identify org link and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to org tab");

		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the org");

		soft.assertAll();

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.IRetryAnalyzerUtility.class)
	public void createOrg_Phno_Test() throws Exception {

		// Fetch Random Number
		JavaUtility jutil = new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		ListenersUtility.Listeners.test.log(Status.INFO, "Generated random number");

		// Fetch data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcelFile("Org_Data", 4, 2) + randomNum;
		String phno = exutil.fetchDataFromExcelFile("Org_Data", 4, 3);
		exutil.closeExcelWorkbook();
		ListenersUtility.Listeners.test.log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to home page");

		HomePomPage home = new HomePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHeader(), "Home");
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified home page");

		// Identify org link and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to Org tab");

		// Identify create plus icon and click
		OrgPomPage org = new OrgPomPage(driver);
		org.getOrgplusicon();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to create new org page");

		// Identify orgname TF and enter orgname and enter phno
		CreateNewOrgPomPage neworg = new CreateNewOrgPomPage(driver);
		neworg.getOrgnameTF(orgname);
		neworg.getPhnoTF(phno);

		// Identify save btn and click
		neworg.getSavebtn();
		ListenersUtility.Listeners.test.log(Status.INFO, "Created org with phno");

		// Identify header in org info page and validate
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String actualOrgname = orginfo.getVerifyOrgname();
		Assert.assertEquals(actualOrgname, orgname);

		// Validate Phno
		String actualPhno = orginfo.getVerifyPhno();
		Assert.assertEquals(actualPhno, phno);
		ListenersUtility.Listeners.test.log(Status.PASS, "Verified org name and phno");

		// Identify org link and click
		home.getOrgTab();
		ListenersUtility.Listeners.test.log(Status.INFO, "Navigated to org tab");

		// Identify del btn and click
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle alert popup
		wutil.HandleAlertPopupAndClickOK(driver);
		ListenersUtility.Listeners.test.log(Status.INFO, "Deleted the org");

		soft.assertAll();

	}

}
