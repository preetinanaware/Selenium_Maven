package testscripts.smoke.functional;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.DashboardPage;
import pages.LoginPage;
import testscripts.TestBase;
public class LoginTest extends TestBase{

//	@BeforeMethod
//	public void setup() {
//		System.out.println("STEP - Open chrome browser and load https://staging.app.hirecorrecto.com");
//		ControlActions.start();
//	}
	
	@Test
	@Description("This test attempts to log into the website using a login and a password")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Maulik Kanani")
    @Link(name = "TC-123", url = "https://jira.technocredits.com/TC-123")
	public void verifyLoginForValidCredentials() {
//		LoginPage loginPage = new LoginPage();
//		
//		System.out.println("VERIFY - Login page is loaded.");
//		boolean welcomeMessageFlag = loginPage.waitForPageLoad();
//		Assert.assertTrue(welcomeMessageFlag);
//		
//		System.out.println("STEP - Enter Username");
//		loginPage.enterUsername("mauliknov24@gmail.com");
//		
//		System.out.println("STEP - Enter Password");
//		loginPage.enterPassword("Pass@123");
//		
//		System.out.println("STEP - Click on Login Button");
//		loginPage.clickOnLoginBtn();
//		
//		System.out.println("VERFIY - Dashboard page is displyed in case of correct credentials, \"Invalid credtentials\" message in case of invalid credentials.");
//		DashboardPage dashboardPage = new DashboardPage();
//        dashboardPage.waitForPageLoad();
		
		String actualURL = dashboardPage.getCurrentURL();
		String expectedURL = "https://staging.app.hirecorrecto.com/dashboard";
		Assert.assertEquals(actualURL, expectedURL);
	}
	
//	@AfterMethod
//	public void tearDown() {
//		ControlActions.closeBrowser();
//	}
}

