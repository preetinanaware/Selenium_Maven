package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.ControlActions;
import io.qameta.allure.Step;

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
	
	@Step("Waiting For Page Load")
	public boolean waitForPageLoad() {
		return isElementDisplayed(welcomeHire360);
	}
	
	@Step("Enter User Name as {0}")
	public void enterUsername(String username) {
		setText(emailInputElement, username);
	}
	
	@Step("Enter Password as {0}")
	public void enterPassword(String password) {
		setText(passwordInputElement, password);
	}
	
	@Step("Click On Login Button")
	public void clickOnLoginBtn() {
		clickOnElement(loginBtnElement, false);
	}
	
	@Step("User Enter Username as {0} and Enter Password as {1}")
	public DashboardPage login(String username, String password) {
		waitForPageLoad();
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
		return new DashboardPage();
	}
}
