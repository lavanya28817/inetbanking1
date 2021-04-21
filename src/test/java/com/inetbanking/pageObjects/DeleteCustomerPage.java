package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.How;

public class DeleteCustomerPage {
	WebDriver ldriver;
	public DeleteCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	// 
@FindBy(how = How.XPATH, using ="/html/body/div[3]/div/ul/li[4]/a")
	@CacheLookup
	WebElement linkdeletecust;
	
	
	@FindBy(how = How.NAME, using = "cusid")
	@CacheLookup
	WebElement custidtxtbox;
	
	@FindBy(how = How.NAME, using = "AccSubmit")
	@CacheLookup
	WebElement Submitbtn;


	
	
	public void clicklink() {
		linkdeletecust.click();
	}
	
	public void customerid(String custid) {
		custidtxtbox.sendKeys(custid);
	}
	public void submitbtn() {
		Submitbtn.click();
	}
	
}
