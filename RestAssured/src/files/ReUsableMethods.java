package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js = new JsonPath(response);
		return js;
	}
	
	public static String getSessionId()
	{
		RestAssured.baseURI = "http://localhost:8085";
		
		String response = given().header("Content-Type", "application/json").cookie("JSESSIONID","D48F258AD1B3CDF5DB193AFB9989F309")
			.body("{ \r\n" + 
					"\"username\": \"nirvanich\", \r\n" + 
					"\"password\": \"Str4nGe-\"\r\n" + 
					"}")
			.when().post("/rest/auth/1/session")
			.then().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String sessionId = js.get("session.value");
		System.out.println(sessionId);
		return sessionId;			
	}
}
