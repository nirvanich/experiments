import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import files.ReUsableMethods;
import io.restassured.RestAssured;

public class HashMapJiraTicketCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap <String, Object> bug = new HashMap <String, Object>();
		HashMap <String, Object> fields = new HashMap <String, Object>();
			HashMap <String, Object> project = new HashMap <String, Object>();
				project.put("key", "RAT");
			HashMap <String, Object> issuetype = new HashMap <String, Object>();
				issuetype.put("name", "Bug");
			HashMap <String, Object> reporter = new HashMap <String, Object>();
				reporter.put("name", "nirvanich");
			HashMap <String, Object> priority = new HashMap <String, Object>();
				priority.put("name", "Blocker");
			List<HashMap<String, Object>> versions = new ArrayList<HashMap<String, Object>>();
				//HashMap <Object> versions = new HashMap <Object>();
				HashMap<String, Object> version = new HashMap <String, Object>();
				version.put("name", "1.0");
				versions.add(version);
		
		bug.put("fields", fields);
		fields.put("project", project);
		fields.put("summary", "Defect made of Json HashMap");
		fields.put("issuetype", issuetype);
		fields.put("reporter", reporter);
		fields.put("description", "Test Description");
		fields.put("priority", priority);
		fields.put("versions", versions);
		
		
		
		
		RestAssured.baseURI = "http://localhost:8085";
		String response = given().header("Content-Type", "application/json").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				.body(bug).log().all()
				.when().post("/rest/api/2/issue")
				.then().assertThat().statusCode(201)
				.extract().response().asString();
		
		System.out.println(response);
	}

}
