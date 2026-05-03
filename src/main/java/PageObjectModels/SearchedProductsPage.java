package PageObjectModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AppUtilities;

public class SearchedProductsPage extends AppUtilities {

	private WebDriver driver;
	
	public SearchedProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//h2[text()='Results']")
	WebElement resultsElement;
	
	@FindBy(xpath = "//div[@role=\"listitem\" and @data-component-type=\"s-search-result\"]")
	List<WebElement> listOfAllProductElements;
	
	@FindBy(xpath = "//div[@id='feature-bullets']")
	WebElement aboutElement;
	
	
	By productNameBy = By.xpath(".//h2");
	By priceBy = By.xpath(".//span[@class='a-price-whole']");
	By productDescListBy = By.xpath("./li/span[@class='a-list-item']");
	By productBy =By.xpath(".//h2/..");
	
	public List<WebElement> getAllProducts() {
		visibilityOf(resultsElement);
		return listOfAllProductElements;
	}
	
	public int[] getMinMaxProductIndexes()
	{

		int productSize = listOfAllProductElements.size();
		int lowestIndex=-1,highestIndex=-1;
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		
		for(int i=0;i<productSize;i++)
		{
			WebElement product = listOfAllProductElements.get(i);
			try {
				String name = product.findElement(productNameBy).getText();
				if(name==null)
					continue;
				
				WebElement priceElement = product.findElement(priceBy);
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
		
		return new int[] {lowestIndex,highestIndex};
	}
	
	public void printHighestLowestProductDetails(int lowestIndex,int highestIndex)
	{
		System.out.println("Product with minimum price is => "+getAllProducts().get((int) lowestIndex).findElement(productBy).getText());
		System.out.println("Product with minimum price is => "+getAllProducts().get((int) highestIndex).findElement(productBy).getText());
	}
	
	public void openHighestLowestProductsInNewTab(int lowestIndex,int highestIndex)
	{
		String keyString = Keys.chord(Keys.COMMAND,Keys.ENTER);
		listOfAllProductElements.get((int) lowestIndex).findElement(productBy).sendKeys(keyString);
		listOfAllProductElements.get((int) highestIndex).findElement(productBy).sendKeys(keyString);
	}

	public List<String> getProductDetails(String parentHandle,Set<String> handles) {
		
		List<String> productDesc = new ArrayList<String>();
		String parentWindow = parentHandle;
		Set<String> windowHandles = handles;
		

		
		for(String productInNewTabIterator : windowHandles)
		{
			
			if(!productInNewTabIterator.equals(parentWindow)) {
				
			driver.switchTo().window(productInNewTabIterator);
			visibilityOf(aboutElement);
			moveToElement(aboutElement);
			WebElement parentAboutThisElement = driver.findElement(By.xpath("//h2[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='about this item']/parent::div/ul"));
			List<WebElement> listItems = parentAboutThisElement.findElements(By.xpath("./li/span[@class='a-list-item']"));
			for(WebElement aboutItemElement : listItems)
			{
				productDesc.add(aboutItemElement.getAttribute("textContent").trim());			
			}
			
		}
		}
		

		return productDesc;

	}


	
}

