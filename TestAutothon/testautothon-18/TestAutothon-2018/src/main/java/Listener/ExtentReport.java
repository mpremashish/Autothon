package Listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import Base.DriverThreadLocal;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport implements ITestListener {
    private static Logger log = Logger.getLogger(ExtentReport.class.getSimpleName());
    protected static ExtentReports reports;
    protected static ExtentTest test;

    public void onTestStart(ITestResult result) {
        log.info("test start : "+ result.getTestContext().getCurrentXmlTest().getName());
        test = reports.startTest(result.getTestContext().getCurrentXmlTest().getName());
        test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
    }

    public void onTestSuccess(ITestResult result) {
        log.info("test success : "+ result.getTestContext().getCurrentXmlTest().getName());
        test.log(LogStatus.PASS, result.getTestContext().getCurrentXmlTest().getName()+":"+result.getMethod().getDescription()+ ": test is passed");
    }

    public void onTestFailure(ITestResult result) {
        log.info("test failure : "+ result.getTestContext().getCurrentXmlTest().getName());
        test.log(LogStatus.FAIL, result.getMethod().getMethodName()+ " test is failed");
        TakesScreenshot ts = (TakesScreenshot) DriverThreadLocal.getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("./raw_data/" + result.getMethod().getMethodName() + ".png"));
            String file = test.addScreenCapture("./raw_data/" + result.getMethod().getMethodName() + ".png");
            test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
            test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed",
                    result.getThrowable().getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        log.info("test skipped :" + result.getTestContext().getCurrentXmlTest().getName()+":"+result.getMethod().getDescription());
        test.log(LogStatus.SKIP, result.getTestContext().getCurrentXmlTest().getName()+":"+result.getMethod().getDescription()+ "test is skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("on test success within percentage");
    }

    public void onStart(ITestContext context) {
        log.info(" Initializing : "+ context.getCurrentXmlTest().getName());
        reports = new ExtentReports("./Reports/" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html", true);
    }

    public void onFinish(ITestContext context) {
        System.out.println("on finish");
        reports.endTest(test);
        reports.flush();
    }
}
