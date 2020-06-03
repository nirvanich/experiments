package remoteTestin.DockerValidatio;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class chromeTest2 {

//	public static void main(String[] args) throws MalformedURLException, InterruptedException {
@Test
public void test2() throws MalformedURLException, InterruptedException
{
		// TODO Auto-generated method stub
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		URL u=new URL("http://localhost:4444/wd/hub");
		//RemoteWebDriver class is needed in case we want to test remote
		RemoteWebDriver driver=new RemoteWebDriver(u,cap);
		driver.get("https://www.bbc.com/ukrainian");
		Thread.sleep(6000);
		System.out.println(driver.getTitle());
		
				//driver.findElements(By.xpath("//span[contains(text(), 'Корона')]"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> news = driver.findElements(By.xpath("//span[contains(text(), 'корона')]"));

        System.out.println(news.size() + " новини про коронавірус знайдено:");

        int i=1;
        for (WebElement webElement : news) 
        {
            String name = webElement.getText();
            System.out.println(i+": "+name);
            i=i+1;
        }
    
        System.out.println();
}
	
		

		
	

}
