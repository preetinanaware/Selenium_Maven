package pages;

import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class LibraryPage extends ControlActions{
	String libraryOptionLocator = "//button[starts-with(text(),'%s')]";
	
	public LibraryPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean waitForPageLoad() {
		return waitUntilURLContains("library");
	}
	
	public int getQuestionCount(String libraryOption) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String locator = String.format(libraryOptionLocator, libraryOption);
		String libraryOptionText = getElementText("XPATH", locator, true);
		String temp = libraryOptionText.split("\\(")[1]; // (2256)
		int count = Integer.parseInt(temp.substring(0, temp.length()-1)); // 2256
		return count;
	}
}
