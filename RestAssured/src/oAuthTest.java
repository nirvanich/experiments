import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		

//		System.setProperty("webdriver.chrome.driver", "C:\\Gitstuff\\advanced-selenium-webdriver\\src\\main\\resources\\chromedriver.exe");
//			WebDriver driver = new ChromeDriver();
//			
//			driver.get("https://accounts.google.com/signin/oauth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&o2v=2&as=egsCVLc-njzxf08_SiInlA&flowName=GeneralOAuthFlow");
//			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testolextest@gmail.com");
//			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//input[@type='password']")).sendKeys("P@ssw0rd13");
//			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
//			Thread.sleep(4000);
//			String url = driver.getCurrentUrl();
			String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0gF8R5iu-Cz2Wf7-CTbEvYcD9DEmVGYe2vAubKm5E-Pv9JUib7RosH12KJ7342wpoxZ81mcRk_dCaQpBsmUd5vE&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
			
			String afterCode = url.split("code=")[1];
			String code = afterCode.split("&scope")[0];
			System.out.println(code);
			
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		String response = given()
		.queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(response);
	}

}
