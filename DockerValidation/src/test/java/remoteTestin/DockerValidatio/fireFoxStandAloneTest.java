package remoteTestin.DockerValidatio;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class fireFoxStandAloneTest {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap=DesiredCapabilities.firefox();
		URL u=new URL("http://localhost:4444/wd/hub");
		//RemoteWebDriver class is needed in case we want to test remote
		RemoteWebDriver driver=new RemoteWebDriver(u,cap);
		driver.get("https://www.gismeteo.ua/weather-bodrum-3778/");
		System.out.println(driver.getCurrentUrl());
		System.out.println();
		System.out.println(driver.getTitle());
		System.out.println();
		System.out.println(driver.findElementByClassName("tab-content").getText());

		
	}

}
