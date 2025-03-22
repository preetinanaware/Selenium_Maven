package testscripts.smoke.functional;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.FilePath;
import pages.CreateQuestionPage;
import testscripts.TestBase;
import utility.ExcelOperations;

public class CreateQuestionTest extends TestBase {
	CreateQuestionPage createQuestionPage;
	
	@Test
	public void verifyCreateQuestion() {
		System.out.println("VERIFY - Create Question Button is displayed");
		boolean createQuestionBtnFlag = dashboardPage.isCreateQuestionBtnDisplayed();
		Assert.assertTrue(createQuestionBtnFlag, "Create Question button was not displayed");
		
		System.out.println("STEP - Click on Create Questions button");
		createQuestionPage = dashboardPage.clickOnCreateQuestion();
		
		System.out.println("VERIFY - MCQ Question is displayed");
		boolean createMCQBtnFlag = createQuestionPage.isQuestionTypeBtnDisplayed("MCQ");
		Assert.assertTrue(createMCQBtnFlag, "Question Type MCQ button was not displayed");
		
		System.out.println("VERIFY - 3 Questions Type should be displayed");
		int countOfQuestionType = createQuestionPage.countOfVisibleQuetionType();
		Assert.assertEquals(countOfQuestionType, 3);
		
		System.out.println("VERIFY - Text of all Questions Type");
		List<String> expectedListOfQuestionTypeText =  new ArrayList<String>(Arrays.asList("MCQ", "Programming", "SQL"));
		List<String> actualListOfQuestionTypeText = createQuestionPage.getAllQuestionTypeText();
		
		Assert.assertEquals(actualListOfQuestionTypeText, expectedListOfQuestionTypeText);
		
		System.out.println("STEP - Click on MCQ button");
		createQuestionPage.clickOnQuestionType("MCQ");
		
		System.out.println("STEP - Enter Problem Title");
		createQuestionPage.setProblemTitle("Predict Correct Answer--2");
		
		System.out.println("STEP - Enter Problem statement");
		createQuestionPage.setProblemStatement("select the correct statement from given options"); // What is the default value of a local variable in Java?
		
		System.out.println("STEP - Select Difficulty");
		createQuestionPage.clickOnDifficultyLevel("Medium");
		
		System.out.println("STEP - Enter Option 1");
		createQuestionPage.setOptionAtIndex(1, "we can declare class as protected");
		String option1 = createQuestionPage.getOptionAtIndex(1);
		Assert.assertEquals(option1, "we can declare class as protected");
		
		System.out.println("STEP - Enter Option 2");
		createQuestionPage.setOptionAtIndex(2, "constructors can be overriden");
		
		System.out.println("STEP - Add Option");
		createQuestionPage.clickOnAddOptionBtn();
		
		System.out.println("STEP - Enter Option 3");
		createQuestionPage.setOptionAtIndex(3, "local variable can be declared as final");
		
		System.out.println("STEP - Add Option");
		createQuestionPage.clickOnAddOptionBtn();
		
		System.out.println("STEP - Enter Option 4");
		createQuestionPage.setOptionAtIndex(4, "All the classes in set interface maintains insertion order");
		
		System.out.println("STEP - Select Option 1 radio button as answer");
		createQuestionPage.setCorrectAnswerAtIndex(3);
		
		System.out.println("STEP - Assign Score");
		createQuestionPage.setScore(2);
		
		System.out.println("STEP - Enter Label");
		createQuestionPage.setLabel("Automation");
		
		System.out.println("STEP - Select Skills");
		createQuestionPage.setSkill("debugging abilities");
		
		System.out.println("STEP - Select Topics");
		createQuestionPage.setTopic("stepping through code");
		
		System.out.println("STEP - Click on publish button");
		createQuestionPage.clickOnPublishBtn();
		
		System.out.println("VERIFY - 'Question published successfully' toast message displayed");
		boolean toastMessageFlag = createQuestionPage.isTostMessageDisplyed();
		Assert.assertTrue(toastMessageFlag, "Question published successfully toast message was not displayed");
		
		System.out.println("STEP - Close Create Question Drawer");
		createQuestionPage.closeCreateQuestionDrawer();
	}
	
