import static io.restassured.RestAssured.given;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;

public class SerializeJiraTicketCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8085";
		String body = "123";
		String response = given().header("Content-Type", "application/json").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				.body(payload.createIssue("test","test"))
				.when().post("/rest/api/2/issue")
				.then().assertThat().statusCode(201)
				.extract().response().asString();
	}

}
