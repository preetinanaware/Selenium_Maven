package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;
import utility.PropertyUtils;

public abstract class ControlActions {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static JavascriptExecutor js;
	static PropertyUtils propertyUtils = new PropertyUtils("./src/main/resources/envConfig.properties");

	public static void start() {
		String browserName = System.getProperty("browser");
		String env = System.getProperty("env");

		System.out.println("Launching : " + browserName);
		System.out.println("Loading Env : " + env);

		// If the pom.xml system variable are commented or not present & if we want to
		// run on default value.
		if (browserName == null)
			browserName = "CHROME";
		if (env == null)
			env = "STAGE";

		System.out.println("Broser Name : " + browserName);
		System.out.println("Env Name : " + env);

		openBrowser(browserName);
		loadEnvironmentInBrowser(env);
	}

	private static void loadEnvironmentInBrowser(String env) {
//		switch (env.toLowerCase()) {
//		case "qa":
//			driver.get(propertyUtils.getPropertyValue("qa"));
//			break;
//		case "stage":
//			driver.get(propertyUtils.getPropertyValue("stage"));
//			break;
//
//		case "dev":
//			driver.get(propertyUtils.getPropertyValue("dev"));
//			break;
//
//		default:
//			throw new RuntimeException("Env Not Supported : " + env);
//		}
		driver.get(propertyUtils.getPropertyValue(env.toLowerCase()));
	}

	protected static void openBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			throw new RuntimeException("Browser Not Supported : " + browserName);

		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
	}

	@SuppressWarnings("unchecked")
	protected void waitForPageToLoad() {
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String readyState = (String) js.executeScript("return document.readyState");
				System.out.println(readyState);
				return readyState.equals("complete");
			}
		};

		fwait.until(function);
	}
