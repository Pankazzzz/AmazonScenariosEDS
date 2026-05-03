package CommonAppCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AppUtilities;


public class Detail extends AppUtilities{
	
	WebDriver driver;
	
	public Detail(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "a[aria-label*='Amazon.in']")
	WebElement appNamElement;
	
	public String getApplicationName()
	{
	 	return appNamElement.getAttribute("aria-label");
	}
	
}



