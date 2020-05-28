package files;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type", "application/json")
			.body(payload.AddBook("OlexBook","adddedbook","1","Author Olex"))
			.when().post("/Library/Addbook.php")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		
		System.out.println("Book is successfully added. ID: " + id);
	}
	
	@Test
	public void deleteBook()
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String id = "adddedbook1";
		String response = given().header("Content-Type", "application/json")
			.body(payload.deleteBook(id))
			.when().post("/Library/DeleteBook.php")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String msg = js.get("msg");
		
		System.out.println(id + " " + msg);
	}
}
