package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;
import io.qameta.allure.Allure;

public class CreateQuestionPage extends ControlActions {

	@FindBys(@FindBy(xpath = "//div[p[contains(@class,'css-2dyhle')]]/button/following-sibling::p"))
	List<WebElement> allQuestionTypeTextElements;

	@FindBy(xpath = "//div[@class='se-wrapper']//div")
	private WebElement problemStatementInputElement;

	@FindBy(xpath = "//button[text()='+ Add Option']")
	private WebElement addOptionBtnElement;

	@FindBy(xpath = "//input[@placeholder='Type a label and press Enter']")
	private WebElement labelTextElement;

	@FindBy(xpath = "//input[@id='skills']")
	private WebElement skillInputElement;

	@FindBy(xpath = "//div[text()='Question published successfully']")
	private WebElement toastMessageElement;

	@FindBy(xpath = "//input[@placeholder='Enter Problem Title']")
	private WebElement problemTitleInputElement;

	@FindBy(id = "score")
	private WebElement scoreInputElement;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelBtnLocator;

	@FindBy(xpath = "//div[@role='presentation']/div[contains(@class,'MuiPaper-root')]/div/div[1]//*[name()='svg']")
	private WebElement closeBtnElement;

	private String difficultyLevelLocator = "//p[text()='%s']";
	private String questionTypelocator = "//div[p[text()='%s']]/button";
	private String optionTextLocator = "//input[@placeholder='Option %d']";
	private String correctAnswerIndexLocator = "option-radio-%d";
	private String selectSkillLocator = "//li[text()='%s']";
	private String topicLocator = "//p[text()='%s']";

	public CreateQuestionPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnQuestionType(String questionType) {
		String questionTypelocator = String.format(this.questionTypelocator, questionType);
		clickOnElement("XPATH", questionTypelocator, true);
		Allure.step("Click On Question Type : " + questionType);
	}

	public boolean isQuestionTypeBtnDisplayed(String questionType) {
		String questionTypelocator = String.format(this.questionTypelocator, questionType);
		boolean flag = isElementDisplayed("XPATH", questionTypelocator, true);
		Allure.step("Is Question Type Button Displayed : " + flag);
		return flag;
	}

	public int countOfVisibleQuetionType() {
		int countOfQuestionType = getAllElementCount(allQuestionTypeTextElements, true);
		Allure.step("Counting the all Question Type : " + countOfQuestionType);
		return countOfQuestionType;
	}

	public List<String> getAllQuestionTypeText() {
		List<String> allQuestions = getAllElementsText(allQuestionTypeTextElements, true);
		Allure.step("All Questions : " + allQuestions);
		return allQuestions;
	}

	public void setProblemTitle(String problemTitle) {
		setText(problemTitleInputElement, problemTitle);
		String actualTitle = getInputElementText(problemTitleInputElement, true);
		Allure.step("Adding the problem title :" + problemTitle);
		if (!actualTitle.equals(problemTitle)) {
			System.out.println("----Adjustment needed *****");
			clearTextUsingBackSpace(problemTitleInputElement, actualTitle);
			setText(problemTitleInputElement, problemTitle);
		}
	}

	public void setProblemStatement(String text) {
		clickOnElement(problemStatementInputElement, true);
		setText(problemStatementInputElement, text);
		Allure.step("Adding the problem statement :" + text);
	}

	public void clickOnDifficultyLevel(String level) {
		String locator = String.format(difficultyLevelLocator, level);
		super.clickOnElement("XPATH", locator, true);
		super.clickOnElement("XPATH", locator, true);
		Allure.step("Click on Difficulty Level : " + level);
	}

	public void setOptionAtIndex(int index, String optionText) {
		String locator = String.format(optionTextLocator, index);
		setText("XPATH", locator, true, optionText);
		Allure.step("Set Option At Index : " + index);
	}

	protected void clickOnElementUsingJs(String locatorType, String locatorValue, boolean isWaitRequired) {
		js.executeScript("document.getElementById('" + locatorValue + "').click();");
	}

	public void setCorrectAnswerAtIndex(int index) {
		String locator = String.format(correctAnswerIndexLocator, index);
		clickOnElementUsingJs("ID", locator, true);
		Allure.step("Selecting the answer index : " + index);
	}

	public void setScore(int score) {
		setText(scoreInputElement, String.valueOf(score));
		Allure.step("setting up score : " + score);
	}

	public void setScore(String score) {
		setText(scoreInputElement, false, score);
		Allure.step("setting up score : " + score);
	}

	public void clickOnAddOptionBtn() {
		clickOnElement(addOptionBtnElement, false);
		Allure.step("Click on add options Button");
	}

	public String getOptionAtIndex(int index) {
		String locator = String.format(optionTextLocator, index);
		return getInputElementText("XPATH", locator, false);
	}

	public void setLabel(String labelText) {
		setText(labelTextElement, labelText);
	}

	public void setSkill(String skillName) {
		setText(skillInputElement, skillName);
		String locator = String.format(selectSkillLocator, skillName);
		super.clickOnElement("XPATH", locator, true);
	}

	public void setTopic(String topic) {
		String locator = String.format(topicLocator, topic);
		super.clickOnElement("XPATH", locator, true);
		Allure.step("Set topic as : " + topic);
	}

	public void clickOnPublishBtn() {
		super.clickOnElement("XPATH", "//button[text()='Publish']", false);
		Allure.step("Click on publish button");
	}

	public boolean isTostMessageDisplyed() {
		return isElementDisplayed(toastMessageElement);
	}

	public void closeCreateQuestionDrawer() {
		clickOnElement(closeBtnElement, false);
		Allure.step("Close created question drawer");
	}
}
