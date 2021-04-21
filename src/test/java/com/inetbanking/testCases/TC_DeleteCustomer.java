package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.DeleteCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_DeleteCustomer extends BaseClass{
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
	@Test(dataProvider = "DeleteCust",priority = 1)
	public void deleteCustomer(String id) throws InterruptedException {
		
		DeleteCustomerPage dp=new DeleteCustomerPage(driver);
		dp.clicklink();
		Thread.sleep(1000);
		dp.customerid(id);
		Thread.sleep(1000);
		dp.submitbtn();
		Thread.sleep(1000);
		Alert al =driver.switchTo().alert();
		al.accept();
		Thread.sleep(2000);
		Alert al2 =driver.switchTo().alert();
		al2.accept();
		logger.info("customer deleted successfully.....");
		try {
			dp.clicklink();
		}
		catch(StaleElementReferenceException e)
		{
			WebElement link=driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[4]/a"));
			link.click();
		}
		
		
	}
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


		
@DataProvider(name="DeleteCust")
String [][] getData() throws IOException
{
	String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/CustomerId.xlsx";
	
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
	
	}
	return logindata;
}
}
















/*String[] deletecust() throws IOException
{
	String path=System.getProperty("user.dir")+ "/src/test/java/com/inetbanking/testData/CustomerId.xlsx";
	 XSSFWorkbook workbook = new XSSFWorkbook(path);
     XSSFSheet sheet = workbook.getSheet("sheet1");
     XSSFRow row = sheet.getRow(0);

     int col_num = -1;

     for(int i=0; i < row.getLastCellNum(); i++)
     {
         if(row.getCell(i).getStringCellValue().trim().equals("customerid"))
             col_num = i;
     }

     row = sheet.getRow(1);
     XSSFCell cell = row.getCell(col_num);

     String value = cell.getStringCellValue();
     System.out.println("Value of the Excel Cell is - "+ value);
     return value;
}*/
	
	

	
/* FileInputStream fis = new FileInputStream("/Volumes/Krishna/Jar Files/poi-3.16-beta1/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Credentials");
        XSSFRow row = sheet.getRow(0);
 
        int col_num = -1;
 
        for(int i=0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals("UserName"))
                col_num = i;
        }
 
        row = sheet.getRow(1);
        XSSFCell cell = row.getCell(col_num);
 
        String value = cell.getStringCellValue();
        System.out.println("Value of the Excel Cell is - "+ value);
    }*/	
	