//
//	protected enum TypeOfLocator{
//		XPATH, ID, NAME, CSS, CLASSNAME, TAGNAME, LINKEDTEXT, PARTIALLINKEDTEXT;
//	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = null;
		locatorType = locatorType.toUpperCase();

		switch (locatorType) {
		case "XPATH":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			} else {
				element = driver.findElement(By.xpath(locatorValue));
			}
			break;

		case "ID":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			} else {
				element = driver.findElement(By.id(locatorValue));
			}
			break;

		case "NAME":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			} else {
				element = driver.findElement(By.name(locatorValue));
			}
			break;

		case "CSS":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			} else {
				element = driver.findElement(By.cssSelector(locatorValue));
			}
			break;

		case "LINKEDTEXT":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			} else {
				element = driver.findElement(By.linkText(locatorValue));
			}
			break;

		}
		return element;

	}

	protected List<WebElement> getElements(String locatorType, String locatorValue, boolean isWaitRequired) {
		List<WebElement> listOfElements = null;
		locatorType = locatorType.toUpperCase();

		switch (locatorType) {
		case "XPATH":
			if (isWaitRequired) {
				listOfElements = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
			} else {
				listOfElements = driver.findElements(By.xpath(locatorValue));
			}
			break;

		case "ID":
			if (isWaitRequired) {
				listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			} else {
				listOfElements = driver.findElements(By.id(locatorValue));
			}
			break;

		case "NAME":
			if (isWaitRequired) {
				listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
			} else {
				listOfElements = driver.findElements(By.name(locatorValue));
			}
			break;

		case "CSS":
			if (isWaitRequired) {
				listOfElements = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
			} else {
				listOfElements = driver.findElements(By.cssSelector(locatorValue));
			}
			break;

		case "LINKEDTEXT":
			if (isWaitRequired) {
				listOfElements = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
			} else {
				listOfElements = driver.findElements(By.linkText(locatorValue));
			}
			break;

		}
		return listOfElements;

	}

	protected void scrollToElement(WebElement e) {

		js.executeScript("arguments[0].scrollIntoView(true)", e);
	}

	protected void setText(String locatorType, String locatorValue, boolean isWaitRequired, String textToBeEntered) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		if (!e.isDisplayed()) {
			scrollToElement(e);
		}
		e.sendKeys(textToBeEntered);
	}

	protected void setText(WebElement e, String textToBeEntered) {
		if (!e.isDisplayed()) {
			scrollToElement(e);
		}
		e.sendKeys(textToBeEntered);
	}

	protected void setText(WebElement e, boolean isWaitRequired, String textToBeEntered) {
		if (isWaitRequired)
			wait.until(ExpectedConditions.visibilityOf(e));

		if (!e.isDisplayed()) {
			scrollToElement(e);
		}
		e.sendKeys(textToBeEntered);
	}

	protected void setText(String locatorType, String locatorValue, String textToBeEntered) {
		setText(locatorType, locatorValue, false, textToBeEntered);
	}

	protected void clearText(String locatorType, String locatorValue) {
		WebElement e = getElement(locatorType, locatorValue, true);
		e.clear();
	}

	protected void pressBackSpace(String locatorType, String locatorValue) {
		WebElement e = getElement(locatorType, locatorValue, true);
		e.click();
		e.sendKeys(Keys.BACK_SPACE);
	}

	protected void clearTextUsingBackSpace(String locatorType, String locatorValue, String text) {
		if (text.length() > 0) {
			WebElement e = getElement(locatorType, locatorValue, true);
			e.click();
			for (int index = 0; index < text.length(); index++) {
				e.sendKeys(Keys.BACK_SPACE);
			}
		}
	}

	protected void clearTextUsingBackSpace(WebElement e, String text) {
		if (text.length() > 0) {
			e.click();
			for (int index = 0; index < text.length(); index++) {
				e.sendKeys(Keys.BACK_SPACE);
			}
		}
	}

	protected void clickOnElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		if (!e.isDisplayed()) {
			scrollToElement(e);
		}
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.click();
	}

	protected void clickOnElement(WebElement e, boolean isWaitRequired) {
		wait.until(ExpectedConditions.visibilityOf(e));
		if (!e.isDisplayed()) {
			scrollToElement(e);
		}
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.click();
	}

	protected boolean isElementDisplayed(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		return e.isDisplayed();
	}

	protected boolean isElementDisplayed(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
		return e.isDisplayed();
	}

	protected String getElementText(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		return e.getText();
	}

	protected String getInputElementText(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		return e.getDomAttribute("value");
	}

	protected String getInputElementText(WebElement e, boolean isWaitRequired) {
		if (isWaitRequired)
			wait.until(ExpectedConditions.visibilityOf(e));
		return e.getDomAttribute("value");
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected boolean waitUntilURLContains(String text) {
		return wait.until(ExpectedConditions.urlContains(text));
	}

	protected int getAllElementCount(String locatorType, String locatorValue, boolean isWaitRequired) {
		List<WebElement> listOfWebElements = getElements(locatorType, locatorValue, isWaitRequired);
		return listOfWebElements.size();
	}

	protected int getAllElementCount(List<WebElement> listOfElements, boolean isWaitRequired) {
		if (isWaitRequired)
			wait.until(ExpectedConditions.visibilityOfAllElements(listOfElements));
		return listOfElements.size();
	}

	protected List<String> getAllElementsText(String locatorType, String locatorValue, boolean isWaitRequired) {
		List<WebElement> listOfWebElements = getElements(locatorType, locatorValue, isWaitRequired);
		List<String> listOfElementText = new ArrayList<String>();
		for (WebElement e : listOfWebElements) {
			listOfElementText.add(e.getText());
		}
		return listOfElementText;
	}

	protected List<String> getAllElementsText(List<WebElement> listOfWebElements, boolean isWaitRequired) {
		if (isWaitRequired)
			wait.until(ExpectedConditions.visibilityOfAllElements(listOfWebElements));

		List<String> listOfElementText = new ArrayList<String>();
		for (WebElement e : listOfWebElements) {
			listOfElementText.add(e.getText());
		}
		return listOfElementText;
	}

	public static void takeScreenshot(String screenShotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("screenshots/" + screenShotName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] saveScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screenShot = ts.getScreenshotAs(OutputType.BYTES);
		return screenShot;
	}

	public static void closeBrowser() {
		driver.quit();
	}
}
