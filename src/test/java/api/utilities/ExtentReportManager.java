package api.utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class ExtentReportManager implements ITestListener {

       // static Date d = new Date();
        //static String fileName = "ExtentReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
      //public  static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
        public ExtentSparkReporter sparkReporter;
        String repName;
        public ExtentReports extent;
        public ExtentTest test;

        public void onStart(ITestContext testContext) {
            // ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
            //testReport.set(test);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName = "Test-Report"+timeStamp+".html";

            sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
            sparkReporter.config().setTheme(Theme.DARK);
            //sparkReporter.config().setDocumentTitle(fileName);
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setReportName("Pet Store User API testing");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("user","pavan");
            extent.setSystemInfo("Env","QA");
        }


        public void onTestSuccess(ITestResult result) {
           /* String methodName=result.getMethod().getMethodName();
            String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " - PASSED"+"</b>";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            testReport.get().pass(markup);*/

            test = extent.createTest(result.getName());
            test.assignCategory(result.getMethod().getGroups());
            test.createNode(result.getName());
            test.log(Status.PASS, "Test Passed");

        }

        public void onTestFailure(ITestResult result) {
            test = extent.createTest(result.getName());
            test.createNode(result.getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.FAIL, "Test Failed");
            test.log(Status.FAIL, result.getThrowable().getMessage());
        }

        public void onTestSkipped(ITestResult result) {
            test = extent.createTest(result.getName());
            test.createNode(result.getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.SKIP, "Test Skipped");
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }

    public void onFinish(ITestContext testContext) {
            extent.flush();
    }

}

