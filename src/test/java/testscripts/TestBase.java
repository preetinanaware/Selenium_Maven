package testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ControlActions;
import pages.DashboardPage;
import pages.LoginPage;
import utility.DateTimeUtil;

public class TestBase {
	protected DashboardPage dashboardPage;
	
	@BeforeMethod
	public void setup() {
		System.out.println("PREREQUISITE - Login HireCorrecto using valid credentials and user is on dashboard page.");
		ControlActions.start();
		LoginPage page = new LoginPage();
		dashboardPage = page.login("mauliknov24@gmail.com", "Pass@123");
		dashboardPage.waitForPageLoad();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus() == 2) {
			ControlActions.takeScreenshot(result.getName()+"_"+ DateTimeUtil.getTimeStamp());
			ControlActions.saveScreenshot();
		}
		System.out.println("CLEAN UP - Close browser");
		ControlActions.closeBrowser();
	}
}
