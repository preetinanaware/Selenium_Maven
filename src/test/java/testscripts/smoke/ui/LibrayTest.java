package testscripts.smoke.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LibraryPage;
import testscripts.TestBase;

public class LibrayTest extends TestBase{
	LibraryPage libraryPage;
	
	@Test
	public void verifyQuestionCount() {
		System.out.println("STEP - Navigate to Library");
		dashboardPage.navigateTo("Library");
		
		System.out.println("VERIFY - All questions should be equal to sum of Hire360 Library, My Library and Draft questions");
		libraryPage = new LibraryPage();
		libraryPage.waitForPageLoad();
		
		int expectedQuestionCount = libraryPage.getQuestionCount("All");
		System.out.println(expectedQuestionCount);
		
		int Hire360LibraryCount = libraryPage.getQuestionCount("Hire 360 Library");
		System.out.println(Hire360LibraryCount);
		
		int myLibraryQuestionCount = libraryPage.getQuestionCount("My Library");
		System.out.println(myLibraryQuestionCount);
		
		int draftQuestionCount = libraryPage.getQuestionCount("Draft");
		System.out.println(draftQuestionCount);
		
		int actualQuestionCount = Hire360LibraryCount+myLibraryQuestionCount+draftQuestionCount;	
		System.out.println(actualQuestionCount);
		
		Assert.assertEquals(actualQuestionCount, expectedQuestionCount, "All Question count was " + expectedQuestionCount + " where as sum of other type questions " + actualQuestionCount);
	}
	
	
}
