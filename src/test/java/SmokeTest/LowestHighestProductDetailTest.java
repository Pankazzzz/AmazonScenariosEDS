package SmokeTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BasePackage.BaseClass;
import BaseUtilities.RetryAnalyzer;
import CommonAppCheck.Check;
import DataResources.TestCaseDetails;
import ExcelUtilities.GetExcelData;

public class LowestHighestProductDetailTest  extends BaseClass{
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void lowestToHighestTest002(Method m) throws Exception {
		
		

		/*--------------------------	COMMON START -----------------------------*/
		
		Properties configProperties = TestCaseDetails.getTestCaseDetails();
		String testcaseDetails = configProperties.getProperty(m.getName());
		HashMap<String, String> data = GetExcelData.getDataForTheTestCase(testcaseDetails);
		Check check =  new Check();
		SoftAssert softAssertCheck = new SoftAssert();
		
		/*--------------------------	COMMON START -----------------------------*/
		
		System.out.println(data);
		amazonPage.landingPage.searchProduct(data.get("SearchCriteria"));
		int[] indexesOfHighLowestPriceProduct = amazonPage.searchedProductsPage.getMinMaxProductIndexes();
		int lowestPriceProduct = indexesOfHighLowestPriceProduct[0];
		int highestPriceProduct = indexesOfHighLowestPriceProduct[1];
		amazonPage.searchedProductsPage.printHighestLowestProductDetails(lowestPriceProduct, highestPriceProduct);
		amazonPage.searchedProductsPage.openHighestLowestProductsInNewTab(lowestPriceProduct, highestPriceProduct);
		List<String> productDetailsList = amazonPage.searchedProductsPage.getProductDetails(getWindowHandle(),getWindowHandles());
		for(String prodDesc : productDetailsList)
		{
			logger.info(" => {}", prodDesc);
		}
		
		 
		/*--------------------------	COMMON End -----------------------------*/
		softAssertCheck.assertAll();
		/*--------------------------	COMMON End -----------------------------*/

	}

}
