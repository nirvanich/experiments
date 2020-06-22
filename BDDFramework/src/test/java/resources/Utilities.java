package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import Cucumber.Automation.ReUsableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	public static RequestSpecification jiraRequestSpec;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (jiraRequestSpec == null) 
		{
		PrintStream log = new PrintStream(new FileOutputStream("Log.txt"));
		jiraRequestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).addCookie("JSESSIONID", ReUsableMethods.getSessionId()).build();

		return jiraRequestSpec;
		}
		return jiraRequestSpec;
	}

	
	public static String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Gitstuff\\BDDFramework\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(key);
	}
	
	public static String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		return js.getString(key);		
	}
}
