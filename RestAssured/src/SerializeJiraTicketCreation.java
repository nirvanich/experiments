import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import jiraPojo.Fields;
import jiraPojo.IssueType;
import jiraPojo.NewIssue;
import jiraPojo.Priority;
import jiraPojo.Project;
import jiraPojo.Reporter;
import jiraPojo.Versions;

public class SerializeJiraTicketCreation {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8085";
		
		NewIssue bug = new NewIssue();
		Fields fields = new Fields();
		Project project = new Project();
		IssueType issuetype = new IssueType();
		Reporter reporter = new Reporter();
		Priority priority = new Priority();
		Versions affectedVersion = new Versions();
		List<Versions> versions = new ArrayList<Versions>();
		
		reporter.setName("nirvanich");
		issuetype.setName("Bug");
		project.setKey("RAT");
		priority.setName("Blocker");
		affectedVersion.setName("1.0");
		versions.add(affectedVersion);
				
		fields.setReporter(reporter);
		fields.setIssuetype(issuetype);
		fields.setProject(project);
		fields.setSummary("Defect made of Serialized Json");
		fields.setDescription("Test description");
		fields.setPriority(priority);
		fields.setVersions(versions);
		bug.setFields(fields);
		
		
		String response = given().header("Content-Type", "application/json").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				.body(bug).log().all()
				.when().post("/rest/api/2/issue")
				.then().log().all().assertThat().statusCode(201)
				.extract().response().asString();
		
		System.out.println(response);
	}

	
}
