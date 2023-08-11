package com.employeeapiUtilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends TestListenerAdapter{


	public ExtentReports extent;
	public ExtentTest test;


	public void onStart(ITestContext testContent) {

		extent=new ExtentReports();
		extent.setSystemInfo("Host name","localHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User","Badri");
	}

	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS,"Test case Passed Is"+result);
	}

	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "TEST CASE FAIL IS"+result.getName());
		test.log(Status.FAIL, "TEST CASE IS FAIL "+result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());

		test.log(Status.SKIP, "TEST CASE SKIPPED IS"+result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
