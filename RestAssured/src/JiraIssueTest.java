import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraIssueTest {

	//@Test
	public void getComment() {
		// TODO Auto-generated method stub

		if (!getIfLabelFixedIsPresentInTheIssue()) {
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
	
	
	public boolean getIfLabelFixedIsPresentInTheIssue() {
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

	
	public boolean getOpenLinkedIssue() {
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
			 String issueType = js.getString("fields.issuelinks["+i+"].inwardIssue.fields.issuetype.name");
			 if (issueType.contains("Bug"))
			 {	 
				 
				 String linkedIssueStatus = js.getString("fields.issuelinks["+i+"].inwardIssue.fields.status.name");
				 if (!linkedIssueStatus.contains("Done"))
				 {
					 openIssues = true;
					 System.out.println("Defect summary: " + js.getString("fields.issuelinks["+i+"].inwardIssue.fields.summary"));
					 System.out.println("Defect status: " + js.getString("fields.issuelinks["+i+"].inwardIssue.fields.status.name") + "\n");
				 }
			 }
		 }
		 System.out.println("Open issue presense: "+ openIssues);
		 return openIssues;
	}
	
	
	
	//@Test
	public void startTestDecision() {
		// TODO Auto-generated method stub
		if (getOpenLinkedIssue())
		{
			System.out.println("Test skipped as there are open defects linked.");
		}
		else
		{
			System.out.println("Test started as there are no open defects linked.");
		}
		
	}
	
	@Test(dataProvider = "TestCases")
	public void automationStatusCheck(String issueKey) {
		// TODO Auto-generated method stub
		if (JiraTest.getAutomationStatus(issueKey))
		{
			System.out.println("Test is automated. EXECUTE");
			System.out.println();
		}
		else
		{
			System.out.println("Test is NOT automated. SKIP");
			System.out.println();
		}
		
	}
	
	@DataProvider(name = "TestCases")
	public Object[] getTestCases() 
	{ 
		return new Object[] {"RAT-90","RAT-134","RAT-135","RAT-136","RAT-137"}; 
		  
	}
}
