import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jiraPojo.Fields;
import jiraPojo.IssueType;
import jiraPojo.NewIssue;
import jiraPojo.Priority;
import jiraPojo.Project;
import jiraPojo.Reporter;
import jiraPojo.Versions;

public class SpecBuilderTest {

	
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
		priority.setName("Highest");
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
		
		
		RequestSpecification jiraSpec = new RequestSpecBuilder().setBaseUri("http://localhost:8085").setContentType(ContentType.JSON).addCookie("JSESSIONID", ReUsableMethods.getSessionId()).build();
		RequestSpecification jiraBugRequest = given().spec(jiraSpec).body(bug);
		ResponseSpecification json201 = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
		
		Response response = 
				jiraBugRequest.when().post("/rest/api/2/issue")
				.then().spec(json201).extract().response();
		
		String responseString = response.asString();
		
		System.out.println(responseString);
	}

	
}
