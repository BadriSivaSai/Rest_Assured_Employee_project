package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.empolyeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record  extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	void deleteEmployee() throws InterruptedException {
		logger.info("********* Started TC005_Delete_Employee_Record ********* ");

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();

		response=httpRequest.request(Method.GET,"/employees");

		//First get the JSONPath instance from the Response interface
		JsonPath jsonPathEvaluator=response.jsonPath();

		//capture Id
		String empID=jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/delete/"+empID); //pass ID to delete Record

		Thread.sleep(3);


	}

	@Test
	void checkResponseBody() {

		String responseBody=	response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Recored"), true);
	}

	@AfterClass
	void tearDown() {
		logger.info("*********** Finished TC001_POST_Employees ***************");
	}
}
