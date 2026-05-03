package BaseUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	
	public static ExtentReports extentReport()
	{
		String pathString = System.getProperty("user.dir")+"/Reports/index.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(pathString);
		extentSparkReporter.config().setDocumentTitle("Amazon Report");
		extentSparkReporter.config().setReportName("Amazon");
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("QA", "Pankaj");
		
		return extentReports;
	}

}