	private void setOption(String option, int index) {
		if(option != null && option.trim().length()>0)
		{
			System.out.println("STEP - Add Option");
			createQuestionPage.clickOnAddOptionBtn();
			
			System.out.println("STEP - Enter Option "+index);
			createQuestionPage.setOptionAtIndex(index, option);
		}
	}
	
	@Test(dataProvider = "getDataForCreateQuestion")
	public void verifyCreateQuestionDataDriven(String title, String statement, String option1, String option2, String option3, String option4, String option5, String option6,
			String correctAns, String score, String difficulty, String label, String skill, String topic, String result) {
		System.out.println("VERIFY - Create Question Button is displayed");
		boolean createQuestionBtnFlag = dashboardPage.isCreateQuestionBtnDisplayed();
		Assert.assertTrue(createQuestionBtnFlag, "Create Question button was not displayed");
		
		System.out.println("STEP - Click on Create Questions button");
		createQuestionPage = dashboardPage.clickOnCreateQuestion();
		
		System.out.println("VERIFY - MCQ Question is displayed");
		boolean createMCQBtnFlag = createQuestionPage.isQuestionTypeBtnDisplayed("MCQ");
		Assert.assertTrue(createMCQBtnFlag, "Question Type MCQ button was not displayed");
		
		System.out.println("VERIFY - 3 Questions Type should be displayed");
		int countOfQuestionType = createQuestionPage.countOfVisibleQuetionType();
		Assert.assertEquals(countOfQuestionType, 3);
		
		System.out.println("VERIFY - Text of all Questions Type");
		List<String> expectedListOfQuestionTypeText =  new ArrayList<String>(Arrays.asList("MCQ", "Programming", "SQL"));
		List<String> actualListOfQuestionTypeText = createQuestionPage.getAllQuestionTypeText();
		
		Assert.assertEquals(actualListOfQuestionTypeText, expectedListOfQuestionTypeText);
		
		System.out.println("STEP - Click on MCQ button");
		createQuestionPage.clickOnQuestionType("MCQ");
		
		System.out.println("STEP - Enter Problem Title");
		createQuestionPage.setProblemTitle(title);
		
		System.out.println("STEP - Enter Problem statement");
		createQuestionPage.setProblemStatement(statement); // What is the default value of a local variable in Java?
		
		System.out.println("STEP - Select Difficulty");
		createQuestionPage.clickOnDifficultyLevel(difficulty);
		
		System.out.println("STEP - Enter Option 1");
		createQuestionPage.setOptionAtIndex(1, option1);
		String actualOption1 = createQuestionPage.getOptionAtIndex(1);
		Assert.assertEquals(actualOption1, option1);
		
		System.out.println("STEP - Enter Option 2");
		createQuestionPage.setOptionAtIndex(2, option2);
		
		setOption(option3, 3);
		setOption(option4, 4);
		setOption(option5, 5);
		setOption(option6, 6);
		
		int correctAnsIndex = Character.getNumericValue(correctAns.trim().charAt(correctAns.length()-1));
		System.out.println("STEP - Select Option "+correctAnsIndex+" radio button as answer");
		createQuestionPage.setCorrectAnswerAtIndex(correctAnsIndex);
		
		System.out.println("STEP - Assign Score");
		createQuestionPage.setScore(score);
		
		System.out.println("STEP - Enter Label");
		createQuestionPage.setLabel(label);
		
		System.out.println("STEP - Select Skills");
		createQuestionPage.setSkill(skill);
		
		System.out.println("STEP - Select Topics");
		createQuestionPage.setTopic(topic);
		
		System.out.println("STEP - Click on publish button");
		createQuestionPage.clickOnPublishBtn();
		
		System.out.println("VERIFY - 'Question published successfully' toast message displayed");
		boolean toastMessageFlag = createQuestionPage.isTostMessageDisplyed();
		Assert.assertTrue(toastMessageFlag, "Question published successfully toast message was not displayed");
		
		System.out.println("STEP - Close Create Question Drawer");
		createQuestionPage.closeCreateQuestionDrawer();
	}
	
	@DataProvider
	public Object[][] getDataForCreateQuestion() throws Exception{
		return ExcelOperations.getSheetData(FilePath.CREATEQUESTION_SHEET, "Data");
	}
}
