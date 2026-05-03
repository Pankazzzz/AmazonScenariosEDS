package CommonAppCheck;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;   


public class SoftAssertCheck {
	
	public SoftAssert assert1;
	
	public SoftAssertCheck(){
	 assert1 = new SoftAssert();
	}
	
	public  void assertTrue(boolean condition, String message)
	{
		assert1.assertTrue(condition, message);
	}

	public  void assertTrue(boolean condition)
	{
		assert1.assertTrue(condition);
	}
	
	public  void assertEquals(boolean condition1,boolean condition2)
	{
		assert1.assertEquals(condition1,condition2);
	}

	public  void assertEquals(boolean condition1,boolean condition2, String message)
	{
		assert1.assertEquals(condition1,condition2,message);
	}
	
	public  void assertEquals(String condition1,String condition2)
	{
		assert1.assertEquals(condition1,condition2);
	}

	public  void assertEquals(String condition1,String condition2, String message)
	{
		assert1.assertEquals(condition1,condition2,message);
	}
	
	public void throwExceptionIfAny()
	{
		assert1.assertAll();
	}
	
}
