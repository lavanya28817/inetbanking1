package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class Tc_logginnewcust_003 extends BaseClass

{
	@Test(priority = 0)
	public void login() throws InterruptedException {
	LoginPage lp=new LoginPage(driver);
	lp.setUSerName(username);
	logger.info("User name is provided");
	lp.setPassword(password);
	logger.info("Passsword is provided");
	lp.clickSubmit();
	
	Thread.sleep(3000);	
	}
	
	@Test(dataProvider="NewCust",priority = 1)
	public void NewCust(String custname,String gen,String dob,String addr,String city,String state,String pin,String tele,String email,String pass) throws InterruptedException
, IOException,StaleElementReferenceException
	{
				
		AddCustomerPage ad=new AddCustomerPage(driver);
		ad.clickAddNewCustomer();		
		
		ad.custName(custname);
		Thread.sleep(1000);	
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(ad.custName(custname));
	
		
		
		
		
		WebElement gender1=driver.findElement(By.xpath("//tbody/tr[5]/td[2]/input[1]"));
		WebElement gender2=driver.findElement(By.xpath("//tbody/tr[5]/td[2]/input[2]"));
		String gend=ad.custgender(gen);
		System.out.println("values of gender===" +gend);
		if(gend.equals("female"))
		{
			gender2.click();
		}
		else {
			gender1.click();
		}
		
			
				
		ad.custdod(dob);
		Thread.sleep(1000);	
		ad.custaddress(addr);
		Thread.sleep(1000);	
		ad.custcity(city);
		Thread.sleep(1000);	
		ad.custstate(state);
		Thread.sleep(1000);	
		ad.custpinno(pin);
		Thread.sleep(1000);	
		
		ad.custtelephoneno(tele);
		Thread.sleep(1000);	
		ad.custemailid(email);
		Thread.sleep(1000);	
		ad.custpassword(pass);
		Thread.sleep(1000);	
		ad.custsubmit();
		Thread.sleep(4000);
		
		captureScreen(driver,"NewCustomer");
		logger.info("screenshot taken");
		Thread.sleep(5000);
		
	try {
		ad.clickAddNewCustomer();
	}
	catch(StaleElementReferenceException e)
	{
		WebElement link=driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[2]/a"));
		link.click();
	}
	}
		/*if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			ad.clickAddNewCustomer();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}*/
		
		

	
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
       // Thread.sleep(3000);
		//driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
	 //driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[2]/a")).click();
		

@DataProvider(name="NewCust")
String [][] getData() throws IOException
{
	String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/NewCust1.xlsx";
	
	int rownum=XLUtils.getRowCount(path, "Sheet1");
	int colcount=XLUtils.getCellCount(path,"Sheet1",1);
	
	String logindata[][]=new String[rownum][colcount];
	
	for(int i=1;i<=rownum;i++)
	{
		for(int j=0;j<colcount;j++)
		{
			logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
			System.out.println("values1:===" +logindata[i-1][j]);
		}
	//	System.out.println("values2:===" +logindata[i-1][j]);	
	}
return logindata;
}
}