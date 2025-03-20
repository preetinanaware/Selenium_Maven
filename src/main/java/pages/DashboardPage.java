package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class DashboardPage extends ControlActions{
	
	@FindBy(xpath = "//p[text()='Create Question']")
	private WebElement createQuestionWebElement;
	
	private String menuLocator = "//span[text()='%s']";
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean waitForPageLoad() {
		return waitUntilURLContains("dashboard");
	}
	
	public String getCurrentURL() {
		return super.getCurrentURL();
	}
	
	public CreateQuestionPage clickOnCreateQuestion() {
		clickOnElement(createQuestionWebElement, true);
		return new CreateQuestionPage();
	}
	
	public boolean isCreateQuestionBtnDisplayed() {
		return isElementDisplayed(createQuestionWebElement);
	}
	
	public void navigateTo(String navigationOptions) {
		String locator = String.format(menuLocator, navigationOptions);
		clickOnElement("XPATH", locator, true);
	}
}
