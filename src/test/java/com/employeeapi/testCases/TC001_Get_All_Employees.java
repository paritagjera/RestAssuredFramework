package com.employeeapi.testCases;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_All_Employees extends TestBase {
	
	

	@BeforeTest
	void getAllEmployees() {

		//logger.info("*************Started getAllEmployees ******************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users");

	}

	@Test
	void checkResponseBody() {

		//logger.info("*************Checking response body*****************");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		//logger.info("response body==>" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkStatusCode() {
		//logger.info("*************Checking Status Code******************");

		int statusCode = response.getStatusCode();
		//logger.info("Status Code is==>" + statusCode);

		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		//logger.info("*************Checking Status Code******************");
		String statusLine=response.getStatusLine();
		//logger.info("Status line is ==>"+statusLine);
		//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		
	}
	
	@Test
	void checkResponseTime() {
		//logger.info("***********  Checking Response Time **********");
		
		long responseTime = response.getTime(); // Getting status Line
		//logger.info("Response Time is ==>" + responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<10000);
	}
	
	@Test
	void checkContentType()
	{
		//logger.info("***********  Checking Content Type **********");
		
		String contentType = response.header("Content-Type");
		//logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkserverType()
	{
		//logger.info("***********  Checking Server Type **********");
		
		String serverType = response.header("Server");
		//logger.info("Server Type is =>" +serverType); 
		Assert.assertEquals(serverType, "cloudflare");
	
	}
	@Test
	void checkcontentEncoding()
	{
		//logger.info("***********  Checking Content Encoding**********");
		
		String contentEncoding = response.header("Content-Encoding");
		//logger.info("Content Encoding is==>" +contentEncoding); 
		//Assert.assertEquals(contentEncoding, "br");
		
		
	}

	//@Test
	void checkContentLenght()
	{
		logger.info("***********  Checking Content Lenght**********");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is==>" +contentLength); 
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
		
	}
	
	//@Test
	void checkCookies()
	{
		logger.info("***********  Checking Cookies **********");

		String cookie = response.getCookie("PHPSESSID");
		//Assert.assertEquals(cookie,"1esuvsfslcmiee2bfrsgnijtg0");
		
	}
	
	@AfterClass
	void tearDown()
	{
		//logger.info("*********  Finished TC001_Get_All_Employees **********");
	}
	
		

}
