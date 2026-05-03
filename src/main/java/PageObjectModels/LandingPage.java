package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.AppUtilities;
import Utilities.HelperUtilities;

public class LandingPage extends AppUtilities {
	
	
	private WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[contains(@href,'signin?openid')])[1]")
	WebElement moveToSignIn;
	
	@FindBy(xpath = "(//span[text()='Sign in'])[1]")
	WebElement signInElement;
	
	@FindBy(xpath = "//*[@type='email']")
	WebElement emailLogin;
	
	@FindBy(className = "a-button-input")
	WebElement continuElement;
	
	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordElement;
	
	@FindBy(css = "[type='submit']")
	WebElement submitElement;
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searhBoxElement;
	
	@FindBy(css="[value=\"Go\"]")
	WebElement searchElement;
	
	
	public void clickOnSignInButton()
	{
		moveToElement(moveToSignIn);
		signInElement.click();
	}
	
	public void putEmailCred(String email) throws InterruptedException {
		visibilityOf(emailLogin);
		emailLogin.sendKeys(email);
		continuElement.click();
		HelperUtilities.sleep(2000); // pls comment it while u run, in my pc savekeys enabled so had to put it 
	}
	
	public void putPassword(String password)
	{
		visibilityOf(passwordElement);
		passwordElement.sendKeys(password);
	}
	
	public void signIn()
	{
		submitElement.click();
	}
	
	public void searchProduct(String productName) {
		searhBoxElement.sendKeys(productName);
		searchElement.click();
	}
}
