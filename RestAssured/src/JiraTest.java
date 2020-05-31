import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraTest {

		@Test(dataProvider = "BooksData")
		public void createIssue(String summary, String description)
		{
			RestAssured.baseURI = "http://localhost:8085";
			
			String response = given().header("Content-Type", "application/json").cookie("JSESSIONID","D48F258AD1B3CDF5DB193AFB9989F309")
				.body(payload.createIssue(summary, description))
				.when().post("/rest/api/2/issue")
				.then().assertThat().statusCode(201)
				.extract().response().asString();
			
			JsonPath js = ReUsableMethods.rawToJson(response);
			String id = js.get("id");
			String key = js.get("key");
			
			
			System.out.println("Issue is successfully created. ID: " + id + ", issueKey is: " + key);
		}
		
		//@Test(dataProvider = "BooksData")
		public void deleteBook(String name, String isbn, String aisle, String author)
		{
			RestAssured.baseURI = "http://216.10.245.166";
			String id = isbn+aisle;
			String response = given().header("Content-Type", "application/json")
				.body(payload.deleteBook(id))
				.when().post("/Library/DeleteBook.php")
				.then().assertThat().statusCode(200)
				.extract().response().asString();
			
			JsonPath js = ReUsableMethods.rawToJson(response);
			String msg = js.get("msg");
			
			System.out.println(id + " " + msg);
		}
		
		
		  @DataProvider(name = "BooksData")
		  public Object[][] getData() 
		  { 
			 return new Object[][] {{"Autocreated bug 1","This issue is created automatically"}}; 
			  
		  }
		 

	
}


