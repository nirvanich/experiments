package remoteTestin.DockerValidatio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;



import org.junit.Assert;

public class stopDocker {

	
	
	public void deleteFile() throws IOException, InterruptedException
	{
		Boolean flag=false;
		Runtime runtime=Runtime.getRuntime();
		runtime.exec("cmd /c start dockerdown.bat");
		
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
		
		if(currentLine.contains("selenium-hub exited"))
		{
			System.out.println();
			System.out.println();
			System.out.println("Docker is successfully closed");
			System.out.println("Testing complete");
			flag=true;
			break;
		}
		
		currentLine=reader.readLine();
	}
	reader.close();
	
	}
	
Assert.assertTrue(flag);




	}
	
}
