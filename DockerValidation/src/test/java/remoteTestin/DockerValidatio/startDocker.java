package remoteTestin.DockerValidatio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;



import org.junit.Assert;

public class startDocker {

	

	public void startFile() throws IOException, InterruptedException
	{
		Boolean flag=false;
		Runtime runtime=Runtime.getRuntime();
		runtime.exec("cmd /c start dockerup.bat");
		
	String f = "output.txt";
	
	Calendar cal=Calendar.getInstance();
	cal.add(Calendar.SECOND, 45);
	long stopnow=cal.getTimeInMillis();
	
	Thread.sleep(3000);
	
	while(System.currentTimeMillis()<stopnow)
	{
		if(flag)
		{
			break;
		}
		BufferedReader reader=new BufferedReader(new FileReader(f));
		String currentLine=reader.readLine();
	while(currentLine!=null && !flag)
		
	{
		
		if(currentLine.contains("The node is registered to the hub and ready to use"))
		{
			System.out.println("Docker successfully started.");
			System.out.println("2 containers successfully created: chrome_1, firefox_1");
			System.out.println("Scaling chrome container to 2");
			System.out.println("Tests start in few seconds...");
			flag=true;
			break;
		}
		
		currentLine=reader.readLine();
	}
	reader.close();
	
	}
	
Assert.assertTrue(flag);
runtime.exec("cmd /c start scalechrome.bat");
Thread.sleep(6000);



	}
	
}
