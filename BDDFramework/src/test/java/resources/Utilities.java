package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import Cucumber.Automation.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	public static RequestSpecification jiraRequestSpec;
	
	public static RequestSpecification requestSpecification() throws IOException {
		
		if (jiraRequestSpec == null) 
		{
		PrintStream log = new PrintStream(new FileOutputStream("Log.txt"));
		jiraRequestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).addCookie("JSESSIONID", ReUsableMethods.getSessionId()).build();

		return jiraRequestSpec;
		}
		return jiraRequestSpec;
	}

	
	public static String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Gitstuff\\BDDFramework\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(key);
	}
	
	public static String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		return js.getString(key);		
	}
	
	public static boolean getAutomationStatus(String issueKey) throws IOException {
		// TODO Auto-generated method stub

		
		 
		String response = given().spec(requestSpecification()).pathParam("key", issueKey)
				 			.queryParam("fields", "customfield_10200")
				 			.when().get("/rest/api/2/issue/{key}")
				 			.then().assertThat().statusCode(200).extract().response().asString();
		 		 
		 JsonPath js = new JsonPath(response);
		 
		 boolean automated = true;
		 //System.out.println("Start checking automation status of " + issueKey + "..");
		 String automationStatus = js.getString("fields.customfield_10200.value");
							 
				 if (!automationStatus.startsWith("Automated"))
				 {
					 automated = false;
				 }
			 
		 //System.out.println("Automation status: "+ automationStatus);
		 return automated;
	}
	
	public static void transitIssueToInProgress(String issueKey) {
		RestAssured.baseURI = "http://localhost:8085";
		given().header("Content-Type", "application/json").pathParam("key", issueKey)
		.cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.body("{\r\n" + 
					"    \"transition\": {\r\n" + 
					"        \"id\": \"31\"\r\n" + 
					"    }\r\n" + 
					"}")
			.when().post("rest/api/2/issue/{key}/transitions")
			.then().assertThat().statusCode(204);
	}
	
	public static void transitIssueToPassed(String issueKey) {
		RestAssured.baseURI = "http://localhost:8085";
		given().header("Content-Type", "application/json").pathParam("key", issueKey)
		.cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.body("{\r\n" + 
					"    \"transition\": {\r\n" + 
					"        \"id\": \"71\"\r\n" + 
					"    }\r\n" + 
					"}")
			.when().post("rest/api/2/issue/{key}/transitions")
			.then().assertThat().statusCode(204);
	}
	
	public static void transitIssueToFailed(String issueKey) {
		RestAssured.baseURI = "http://localhost:8085";
		given().header("Content-Type", "application/json").pathParam("key", issueKey)
		.cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.body("{\r\n" + 
					"    \"transition\": {\r\n" + 
					"        \"id\": \"81\"\r\n" + 
					"    }\r\n" + 
					"}")
			.when().post("rest/api/2/issue/{key}/transitions")
			.then().assertThat().statusCode(204);
	}
}
