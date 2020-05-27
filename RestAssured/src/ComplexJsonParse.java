import java.util.List;
import java.util.StringTokenizer;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Using dummy response as target for parse
		JsonPath js = new JsonPath(payload.CoursePrice());

		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Number of courses in the dummy response is: " + count);

		// Print Purchase Amount
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount in the dummy response is: " + amount);
		
		// Print Title of the first course
		String title = js.get("courses[0].title");
		System.out.println("Title of the first course in the dummy response is: " + title);
		
		// Print All course titles and their respective Prices
		
		for (int i = 0; i < count; i++) {
			int j = i + 1;
			System.out.print("The title of the " + j + " course in the dummy response is: " + js.get("courses[" + i +"].title"));
			System.out.println(" -- Price is: " + js.getInt("courses[" + i +"].price"));
		}
		
		// Print no of copies sold by RPA Course
	//	String rpaCopies = js.get("courses[?(@.title=='RPA')].copies");
	//	System.out.println(rpaCopies);
		// Verify if Sum of all Course prices matches with Purchase Amount
	}

}
