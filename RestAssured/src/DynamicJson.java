

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson { 
	
	@Test(dataProvider = "BooksData")
	public void addBook(String name, String isbn, String aisle, String author)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type", "application/json")
			.body(payload.AddBook(name, isbn, aisle, author))
			.when().post("/Library/Addbook.php")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		
		System.out.println("Book is successfully added. ID: " + id);
	}
	
	@Test(dataProvider = "BooksData")
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
		 return new Object[][] {{"OlexBook","adddedbook","1","Author Olex"}, {"OlexBook","adddedbook","2","Author Olex"}, {"OlexBook","adddedbook","3","Author Olex"}}; 
		  
	  }
	 

}



