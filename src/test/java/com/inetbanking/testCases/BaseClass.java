package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {


	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;


	
	public static Logger logger;
	
	
	
    @Parameters("browser")

	@BeforeClass

	public void setup(String br)
	{
	logger = Logger.getLogger("ebanking");

	PropertyConfigurator.configure("Log4j.properties");

		
		if(br.equals("chrome"))

		{
			
                    System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());

			driver=new ChromeDriver();

		}


		else if(br.equals("firefox"))
	

	{


	System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());

	driver = new FirefoxDriver();


		}
		
else if(br.equals("ie"))
	
	{
	
		System.setProperty("webdriver.ie.driver",readconfig.getIepath());

			driver = new InternetExplorerDriver();
	
	}
		


		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.get(baseURL);

	}
	


	@AfterClass

	public void tearDown()
	
{
		
driver.quit();

	}
	
	
	public void captureScreen(WebDriver driver,String tname) throws IOException {
	TakesScreenshot ts=(TakesScreenshot )driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
	String repName= tname+ "-"+timeStamp+".png";
	
	File trg=new File(System.getProperty("user.dir")+ "/Screenshots/" + repName);
	//File trg=new File(System.getProperty("user.dir")+ "/Screenshots/" +tname+".png");
	FileUtils.copyFile(src,trg);
	System.out.println("screenshot taken");
	
}
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
		public static String rameNum() {
			String generatedstring2=RandomStringUtils.randomNumeric(4);
			return(generatedstring2);
		}
		
}
