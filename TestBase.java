package com.empolyeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httRequest;
	public static Response response;
	public String empId="51838";//Hard coded - input for Get details of single Employee & upload employee
	
	public Logger logger;
	
	@BeforeClass
	public void  setUp() {
		
		logger=Logger.getLogger("EmployeeRestAPI");//added Logger
		PropertyConfigurator.configure("Log4j.properties"); //added Logger
		logger.setLevel(Level.DEBUG);
	}
}
