package taketest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonFunctions {
	public static WebDriver driver = null;
	public boolean browserAlreadyOpen = false;
	public static Properties SYSPARAM = null;

	// To Initialize .properties file.
	public void initData() throws IOException {
		SYSPARAM = new Properties();
		FileInputStream Ist = new FileInputStream(
				System.getProperty("user.dir") + "//src//taketest//SYSPARAM.properties");
		SYSPARAM.load(Ist);
	}
	public void initBrowser() {
		/*// Check If browser Is already opened during previous test execution.
		if (!browserAlreadyOpen) {
			if (SYSPARAM.getProperty("BrowserToTestIn").equals("MFF")) {
				System.setProperty("webdriver.gecko.driver", "D:\\Automation\\Setup\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (SYSPARAM.getProperty("BrowserToTestIn").equals("CHRM")) {
				driver = new ChromeDriver();
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			browserAlreadyOpen = true;
		}*/
		
		System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Setup\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        driver = new ChromeDriver(options);
        System.out.print("initBrowser()");

	}

	// To Close Browser
	public void closeBrowser() {
		driver.quit();
		browserAlreadyOpen = false;
		System.out.println("close()");
	}

	public boolean isAlreadyLogIn = false;

	public void redirectSignIn() {
		driver.findElement(By.xpath(".//*[@id='section1']/section/div[1]/div/div/div/div[2]/nav/ul[2]/a[1]/button"))
				.click();
	}

	// Can accept userID and password as a string
	public void logIn(String userID, String password) {
		// To check If already login previously then don't execute this function.
		if (!isAlreadyLogIn) {
			// If Not login then login In to your account.
			driver.findElement(By.xpath(".//*[@id='edit-name']")).sendKeys(userID);
			driver.findElement(By.xpath(".//*[@id='edit-pass']")).sendKeys(password);
			driver.findElement(By.xpath(".//*[@id='edit-submit']")).click();
			isAlreadyLogIn = true;
		}
	}

	public void redirectCheckout() throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='div2']/div[1]/div/div/ul/li[1]/div/div[4]/div[2]/div")).click();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='edit-continue']")));
		System.out.print(driver.getCurrentUrl());
		driver.findElement(By.xpath(
				".//*[@id='commerce-checkout-form-checkout']/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div/div"))
				.click();
		driver.findElement(By.xpath(
				".//*[@id='commerce-checkout-form-checkout']/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div/ul/li[2]"))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='edit-continue']")));

	}

	public void logOut() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='userNavigationLabel']")));
		driver.findElement(By.xpath(".//*[@id='userNavigationLabel']")).click();
		driver.findElement(By.xpath(".//*[@id='js_8c']/div/div/ul/li[12]/a/span/span")).click();
		isAlreadyLogIn = false;
	}
}
