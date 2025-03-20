package testscripts.smoke.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setup() {
		System.out.println("STEP - Open chrome browser and load https://staging.app.hirecorrecto.com");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://staging.app.hirecorrecto.com/login");
		
		System.out.println("Wait until Login page loaded");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Welcome to Hire360']")));
		
		System.out.println("STEP - Enter Username");
		driver.findElement(By.xpath("//input[@id='outlined-adornment-email']")).sendKeys("mauliknov24@gmail.com");
		
		System.out.println("STEP - Enter Password");
		driver.findElement(By.xpath("//input[@id='outlined-adornment-password']")).sendKeys("Pass@123");
		
		System.out.println("STEP - Click on Login Button");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
	}
	
	@Test
	public void verifyDashboardUiElements() {
		System.out.println("VERIFY the 'Hello, Welcome Back' message on the dashboard");
		WebElement welcomeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Hello, Welcome Back!!']")));
		Assert.assertTrue(welcomeElement.isDisplayed());
		
		System.out.println("VERIFY - 'Total Assessments' Lable");
		WebElement totalAssessmentLable = driver.findElement(By.xpath("//p[text()='Total Assessments']"));
		Assert.assertTrue(totalAssessmentLable.isDisplayed());
		
		System.out.println("VERIFY - 'Total Assessments' count");
		WebElement totalAssessmentCountElement = driver.findElement(By.xpath("//p[text()='Total Assessments']//following-sibling::h6"));
		int count = Integer.parseInt(totalAssessmentCountElement.getText());
		Assert.assertTrue(count>=0);
		
		System.out.println("VERIFY - 'Total Appeared' Lable");
		WebElement totalAppearedLable = driver.findElement(By.xpath("//p[text()='Total Appeared']"));
		Assert.assertTrue(totalAppearedLable.isDisplayed());
		
		System.out.println("VERIFY - 'Total Appeared' count");
		WebElement totalApperedCountElement = driver.findElement(By.xpath("//p[text()='Total Appeared']//following-sibling::h6"));
		count = Integer.parseInt(totalApperedCountElement.getText());
		Assert.assertTrue(count>=0);
		
		System.out.println("VERIFY - 'Total Qualified' Lable");
		WebElement totalQualifiedLable = driver.findElement(By.xpath("//p[text()='Total Qualified']"));
		Assert.assertTrue(totalQualifiedLable.isDisplayed());
		
		System.out.println("VERIFY - 'Total Qualified' count");
		WebElement totalQualifiedCountElement = driver.findElement(By.xpath("//p[text()='Total Qualified']//following-sibling::h6"));
		String qualifiedText = totalQualifiedCountElement.getText();
		count = Integer.parseInt(qualifiedText.replace("%", ""));
		Assert.assertTrue(qualifiedText.endsWith("%"), "Qualified count should show in %, % was not displayed at the end");
		Assert.assertTrue(count >=0, "Qualified % count should be >=0, but it was displayed as " + count);	
	
		System.out.println("VERIFY - 'View Template' button is visible");
		WebElement viewTemplateElement = driver.findElement(By.xpath("//p[text()='View Template']"));
		Assert.assertTrue(viewTemplateElement.isDisplayed(), "View Template button was not displayed");
		
		System.out.println("VERIFY - 'Create Question' button is visible");
		WebElement createQuestionElement = driver.findElement(By.xpath("//p[text()='Create Question']"));
		Assert.assertTrue(createQuestionElement.isDisplayed(), "Create Question button was not displayed");
		
		System.out.println("VERIFY - 'Create Assessment' button is visible");
		WebElement createAssessmentElement = driver.findElement(By.xpath("//p[text()='Create Assessment']"));
		Assert.assertTrue(createAssessmentElement.isDisplayed(), "Create Assessement button was not displayed");
	}
}
