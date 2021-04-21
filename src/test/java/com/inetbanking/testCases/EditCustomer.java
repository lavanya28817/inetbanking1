package com.inetbanking.testCases;

import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class EditCustomer extends BaseClass{
	@Test(priority=0)
	public void loginForedit() {
		LoginPage lp=new LoginPage(driver);
		lp.setUSerName(username);
		lp.setPassword(password);
		lp.clickSubmit();
	}
	@Test(priority=1)
public void editTest()
{
	
}

}
