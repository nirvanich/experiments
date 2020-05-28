import org.testng.Assert;


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
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount in the dummy response is: " + purchaseAmount);
		
		// Print Title of the first course
		String title = js.get("courses[0].title");
		System.out.println("Title of the first course in the dummy response is: " + title);
		
		// Print All course titles and their respective Prices
		
		for (int i = 0; i < count; i++) {
			int j = i + 1;
			System.out.print("The title of the " + j + " course in the dummy response is: " + js.get("courses[" + i +"].title"));
			System.out.print(" -- Price is: " + js.getInt("courses[" + i +"].price"));
			System.out.println(" -- Copies is: " + js.getInt("courses[" + i +"].copies"));
		}
		
		// Print no of copies sold by RPA Course
		for (int i = 0; i < count; i++) {
			if (js.get("courses[" + i +"].title").toString().contains("RPA")) {
				System.out.println("Number of copies sold by RPA Course: "+ js.get("courses[" + i +"].copies").toString());
				break;
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int sumAmount = 0;
		for (int i = 0; i < count; i++) {
			sumAmount = sumAmount + (js.getInt("courses[" + i +"].price") * js.getInt("courses[" + i +"].copies"));
			}
		System.out.println("Sum of all Course prices: " + sumAmount + " | Sum of Purchase Amount: " + purchaseAmount);
		if (sumAmount==purchaseAmount) {
			System.out.println("Sum of all Course prices matches with Purchase Amount!");
		}
		Assert.assertEquals(sumAmount, purchaseAmount, "Sum of all Course prices DOESN'T match with Purchase Amount");
		
		
	}

}
