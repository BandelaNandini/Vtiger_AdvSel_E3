package BaseclassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import GenericUtilies.DatabaseUtility;
import GenericUtilies.PropertyFileUtility;
import GenericUtilies.WebDriverUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class Baseclass {
	DatabaseUtility dbutil = new DatabaseUtility();
	PropertyFileUtility prop = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite
	public void getConnectionToDB() throws Exception {
		dbutil.getDBConnection();
		Reporter.log("Connected with the Database");
	}

	@BeforeTest
	public void conParallelExe() {

		Reporter.log("Configure parallel Execution", true);
	}

//	@Parameters("browser")
	@BeforeClass
	public void launchTheBrowser() throws IOException {
		String BROWSER = prop.FetchDataFromPropFile("browser");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		Reporter.log("Launched the Browser", true);
		sdriver = driver;
	}

	@BeforeMethod
	public void login() throws IOException {
		String UN = prop.FetchDataFromPropFile("username");
		String PSWD = prop.FetchDataFromPropFile("password");
		String URL = prop.FetchDataFromPropFile("url");
		String Time = prop.FetchDataFromPropFile("timeouts");

		wutil.maximizetheWindow(driver);
		wutil.waitUntilElementIsFound(driver, Time);
		wutil.navigateToAnApplication(driver, URL);
		LoginPomPage login = new LoginPomPage(driver);
		login.login(UN, PSWD);
		Reporter.log("Logged in to the application", true);

	}

	@AfterMethod
	public void logout() {
		HomePomPage home = new HomePomPage(driver);
		home.logout(driver);
		Reporter.log("Logged out of the application", true);

	}

	@AfterClass
	public void closeTheBrowser() {
		wutil.quitTheBrowser(driver);
		Reporter.log("closed the browser", true);

	}

	@AfterTest
	public void closeParalelExe() {
		Reporter.log("close Configuration of parallel Execution", true);

	}

	@AfterSuite
	public void closeConnectionWithDB() throws SQLException {
		dbutil.closeDatabaseConnection();
		Reporter.log("closed Connection with the Database", true);

	}
}
