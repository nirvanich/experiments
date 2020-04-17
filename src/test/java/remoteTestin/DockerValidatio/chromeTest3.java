package remoteTestin.DockerValidatio;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class chromeTest3 {

//	public static void main(String[] args) throws MalformedURLException, InterruptedException {
@Test
public void test3() throws MalformedURLException
{
		// TODO Auto-generated method stub
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		URL u=new URL("http://localhost:4444/wd/hub");
		//RemoteWebDriver class is needed in case we want to test remote
		RemoteWebDriver driver=new RemoteWebDriver(u,cap);
		driver.get("https://www.gismeteo.ua/weather-bodrum-3778/tomorrow/");
		System.out.println(driver.getTitle());
		System.out.println(driver.findElementByXPath("/html/body/section/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div/div/div[1]").getText());
		

		
	}

}
