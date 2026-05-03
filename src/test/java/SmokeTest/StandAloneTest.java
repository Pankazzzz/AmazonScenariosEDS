package SmokeTest;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest {
	
	@Test(priority = 1)
	public void endToEndFlow() throws Exception
	{
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String logoNameString = driver.findElement(By.cssSelector("a[aria-label*='Amazon.in']")).getAttribute("aria-label");
		
		Actions actions = new Actions(driver);
		WebElement signElement = driver.findElement(By.xpath("(//a[contains(@href,'signin?openid')])[1]"));
		actions.moveToElement(signElement).build().perform();
		driver.findElement(By.xpath("(//span[text()='Sign in'])[1]")).click();
		Thread.sleep(2000);
		WebElement emailLogin = driver.findElement(By.xpath("//*[@type='email']"));
		waitDriver.until(ExpectedConditions.visibilityOf(emailLogin));
		emailLogin.sendKeys("shuklapankaj799@gmail.com");
		driver.findElement(By.className("a-button-input")).click();
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("9022575112@");
		driver.findElement(By.cssSelector("[type='submit']")).click();	
		Assert.assertEquals(logoNameString, "Amazon.in");
		
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Mobile");
		driver.findElement(By.cssSelector("[value=\"Go\"]")).click();
		waitDriver.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Results']"))));
		List<WebElement> listOfAllProductElements = driver.findElements(By.xpath("//div[@role=\"listitem\" and @data-component-type=\"s-search-result\"]"));
		int productSize = listOfAllProductElements.size();
		int lowestIndex=-1,highestIndex=-1;
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		System.out.println(productSize);
		for(int i=0;i<productSize;i++)
		{
			WebElement product = listOfAllProductElements.get(i);
			try {
				String name = product.findElement(By.xpath(".//h2")).getText();
				if(name==null)
					continue;
				
				WebElement priceElement = product.findElement(By.xpath(".//span[@class='a-price-whole']"));
		        String priceText = priceElement.getText();
		        if (priceText.isEmpty()) continue;

		        long price = Long.parseLong(priceText.replace(",", "").trim());

		        if (price < min) {
		            min = price;
		            lowestIndex = i;
		        }
		        if (price > max) {
		            max = price;
		            highestIndex = i;
		        }
			}catch (Exception e) {
				System.out.println("Error occured on product search");
			}
		}
		
		
		System.out.println(lowestIndex);
		System.out.println(highestIndex);
		
		System.out.println("Product with minimum price is => "+listOfAllProductElements.get((int) lowestIndex).findElement(By.xpath(".//h2")).getText());
		System.out.println("Product with minimum price is => "+listOfAllProductElements.get((int) highestIndex).findElement(By.xpath(".//h2")).getText());
		
		
		String keyString = Keys.chord(Keys.COMMAND,Keys.ENTER);
		listOfAllProductElements.get((int) lowestIndex).findElement(By.xpath(".//h2/..")).sendKeys(keyString);
		listOfAllProductElements.get((int) highestIndex).findElement(By.xpath(".//h2/..")).sendKeys(keyString);

		
		String parentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for(String productInNewTabIterator : windowHandles)
		{
			if(!productInNewTabIterator.equals(parentWindow)) {
				
			driver.switchTo().window(productInNewTabIterator);
			waitDriver.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='feature-bullets']"))));
			actions.moveToElement(driver.findElement(By.xpath("//div[@id='feature-bullets']")));
			WebElement parentAboutThisElement = driver.findElement(By.xpath("//h2[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='about this item']/parent::div/ul"));
			List<WebElement> listItems = parentAboutThisElement.findElements(By.xpath("./li/span[@class='a-list-item']"));
			for(WebElement aboutItemElement : listItems)
			{
				System.out.println(aboutItemElement.getAttribute("textContent").trim());			}
			}
		}
		
		


		driver.close();
	}
	

}
