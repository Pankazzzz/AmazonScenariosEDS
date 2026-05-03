package PageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import CommonAppCheck.Detail;

public class AmazonPage {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public Detail detail;
	public SearchedProductsPage searchedProductsPage;
	
	public AmazonPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		landingPage = new LandingPage(driver);
		detail = new Detail(driver);
		searchedProductsPage = new SearchedProductsPage(driver);
	}
	
	public LandingPage getLandingPage()
	{
		return landingPage;
	}
	
	public Detail getdetailPage()
	{
		return detail;
	}
	
	public SearchedProductsPage getSearchPage()
	{
		return searchedProductsPage;
	}

}
