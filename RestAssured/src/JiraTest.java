import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraTest {

		public String id;
		public List<String> ListOfIds = new ArrayList<String>();
		
		@Test(dataProvider = "BooksData")
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
			String key = js.get("key");
			ListOfIds.add(id);
			
			System.out.println("Issue is successfully created. ID: " + id + ", issueKey is: " + key);
			addComment(id, "Autocomment: id of this issue is: " + id);
		}
		
		
		public void addComment(String idd, String comment)
		{
			RestAssured.baseURI = "http://localhost:8085";
			//String id = "10130";
			given().header("Content-Type", "application/json").pathParam("id", idd).cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.body("{\r\n" + 
					"    \"body\": \"" + comment + "\",\r\n" + 
					"    \"visibility\": {\r\n" + 
					"        \"type\": \"role\",\r\n" + 
					"        \"value\": \"Administrators\"\r\n" + 
					"    }\r\n" + 
					"}")
			.when().post("/rest/api/2/issue/{id}/comment")
			.then().assertThat().statusCode(201)
			.extract().response().asString();

			System.out.println("Comment to the Issue with ID: " + id + " is succssfully added.");
		}
		
		//@Test(dataProvider = "ListOfIds")
		public void deleteIssue(String id)
		{
			RestAssured.baseURI = "http://localhost:8085";
			
			given().header("Content-Type", "application/json").pathParam("id", id).cookie("JSESSIONID", ReUsableMethods.getSessionId())
			.when().delete("/rest/api/2/issue/{id}")
			.then().assertThat().statusCode(204)
			.extract().response().asString();

			System.out.println("Issue succssfully deleted. ID: " + id);
		}
		
		
		 
		
		  @DataProvider(name = "BooksData")
		  public Object[][] getData() 
		  { 
			 return new Object[][] {{"Test issue 1","This issue is created automatically"},{"Test issue 2","This issue is created automatically"}}; 
			  
		  }
		 
		  @DataProvider(name = "ListOfIds")
		  public Object[] getListOfIds() 
		  { 
			  Object[] listOfId;
			    listOfId = ListOfIds.toArray();
			  
			 return listOfId; 			  
		  }
		  
		  
	
}


