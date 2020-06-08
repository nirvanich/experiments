import static io.restassured.RestAssured.given;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import jiraPojo.Fields;
import jiraPojo.IssueType;
import jiraPojo.NewIssue;
import jiraPojo.Project;
import jiraPojo.Reporter;

public class SerializeJiraTicketCreation {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8085";
		
		NewIssue bug = new NewIssue();
		Fields fields = new Fields();
		Project project = new Project();
		IssueType issuetype = new IssueType();
		Reporter reporter = new Reporter();
		reporter.setName("nirvanich");
		issuetype.setName("Bug");
		project.setKey("RAT");
		fields.setReporter(reporter);
		fields.setIssuetype(issuetype);
		fields.setProject(project);
		fields.setSummary("Defect made of Serialized Json");
		fields.setDescription("Test description");
		bug.setFields(fields);
		
		
		String response = given().header("Content-Type", "application/json").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				.body(bug)
				.when().post("/rest/api/2/issue")
				.then().assertThat().statusCode(201)
				.extract().response().asString();
		
		System.out.println(response);
	}

	
}
