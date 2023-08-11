package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.employeeapiUtilities.RestUtils;
import com.empolyeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_put_Employee_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empsalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	void createEmployee() throws InterruptedException {
		logger.info("*************Started TC001_PUT_Employee_Record**********************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		//JSONObject is a class that represents a simple JSON, We can add key-value pairs using put method.
		
		JSONObject requestParams= new JSONObject();
		requestParams.put("name",empName);
		requestParams.put("salary", empsalary);
		requestParams.put("age", empAge);
		
		//Add a header started the Request body is a JSON
		httpRequest.header("content-Type","application/json");
		
		//Add the JSON to the body of the Request
		httpRequest.body(requestParams.toJSONString());
		
		response=httpRequest.request(Method.POST,"/create/"+empId);
		
		Thread.sleep(3000);
		
	}
	
	@Test
	void checkResponseBody() {
		
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empsalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);

	}
	@Test
	void checkStatusCode() {
		
	int statusCode=	response.getStatusCode();
	Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		
	String statusLine=	response.getStatusLine();
	Assert.assertEquals(statusLine, "HTTP/1.1 200 ok");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("*********** Finished TC001_PUT_Employees ***************");

	}

}
