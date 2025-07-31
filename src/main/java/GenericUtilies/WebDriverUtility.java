package GenericUtilies;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void maximizetheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitUntilElementIsFound(WebDriver driver, String time) {
		long Timeouts = Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
	}

	public void navigateToAnApplication(WebDriver driver, String url) {
		driver.get(url);
	}

	public void HandleAlertPopupAndClickOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void mousehoverOnAnElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void quitTheBrowser(WebDriver driver) {
		driver.quit();
	}

	public void SelectDD_UsingValue(WebDriver driver, WebElement ddEle, String value) {
		Select s = new Select(ddEle);
		s.selectByValue(value);
	}

	public String fetchParentWindowID(WebDriver driver) {
		String pwid = driver.getWindowHandle();
		return pwid;
	}

	public Set<String> fetchMultipleWindowIDS(WebDriver driver) {
		Set<String> wids = driver.getWindowHandles();
		return wids;
	}

	public void switchToChildWindowUsingTitle(WebDriver driver, String title, Set<String> wids) {
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(title)) {
				break;
			}
		}
	}

	public void switchToChildWindowUsingTitle(WebDriver driver, String title) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(title)) {
				break;
			}
		}
	}

	public void switchToChildWindowUsingUrl(WebDriver driver, String url) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains(url)) {
				break;
			}
		}
	}

	public void switchToParentWindow(WebDriver driver, String pwid) {
		driver.switchTo().window(pwid);
	}

	public void waitTillElementIsVisible(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitTillElementIsClickable(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitTillTitleIsVisible(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void SelectDD_UsingIndex(WebDriver driver, WebElement ddEle, int index) {
		Select s = new Select(ddEle);
		s.selectByIndex(index);
	}

	public void SelectDD_UsingVisibleText(WebDriver driver, WebElement ddEle, String text) {
		Select s = new Select(ddEle);
		s.selectByVisibleText(text);
	}

	public void HandleAlertPopupAndClickCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String HandleAlertPopupAndFetchText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void HandleAlertPopupAndPassTheText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void switchToFrameByIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrameByID_Name(WebDriver driver, String id_name) {
		driver.switchTo().frame(id_name);
	}

	public void switchToFrameByWebelement(WebDriver driver, WebElement frameele) {
		driver.switchTo().frame(frameele);
	}

	public void switchToMainWebpageFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
}
