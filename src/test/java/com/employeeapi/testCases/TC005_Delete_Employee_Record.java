package com.employeeapi.testCases;

import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC005_Delete_Employee_Record extends TestBase {
	Response response;
	void deleteEmployee() throws InterruptedException {
		RestAssured.baseURI = "https://reqres.in/api/users";
		httpRequest = RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/users");
		
		JsonPath jsonPathEvaluator=response.jsonPath();
		String empID=jsonPathEvaluator.get("[0].id");
		
		response=httpRequest.request(Method.DELETE,empID);
		Thread.sleep(4000);

	}
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		//https://github.com/paritagjera/RestAssuredFramework.git
	}
}
