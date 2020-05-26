import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class Basics {

	public static void main(String[] args) {

//	validate if Add place API is working as expected
/*	given
	when
	then*/
//	Add place -> update place with new address -> Get Place to validate if New Address is present in the response

	RestAssured.baseURI = "https://rahulshettyacademy.com";
	String response = 
			
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body(payload.AddPlace())
	
	.when().post("maps/api/place/add/json")
	
	.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
	.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	
	// converting response String to Json variable
	JsonPath js = new JsonPath(response); 
	
	//parsing value from Json
	String place_id = js.getString("place_id");
	
	System.out.println(place_id);
	
	// update place
	String newAddress = "88 Summer time, USA";
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\r\n" + 
			"\"place_id\":\"" + place_id + "\",\r\n" + 
			"\"address\":\""+ newAddress +"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}\r\n" + 
			"")
	.when().put("maps/api/place/update/json")
	
	.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	//Get Place
	String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
	JsonPath js1 = new JsonPath(getPlaceResponse); 	
	String actualAdress = js1.getString("address");
	System.out.println("Expected address is: " + newAddress + " | actual address is: " + actualAdress);
	
	
	
	}

}
