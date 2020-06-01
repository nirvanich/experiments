import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	
	@Test
	public void addBook() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type", "application/json")
			.body(GenerareStringFromResource("C:\\Gitstuff\\RestAssured\\src\\files\\BooksData.json"))
			.when().post("/Library/Addbook.php")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		
		System.out.println("Book is successfully added. ID: " + id);
	}
	
	@Test
	public void deleteBook() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		JsonPath input_js = new JsonPath(GenerareStringFromResource("C:\\Gitstuff\\RestAssured\\src\\files\\BooksData.json"));
		String id = input_js.getString("isbn") + input_js.getString("aisle");
		String response = given().header("Content-Type", "application/json")
			.body("{\r\n" + 
					"\"ID\" : \""+id+"\"\r\n" + 
					"}")
			.when().post("/Library/DeleteBook.php")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String msg = js.get("msg");
		
		System.out.println(id + " " + msg); 
	}
	
	public static String GenerareStringFromResource(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
