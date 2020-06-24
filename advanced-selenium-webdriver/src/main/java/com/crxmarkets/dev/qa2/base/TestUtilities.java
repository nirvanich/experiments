package com.crxmarkets.dev.qa2.base;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestUtilities extends BaseTest {
	
	// STATIC SLEEP
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="files")
	protected static Object[][] files(){
		return new Object[][] {
			{1, "Proposals.Import1.xlsx"},
			{2, "Proposals.Import2.xlsx"}
			
		};
		
		
	}
	
	/** Take screenshot */
	protected void takeScreenshot(String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") 
				+ File.separator + "test-output" 
				+ File.separator + "screenshots"
				+ File.separator + getTodaysDate() 
				+ File.separator + testSuiteName 
				+ File.separator + testName
				+ File.separator + testMethodName 
				+ File.separator + getSystemTime() 
				+ " " + fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Todays date in yyyyMMdd format */
	private static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}

	/** Current time in HHmmssSSS */
	private String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
	}
	
	/** Get logs from browser console */
	protected List<LogEntry> getBrowserLogs() {
		LogEntries log = driver.manage().logs().get("browser");
		List<LogEntry> logList = log.getAll();
		return logList;
	}
	
	public static String getSessionId()
	{
		RestAssured.baseURI = "http://localhost:8085";
		
		String response = given().header("Content-Type", "application/json")
			.body("{ \r\n" + 
					"\"username\": \"nirvanich\", \r\n" + 
					"\"password\": \"Str4nGe-\"\r\n" + 
					"}")
			.when().post("/rest/auth/1/session")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String sessionId = js.get("session.value");
		System.out.println(sessionId);
		return sessionId;			
	}
	
	public static String createIssue(String summary, String description) 
	{
		return "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"RAT\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"" + summary + "\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"        },\r\n" + 
				"        \"reporter\": {\r\n" + 
				"            \"name\": \"nirvanich\"\r\n" + 
				"        },\r\n" + 
				"        \"description\": \"" + description + "\"\r\n" + 
				"    }\r\n" + 
				"}";
	}

	public void createJiraIssue(String summary, String description, String sessionId)
	{
		RestAssured.baseURI = "http://localhost:8085";
		
		String response = given().header("Content-Type", "application/json").cookie("JSESSIONID", sessionId)
			.body(createIssue(summary, description))
			.when().post("/rest/api/2/issue")
			.then().assertThat().statusCode(201)
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("id");
		String key = js.get("key");
		
		
		System.out.println("Issue is successfully created. ID: " + id + ", issueKey is: " + key);
		addComment(key, "Autocomment: technical ID of this issue is: " + id);
	}
	
	public void addComment(String key, String comment)
	{
		RestAssured.baseURI = "http://localhost:8085";
		//String id = "10130";
		given().header("Content-Type", "application/json").pathParam("key", key).cookie("JSESSIONID",getSessionId())
		.body("{\r\n" + 
				"    \"body\": \"" + comment + "\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.when().post("/rest/api/2/issue/{key}/comment")
		.then().assertThat().statusCode(201)
		.extract().response().asString();

		System.out.println("Comment to the Issue with key: " + key + " is succssfully added.");
	}
	
	public String getIssue(String key)
	{
		 RestAssured.baseURI = "http://localhost:8085";
		 
		 String response = given().pathParam("key", key).cookie("JSESSIONID", getSessionId())
				 .queryParam("fields", "comment")
		 .when().get("/rest/api/2/issue/{key}")
		 .then().assertThat().statusCode(200).extract().response().asString();
			
		 return response;
	}
	public String getBugDescription(String expectedURL, String actualURL) {
		return "*Steps to reproduce:*\\n# Login to CRX Portal\\n\\n*Expected result:*\\n* Actual URL is coresponds to HomePage URL [ "+expectedURL+" ]\\n\\n*Actual result:*\\n* Wrong URL is observed - [ "+actualURL+" ]";
	}

}
