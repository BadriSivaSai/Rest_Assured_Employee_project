package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.empolyeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee_Record extends TestBase{

	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("***********started TC001_Get_All_Employees***************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httRequest=RestAssured.given();
		response=httRequest.request(Method.GET,"/employees/"+empId);

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("***********checkResponseBody***************");


		String responseBody=response.getBody().asString();
		logger.info("Response Body=>"+responseBody);
		Assert.assertEquals(responseBody.contains(empId),true);
	}

	@Test
	void checkResponseTime() {
		logger.info("***********checkResponseTime***************");

		long responseTime=response.getTime();
		logger.info("Response Time is =>"+responseTime);
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime<2000);
	}

	@Test
	void checkStatusline() {

		logger.info("***********checkStatusline***************");

		String statusLine=response.getStatusLine();
		logger.info("statusLine is =>"+statusLine);
		Assert.assertEquals(statusLine, "Http/1.1 200 ok");

	}

	@Test
	void checkContentType() {

		logger.info("***********checkStatusline***************");

		String contentType=response.header("Content-Type");
		logger.info("contentType is =>"+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");

	}

	@Test
	void checkserverType() {
		logger.info("***********checkserverType***************");

		String checkserverType=response.header("Server");
		logger.info("checkserverType is =>"+checkserverType);
		Assert.assertEquals(checkserverType, "nginx/1.14.1");

	}

	@Test
	void checkcontetlength() {
	String contentLength=	response.header("Content-Length");
	Assert.assertTrue(Integer.parseInt(contentLength)<1500);
			}
	@AfterClass
	void tearDown() {
		logger.info("*********** Finished TC001_Get_All_Employees ***************");

	}

}
