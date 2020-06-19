import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class JiraTest {

		public String id;
		public String key;
		public List<String> ListOfIds = new ArrayList<String>();
		
		//@Test(dataProvider = "BooksData")
		public void createIssue(String summary, String description)
		{
			RestAssured.baseURI = "http://localhost:8085";
			
			String response = given().header("Content-Type", "application/json").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				.body(payload.createIssue(summary, description))
				.when().post("/rest/api/2/issue")
				.then().assertThat().statusCode(201)
				.extract().response().asString();
			
			JsonPath js = ReUsableMethods.rawToJson(response);
			id = js.get("id");
			key = js.get("key");
			ListOfIds.add(key);
			
			System.out.println("Issue is successfully created. ID: " + id + ", issueKey is: " + key);
			addComment(key, "Autocomment: id of this issue is: " + id);
			System.out.println(getIssue(key));
		}
		
		
		public void addComment(String key, String comment)
		{
			RestAssured.baseURI = "http://localhost:8085";
			//String id = "10130";
			given().header("Content-Type", "application/json").pathParam("key", key).cookie("JSESSIONID", ReUsableMethods.getSessionId())
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
		
		//@Test(dataProvider = "ListOfIds")
		public void deleteIssue(String key)
		{
			RestAssured.baseURI = "http://localhost:8085";
			
			given().contentType(ContentType.JSON).pathParam("key", key).cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.when().delete("/rest/api/2/issue/{key}")
			.then().assertThat().statusCode(204)
			.extract().response().asString();

			System.out.println("Issue succssfully deleted. key: " + key);
		}
		
		//Add attachment
		public void addAttachment(String key)
		{
			RestAssured.baseURI = "http://localhost:8085";
			
			given().header("X-Atlassian-Token", "no-check").pathParam("key", key).cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.header("Content-Type", "multipart/form-data")
			.multiPart("file", new File("C:\\Gitstuff\\RestAssured\\test-output\\111809635 HomePage opened.png"))
			.when().post("/rest/api/2/issue/{key}/attachments")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
			
			System.out.println("Attachment to the Issue with key: " + key + " is succssfully added.");

		}
		//get Issue
		public String getIssue(String key)
		{
			 RestAssured.baseURI = "http://localhost:8085";
			 
			 String response = given().pathParam("key", key).cookie("JSESSIONID", ReUsableMethods.getSessionId())
					 .queryParam("fields", "comment")
			 .when().get("/rest/api/2/issue/{key}")
			 .then().assertThat().statusCode(200).extract().response().asString();
				
			 return response;
		}
		
		//get automation status
		public static boolean getAutomationStatus(String issueKey) {
			// TODO Auto-generated method stub

			RestAssured.baseURI = "http://localhost:8085";
			 
			 String response = given().pathParam("key", issueKey).cookie("JSESSIONID", ReUsableMethods.getSessionId())
					 			.queryParam("fields", "customfield_10200")
					 			.when().get("/rest/api/2/issue/{key}")
					 			.then().assertThat().statusCode(200).extract().response().asString();
			 		 
			 JsonPath js = new JsonPath(response);
			 
			 boolean automated = true;
			 System.out.println("Start checking automation status of " + issueKey + "..");
			 String automationStatus = js.getString("fields.customfield_10200.value");
								 
					 if (!automationStatus.startsWith("Automated"))
					 {
						 automated = false;
					 }
				 
			 System.out.println("Automation status: "+ automationStatus);
			 return automated;
		}
		
		@DataProvider(name = "BooksData")
		public Object[][] getData() 
		{ 
			return new Object[][] {{"Test issue 1","This issue is created automatically"}}; 
			  
		}
		
		@DataProvider(name = "TestCases")
		public Object[] getTestCases() 
		{ 
			return new Object[] {"RAT-90","RAT-134"}; 
			  
		}
		 
		@DataProvider(name = "ListOfIds")
		public Object[] getListOfIds() 
		{ 
			Object[] listOfId;
			listOfId = ListOfIds.toArray();
			  
		return listOfId; 			  
		}
		  
		  
	
}


