package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.ControlActions;

public class LoginPage extends ControlActions{
	
	@FindBy(xpath = "//span[text()='Welcome to Hire360']")
	WebElement welcomeHire360;
	
	@FindBy(xpath = "//input[@id='outlined-adornment-email']")
	WebElement emailInputElement;
	
	@FindBy(xpath = "//input[@id='outlined-adornment-password']")
	WebElement passwordInputElement;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginBtnElement;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean waitForPageLoad() {
		return isElementDisplayed(welcomeHire360);
	}
	
	public void enterUsername(String username) {
		setText(emailInputElement, username);
	}
	
	public void enterPassword(String password) {
		setText(passwordInputElement, password);
	}
	
	public void clickOnLoginBtn() {
		clickOnElement(loginBtnElement, false);
	}
	
	public DashboardPage login(String username, String password) {
		waitForPageLoad();
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
		return new DashboardPage();
	}
}
