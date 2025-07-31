package ListenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.Baseclass;

public class Listeners implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite Execution started-Configure the Reports", true);
		String time = new Date().toString().replace(":", "_").replace(" ", "_");
		// Configure the report
		spark = new ExtentSparkReporter("./AdvancedReports/report_" + time + ".html");
		spark.config().setDocumentTitle("Vtiger CRM Prjt");
		spark.config().setReportName("Test Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WINDOWS-11");
		report.setSystemInfo("BROWSER", "CHROME-138");
		test = report.createTest("Report");

		test.log(Status.INFO, "Configuring the report");

	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Suite Execution ended-Backup the Reports", true);
		test.log(Status.INFO, "Report Backup");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution started", true);
		test.log(Status.INFO, "" + testname + "Test Execution started");
//		test = report.createTest("" + testname + "Report");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution Success", true);
		test.log(Status.PASS, "" + testname + "Test Execution Success");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		Reporter.log(testname + "Test Execution Failed", true);
		test.log(Status.FAIL, "" + testname + "Test Execution Failed");
		TakesScreenshot ts = (TakesScreenshot) Baseclass.sdriver;
		String ss = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(ss, "" + testname + time + "");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution Skipped", true);
		test.log(Status.SKIP, "" + testname + "Test Execution Skipped");
	}

}
