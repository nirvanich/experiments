import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraIssueTest {

	//@Test
	public void getComment() {
		// TODO Auto-generated method stub

		if (!getIssue()) {
		RestAssured.baseURI = "http://localhost:8085";
		 
		 String response = given().pathParam("key", "10504").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				 .queryParam("fields", "comment")
		 .when().get("/rest/api/2/issue/{key}")
		 .then().assertThat().statusCode(200).extract().response().asString();
		
		 JsonPath js = new JsonPath(response);
		 boolean fixed = false;
		 System.out.println("Start checking issue comments..");
		 int commentsCount = js.getInt("fields.comment.total");
		 for (int i=0; i<commentsCount;i++)
		 {
			 String comment = js.getString("fields.comment.comments["+i+"].body");
			 if (comment.contains("fixed"))
			 {
				 fixed = true;
				 break;
			 }
		 }
		 System.out.println("At least one comment contains 'Fixed' word: "+ fixed);
		} else
		{
			System.out.println("Label 'Fixed' is present in the issue so it is skipped for testing");
		}
	}
	
	
	public boolean getIssue() {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8085";
		 
		 String response = given().pathParam("key", "10504").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				 .queryParam("fields", "labels")
		 .when().get("/rest/api/2/issue/{key}")
		 .then().assertThat().statusCode(200).extract().response().asString();
		
		 JsonPath js = new JsonPath(response);
		 boolean flag = false;
		 System.out.println("Start checking issue labels..");
		 int labelsCount = js.getInt("fields.labels.size()");
		 for (int i=0; i<labelsCount;i++)
		 {
			 String label = js.getString("fields.labels["+i+"]");
			 if (label.contains("fixed"))
			 {
				 flag = true;
				 
			 }
		 }
		 System.out.println("Label 'fixed' presense: "+ flag);
		 return flag;
	}

	@Test
	public void getLinkedIssue() {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8085";
		 
		 String response = given().pathParam("key", "10504").cookie("JSESSIONID", ReUsableMethods.getSessionId())
				 .queryParam("fields", "issuelinks")
		 .when().get("/rest/api/2/issue/{key}")
		 .then().assertThat().statusCode(200).extract().response().asString();
		 		 
		 JsonPath js = new JsonPath(response);
		 
		 boolean openIssues = false;
		 System.out.println("Start checking linked issues..");
		 int linkedissuesCount = js.getInt("fields.issuelinks.size()");
		 for (int i=0; i<linkedissuesCount;i++)
		 {
			 System.out.print("Defect summary: " + js.getString("fields.issuelinks["+i+"].inwardIssue.fields.summary")+" | ");
			 System.out.println("Defect status: " + js.getString("fields.issuelinks["+i+"].inwardIssue.fields.status.name"));
			 String linkedIssueStatus = js.getString("fields.issuelinks["+i+"].inwardIssue.fields.status.name");
			 if (!linkedIssueStatus.contains("Done"))
			 {
				 openIssues = true;
				 
			 }
		 }
		 System.out.println("Open issue presense: "+ openIssues);
		 //return openIssues;
	}
}
