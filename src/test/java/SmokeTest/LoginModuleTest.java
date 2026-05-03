package SmokeTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BasePackage.BaseClass;
import CommonAppCheck.Check;
import DataReaderFunction.DataProviderClass;
import DataResources.TestCaseDetails;

public class LoginModuleTest extends BaseClass {
	
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "getData", groups = {"smokeTest"})
	public void loginModuleTest001(HashMap<String, String> data) throws IOException, InterruptedException
	{
		
		/*--------------------------	COMMON START -----------------------------*/
		
		Properties configProperties = TestCaseDetails.getTestCaseDetails();
		Check check =  new Check();
		SoftAssert softAssertCheck = new SoftAssert();
		
		/*--------------------------	COMMON START -----------------------------*/
		
		amazonPage.landingPage.clickOnSignInButton();
		amazonPage.landingPage.putEmailCred(data.get("email"));
		amazonPage.landingPage.putPassword(data.get("password"));
		amazonPage.landingPage.signIn();
		softAssertCheck.assertEquals(amazonPage.detail.getApplicationName(),"Amazon.in","Application name not matching");			
		
		/*--------------------------	COMMON End -----------------------------*/
		//logout function we can make here
		softAssertCheck.assertAll();
		/*--------------------------	COMMON End -----------------------------*/
		
	}

}
