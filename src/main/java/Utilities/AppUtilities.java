package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppUtilities  {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public Actions actions;
	public JavascriptExecutor jsExecutor;
	
	public AppUtilities(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		actions = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	public void click(String elementName) {
        this.driver.findElement(By.xpath("//*[contains(text(),'"+elementName+"']"));	
    }
	
	public void select(String tabName) {
        this.driver.findElement(By.xpath("//*[contains(text(),'"+tabName+"']"));    
    }
	
	public void scrollIntoView(WebElement element)
	{
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);	
	}

	public void invisibilityOf(WebElement element)
	{
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void invisibilityOfElementLocatedBy(By by)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void visibilityOf(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void elementToBeClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void moveToElement(WebElement element)
	{
		actions.moveToElement(element).build().perform();
	}
}
