package remoteTestin.DockerValidatio;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class chromeTest1 {

	@BeforeTest
	public void StartDockerScale() throws IOException, InterruptedException
	{
		File fi=new File("output.txt");
		if(fi.delete())
		{
			System.out.println("Starting Docker...");
			
		}
		startDocker s=new startDocker();
		s.startFile();
	}
	
	@AfterTest
	public void StopDockerScale() throws IOException, InterruptedException
	{
		stopDocker d=new stopDocker();
		d.deleteFile();
	}
	
@Test
public void test1() throws MalformedURLException, InterruptedException
{
	// TODO Auto-generated method stub
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		URL u=new URL("http://localhost:4444/wd/hub");
		//RemoteWebDriver class is needed in case we want to test remote
		RemoteWebDriver driver=new RemoteWebDriver(u,cap);
		
		driver.get("https://edition.cnn.com/world");
		Thread.sleep(6000);
		
		System.out.println(driver.getTitle());
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> news1 = driver.findElements(By.xpath("//span[contains(text(), 'corona')]"));

        System.out.println(news1.size() + " news found about coronavirus:");
        int i=1;
        for (WebElement webElement : news1) 
        {
            String name = webElement.getText();
            System.out.println(i+": "+name);
            i=i+1;
        }
        System.out.println();
//		driver.get("https://www.accuweather.com/uk/tr/bodrum/319472/weather-forecast/319472");
//		System.out.println(driver.getTitle());
//		System.out.println(driver.findElementByXPath("/html/body/div/div[5]/div[1]/div/div[1]/a[1]/div").getText());
//		System.out.println();
		
	}

}
