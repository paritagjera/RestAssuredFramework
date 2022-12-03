package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee_Record extends TestBase {
	
	@BeforeClass
	void getSingleEmpData() throws InterruptedException {
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users/"+empID);
		Thread.sleep(2000);
		
	}
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		//Assert.assertEquals(responseBody.contains(4), true);
	}
	
	@Test
	void checkStatusCode()
	{
		//logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	void checkContentType()
	{
		//logger.info("***********  Checking Content Type **********");
		
		String contentType = response.header("Content-Type");
		//logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	

}
