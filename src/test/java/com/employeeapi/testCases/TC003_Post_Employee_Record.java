package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeesapi.utility.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_Employee_Record extends TestBase{
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	
	@BeforeClass
	void createEmployee() {
		
		RestAssured.baseURI="https://reqres.in/api";
		httpRequest=RestAssured.given();
		
		
		JSONObject requestParam=new JSONObject();
		requestParam.put("name",empName);
		requestParam.put("Salary",empSalary);
		requestParam.put("Age",empAge);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParam.toJSONString());
		response=httpRequest.request(Method.POST,"/users"+empID);
		
		
		
		
	}
	
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}
	

	@Test
	void checkStatusCode()
	{
		//logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 201);
		
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
