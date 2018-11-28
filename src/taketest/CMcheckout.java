package taketest;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CMcheckout extends CommonFunctions {
	@Test
	@Parameters({ "sUsername", "sPassword" })
	public void f(String sUsername, String sPassword) throws InterruptedException {
		driver.get("http://dev.campusmate.com/");
		System.out.print("http://dev.campusmate.com/");
		redirectSignIn();
		System.out.print("redirectSignIn");
		logIn(sUsername, sPassword);

		redirectCheckout();
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		//initData();
		initBrowser();
	}

	@AfterTest
	public void afterTest() {
		closeBrowser();
	}

}
