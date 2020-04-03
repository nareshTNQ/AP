package com.authorportal.ui;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ap.files.FilesProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class BasePage {	
	private static String failedCaseName="";
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest test;
	public static String timeStamp;
	public static List<Integer> totalTestCases=new ArrayList<Integer>();
	public static List<Integer> failedTestCases=new ArrayList<Integer>();
	public static List<Integer> passedTestCases=new ArrayList<Integer>();
	protected WebDriver driver;
	
	@BeforeSuite
	public  void reportGenerator() throws IOException {
		
		htmlReporter = new ExtentHtmlReporter(FilesProvider.extentReportFilePath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);	
		 
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
//		ChromeOptions options = new ChromeOptions(); 
//		options.addArguments("--headless", "--disable-gpu","--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
	}
	
	
	@BeforeMethod
	public static void createTestExtent() {		
		totalTestCases.add(1);
	}
	
	public static void CreateTest(String testcaseName) {		
		test = extent.createTest(testcaseName);				
	}
	
	
	public static void statusInfo(String info) {		
		test.log(com.aventstack.extentreports.Status.INFO, info);				
	}
	
	public static void statusPass(String pass) {		
		test.log(com.aventstack.extentreports.Status.PASS, pass);							
	}
	
	public static void statusFail(String fail) {		
		test.log(com.aventstack.extentreports.Status.FAIL, fail);						
	}
	
	@AfterSuite
	public void flush() {	
		buildReport(totalTestCases.size(), passedTestCases.size(), failedTestCases.size());
		extent.flush();
		driver.quit();
	}
	
	
	public static void buildReport(int testedCases,int passedCases,int failedCases) {
		StringBuilder builder = new StringBuilder();
		
		try {
            timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH").format(new Date());
			builder.append("<html>"
					+ "<head><style>table {border-collapse: collapse;}"
					+ "table, td, th {border: 2px solid black;font-weight: bold;}</style></head>"
					+ "<body><center><img src='http://pgc-dev-test.s3.amazonaws.com/salomanat/images/final.png' alt='SalmonAT' height='25%' width='25%'></center>"
					+ "<p>Hi All,<p>"
					+ "<p>PFB. Author portal Live Monitoring Status.</p>"
					+ "<table>"
					+ "<tr>"
					+ "<td><font color='Purple'>TestCases: "+testedCases+"</font></td><td><font color='Green'>Passed: "+passedCases+"</font></td><td><font color='Red'>Failed: "+failedCases+"</font></td>"
					+ "</tr>"					
					+ "</table>"
					+ "<p><font color=\"red\">"+failedCaseName+" is failed"+"</font></p>"
					+ "<p><a href ='http://autotestresult.tnq.co.in//Projects/AutomationTesting/AuthorPortal/Live_Monitoring/TestReport_"+timeStamp+".html'>Click here </a>to view the result.</p>"
					+ "<p><b>Note:</b> This is an automated mail. Do not reply to this mail. If you have any queries reply to this mail id "
					+ "<u>karthik.nithianandam@tnqsoftware.co.in</u></p>"
					+ "<p>Thanks &amp; regards,</p>"
					+ "<p>TestLab.</p>"
					+ "<pre>******* Happy Testing *******</pre>"
					+ "</blockquote>"
					+ "<br>"
					+ "</html>");
			
			File summaryLocation = new File("./Summary.html");
			if(summaryLocation.exists()) {
				summaryLocation.delete();
				Thread.sleep(5000);
				summaryLocation.createNewFile();
			}
			OutputStream outputstream = new FileOutputStream(summaryLocation);
			Writer writer = new OutputStreamWriter(outputstream);
			writer.write(builder.toString());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {				
				failedTestCases.add(result.getStatus());
				failedCaseName=result.getName();
				throw new SkipException("Testcase failed");
				
			}else if (result.getStatus() == ITestResult.SUCCESS) {				
				passedTestCases.add(result.getStatus());
			}
		}catch (Exception e) {
			
			if(e.getMessage().contains("Testcase failed")) 
			{
				throw new SkipException("Testcase failed");
			}
			
			
		}
	
	}
	
	
	
}
