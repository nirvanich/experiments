package remoteTestin.DockerValidatio;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class chromeStandAloneTest {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		URL u=new URL("http://localhost:4444/wd/hub");
		//RemoteWebDriver class is needed in case we want to test remote
		RemoteWebDriver driver=new RemoteWebDriver(u,cap);
		driver.get("http://crxmarkets.com");
		System.out.println(driver.getTitle());
		
	}

}
