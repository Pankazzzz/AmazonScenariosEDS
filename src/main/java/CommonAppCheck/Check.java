package CommonAppCheck;
import org.testng.Assert;   


public class Check {

	public  void assertTrue(boolean condition, String message)
	{
		Assert.assertTrue(condition, message);
	}

	public  void assertTrue(boolean condition)
	{
		Assert.assertTrue(condition);
	}
	
	public  void assertEquals(boolean condition1,boolean condition2)
	{
		Assert.assertEquals(condition1,condition2);
	}

	public  void assertEquals(boolean condition1,boolean condition2, String message)
	{
		Assert.assertEquals(condition1,condition2,message);
	}
	
	public  void assertEquals(String condition1,String condition2)
	{
		Assert.assertEquals(condition1,condition2);
	}

	public  void assertEquals(String condition1,String condition2, String message)
	{
		Assert.assertEquals(condition1,condition2,message);
	}
	
}
