package BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;
import java.util.logging.FileHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjectModels.AmazonPage;
import PageObjectModels.LandingPage;


public class BaseClass {
	
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public AmazonPage amazonPage;
	public static  final Logger logger = LogManager.getLogger();
	
    public WebDriver getDriver() {
    	return driver.get();
	}
	 
	public  void init() throws IOException
	{

		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/TestResources/config.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String browserNameString = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
		
		ChromeOptions cpChromeOptions = new ChromeOptions();
		cpChromeOptions.setAcceptInsecureCerts(true);
		
		if(browserNameString.equalsIgnoreCase("chrome"))
		{
			driver.set(new ChromeDriver());
		}else if(browserNameString.equalsIgnoreCase("edge"))
		{
			driver.set(new EdgeDriver());
		}if(browserNameString.equalsIgnoreCase("chrome headless"))
		{
			cpChromeOptions.addArguments("--headless");
			driver.set(new ChromeDriver(cpChromeOptions));
		}
		
		getDriver().get(properties.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
	}
	
	
	
	
	public String getScreenCaptureFromPath(String testcaseName) throws IOException
	{
		TakesScreenshot tScreenshot = (TakesScreenshot)getDriver();
		File souFile =tScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"/Reports/"+testcaseName+".jpg");
		org.openqa.selenium.io.FileHandler.copy(souFile, destFile);
		return destFile.getAbsolutePath();
	}
	
	public String getWindowHandle()
	{
		return getDriver().getWindowHandle();
	}
	

	public Set<String> getWindowHandles()
	{
		return getDriver().getWindowHandles();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() throws IOException
	{
		init();
		logger.info("Start");
		amazonPage = new AmazonPage(getDriver());
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeInstance() {
		logger.info("End");
	    if (getDriver() != null) {
	    	getDriver().quit();
	    	driver.remove();;
	    }
	}


}
