package config;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
//import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.Properties;
import org.apache.xpath.operations.Equals;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionKeywords {
	//app-xml
	public static WebDriver driver;
	private static final int timeoutWait = 10;
	private static final int timeoutWaitForPageLoaded = 20;
	private static Actions action;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;

	public static Properties OR = new Properties(System.getProperties());

	private static WebElement GetElement(String locatorType, String locatorValue) {
		WebElement element;

		if (locatorType.equalsIgnoreCase("className"))
			element = driver.findElement(By.className(locatorValue));
		else if (locatorType.equalsIgnoreCase("cssSelector"))
			element = driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.equalsIgnoreCase("id"))
			element = driver.findElement(By.id(locatorValue));
		else if (locatorType.equalsIgnoreCase("partialLinkText"))
			element = driver.findElement(By.partialLinkText(locatorValue));
		else if (locatorType.equalsIgnoreCase("name"))
			element = driver.findElement(By.name(locatorValue));
		else if (locatorType.equalsIgnoreCase("xpath"))
			element = driver.findElement(By.xpath(locatorValue));
		else if (locatorType.equalsIgnoreCase("tagName"))
			element = driver.findElement(By.tagName(locatorValue));
		else
			element = driver.findElement(By.xpath(locatorValue));

		return element;
	}
	public static WebDriver getdriver() {
		return driver;
	}

	public static void openBrowser() {
		driver = new ChromeDriver(); 
	}

	// Khoi tao cau hinh cua cac Browser de dua vao Switch Case
	private static WebDriver initChromeDriver() {
		System.out.println("Launching Chrome browser...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	private static WebDriver initFirefoxDriver() {
		System.out.println("Launching Firefox browser...");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	private static WebDriver initOperaDriver() {
		System.out.println("Launching Opera browser...");
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver openBrowser(String browserType) {
		switch (browserType.trim().toLowerCase()) {
		case "chrome":
			driver = initChromeDriver();
			break;
		case "firefox":
			driver = initFirefoxDriver();
			break;
		case "opera":
			driver = initOperaDriver();
			break;
		default:
			System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
			driver = initChromeDriver();
		}
		wait = new WebDriverWait(driver, timeoutWait);
		return driver;
	}

	public static void navigate(String url) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(url);
			driver.manage().window().maximize();
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Error..." + e.getStackTrace());
		}
	}

	public static void quitDriver() throws Exception {
		if (driver.toString().contains("null")) {
			System.out.print("All Browser windows are closed ");
		} else {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}

	public static void setText(WebElement element, String Value) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(Value);
	}

	public static void setText(String locatorType, String locatorValue, String value) {
		WebElement element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	public static void clickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void clickElement(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public static void clickElementWithJs(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].click();", element);
	}

	//
	public static void clickElementWithJs(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].click();", element);
	}

	public static void waitForPageLoaded() {
		try {
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
							.equals("complete");
				}
			});
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load request.");
		}
	}

	public static boolean verifyPageLoaded(String pageLoadedText) {
		waitForPageLoaded();
		Boolean res = false;

		List<WebElement> elementList = driver.findElements(By.xpath("//*contains(text(),'" + pageLoadedText + "')]"));
		if (elementList.size() > 0) {
			res = true;
			System.out.println("Page loaded(" + res + "): " + pageLoadedText);
		} else {
			res = false;
			System.out.println("Page loaded(" + res + "): " + pageLoadedText);
		}
		return res;
	} 

	public static boolean verifyUrl(String url) {
		return driver.getCurrentUrl().contains(url);
	}

	public static String getPageTitle() {
		waitForPageLoaded();
		return driver.getTitle(); 
	}

	public static boolean verifyPageTitle(String pageTitle) {
		return getPageTitle().equals(pageTitle);
	}

	public static void rightClickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		action.contextClick().build().perform();
	}
 
	//
	public static void rightClickElement(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		wait.until(ExpectedConditions.elementToBeClickable(element));
		action.contextClick().build().perform();
	}

	// Chon du lieu tu combobox
	public static void selectOptionByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public static void selectOptionByText(String locatorType, String locatorValue, String text) {
		WebElement element = GetElement(locatorType, locatorValue);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public static void selectOptionByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void selectOptionByValue(String locatorType, String locatorValue, String value) {
		WebElement element = GetElement(locatorType, locatorValue);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void selectOptionByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void selectOptionByIndex(String locatorType, String locatorValue, int index) {
		WebElement element = GetElement(locatorType, locatorValue);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static boolean verifyElementText(WebElement element, String textValue) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText().equals(textValue);
	}

	public static boolean verifyElementText(String locatorType, String locatorValue, String text) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println(element.getText().toString());
    	System.out.println(text);
		//return element.getText().equalsIgnoreCase(text);
	  
		return element.getText().equals(text);
		 
			
		// assertEquals(text, element.getText());

	}
	public static boolean verifyElementPresent(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		//element = driver.findElement(By.xpath(locatorValue));
//		if (element != null) {
//			return true;
//		}
//		else {
//			return false;
//		}
//		 
		
		//waitForPageLoaded();
		//wait.until(ExpectedConditions.visibilityOf(element));
		//System.out.println(element.getText().toString());
    	 
		//return element.getText().equalsIgnoreCase(text);
	 
		return element.isDisplayed(); 
			
		// assertEquals(text, element.getText());

	}
//	public static void selectCheckbox(String locatorType, String locatorValue) {
//		WebElement element;
//		element = GetElement(locatorType, locatorValue);
//		waitForPageLoaded();
//		Boolean isSelected = element.isSelected();
//
//		//Nếu chưa được check thì click vào ô check box đó
//		if(!isSelected)
//		{
//		   element.click();
//		}
//		// assertEquals(text, element.getText());
//
//	}
	public static void selectCheckbox1(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
	 
		Boolean isSelected = element.isSelected();
		if(!isSelected)
		{
		   element.click();
		}
		//Nếu chưa được check thì click vào ô check box đó
		
		// assertEquals(text, element.getText());

	}
	public static boolean verifyLabel(WebElement element, String textValue) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText().equals(textValue);
	}

	public static boolean verifyElementValue(String locatorType, String locatorValue, String value) {
		WebElement element = GetElement(locatorType, locatorValue);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getAttribute("value").equals(value);
	}

	public static boolean verifyElementExist(By elementBy) {
		// Tạo list lưu tất cả các đối tượng WebElement
		List<WebElement> listElement = driver.findElements(elementBy);

		int total = listElement.size();
		if (total > 0) {
			return true;
		}
		return false;
	}

	// wait for Javascript to Loaded
	public static void waitForJQueryLoaded() {
		ExpectedCondition<Boolean> jQueryload = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		try {
			wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
			wait.until(jQueryload);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for JQuery.");
		}
	}

	// wait for Javascript to Loaded
	public static void waitForJSLoaded() {

		ExpectedCondition<Boolean> jsload = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
			wait.until(jsload);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Javascript request.");
		}

	}

	public static void scrollToElement(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToPosition(int X, int Y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(" + X + "," + Y + ");");
	}

	public static boolean moveToElement(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).release(element).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {

			return false; 
		}
	}

	public static boolean moveToOffset(int X, int Y) {
		try {
			Actions action = new Actions(driver);
			action.moveByOffset(X, Y).build().perform();
			return true;
		} catch (Exception e) {

			return false;
		}
	} 

	public static boolean hoverElement(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean mouseHover(String locatorType, String locatorValue) {
		WebElement element;
		element = GetElement(locatorType, locatorValue);
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean dragAndDrop(String locatorType, String locatorValue1, String locatorValue2) {
		WebElement fromelement = GetElement(locatorType, locatorValue1);
		WebElement toelement = GetElement(locatorType, locatorValue2);
		try {
			Actions action = new Actions(driver);
			action.dragAndDrop(fromelement, toelement).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean dragAndDropElement(String locatorType, String locatorValue1, String locatorValue2) {
		WebElement fromelement = GetElement(locatorType, locatorValue1);
		WebElement toelement = GetElement(locatorType, locatorValue2);
		waitForPageLoaded();
		try {
			Actions action = new Actions(driver);
			action.clickAndHold(fromelement).moveToElement(toelement).release(toelement).build().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean dragAndDropOffset(String locatorType, String locatorValue, int X, int Y) {
		WebElement element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		try {
			Actions action = new Actions(driver);
			// Tính từ vị trí click chuột đầu tiên (clickAndHold)
			action.clickAndHold(element).pause(1).moveByOffset(X, Y).release().build().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean pressENTER() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean pressESC() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean pressF11() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F11);
			robot.keyRelease(KeyEvent.VK_F11);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean pressF5() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_F5);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean copy() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_COPY);
			robot.keyRelease(KeyEvent.VK_COPY);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean paste() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PASTE);
			robot.keyRelease(KeyEvent.VK_PASTE);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void color_text_danger(String locatorType, String locatorValue, String color) {
		WebElement element = GetElement(locatorType, locatorValue);
		// #dc3545
		String rgbFormat = element.getCssValue("color");
//String[] arrColor = colorString .split("#");
		System.out.println(rgbFormat); // In RGB Format the value will be print => rgba(254, 189, 105, 1)

		String hexcolor = Color.fromString(rgbFormat).asHex(); // converted Into HexFormat
		hexcolor.equals(color);
		// Output of Hex code will be => #febd69

	}

	public static void waitForWindow(WebDriver driver) throws InterruptedException {
		// wait until number of window handles become 2 or until 6 seconds are
		// completed.
		int timecount = 1;
		do {
			driver.getWindowHandles();
			Thread.sleep(200);
			timecount++;
			if (timecount > 30) {
				break;
			}
		} while (driver.getWindowHandles().size() != 2);

	}

	public static void switchToModalDialog(WebDriver driver, String parent) {
		// Switch to Modal dialog
		if (driver.getWindowHandles().size() == 2) {
			for (String window : driver.getWindowHandles()) {
				if (!window.equals(parent)) {
					driver.switchTo().window(window);
					System.out.println("Modal dialog found");
					break;
				}
			}
		}
	}
	public static void WindowHelper() throws InterruptedException {
		String parent = driver.getWindowHandle();
		waitForWindow(driver);
		if (driver.getWindowHandles().size() == 2) {
			for (String window : driver.getWindowHandles()) {
				if (!window.equals(parent)) {
//					driver.switchTo().window(parent);
//					driver.close();
					driver.switchTo().window(window);
					 
					break;
				}
			}
		}
		
	}
//	public static void dialogHelper() throws InterruptedException {
//		String parent = driver.getWindowHandle();
//		waitForWindow(driver);
//		switchToModalDialog(driver, parent);
//
//	}

	public static void close_dialog(String locatorType, String locatorValue) {
		WebElement element = GetElement(locatorType, locatorValue);
		clickElement(element);
	}

}
