package com.authorportal.ui;


import java.util.List;

import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.collections.Lists;



public class Author extends BasePage {	
	
	// Using  Jar
	
	  static String URL; 
	  static String username;
	  static String password ;
	  static String load ;
	  static int loadTime;

	public static void main(String[] args) {
		URL=args[0];
	    username=args[1];
	    password=args[2];
	    load=args[3];
	    loadTime=Integer.valueOf(load);
	    TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add("./testng.xml");	   
	    testng.setTestSuites(suites);	  
	    testng.run();	    
	    
	}	
	
//	  static String URL = System.getProperty("URL"); static String username =
//	  System.getProperty("username");
//	  static String password = System.getProperty("password"); 
//	  static String load = System.getProperty("pageLoadTime"); 
//	  static int loadTime =  Integer.parseInt(load);	 
	  
	  // Live
//	  static String URL = "https://publish.sciencejournals.ru/login"; 
//	  static String username = "journalofficer1@sciencejournals.ru"; 
//	  static String password = "Test@1234"; 
//	  static int loadTime = 35;
	
	
	@Test(priority = 1)
	public void Login_Page() {
		long totalTime = 0;
		try {
			    
			  driver.get(URL);
			  BasePage.CreateTest("Login Page");
			  long start = Utility.PageLoadTimeStart(driver);
			  boolean status =  Utility.explicitWait(driver, loadTime,"//input[@name='username']"); 
			  long end = Utility.PageLoadTimeStart(driver);
			  totalTime = Utility.totalTimeTaken(start, end, "Login Page");
			  if(status==true) {
					 BasePage.statusPass("Login page is launched successfully"); 
					 BasePage.statusInfo("Total time taken to load - Login Page is " + totalTime + " ms or " + totalTime/1000 + " seconds");
				 }else if(status==false) {
					 BasePage.statusFail("Login page is launched not successfully and took time to load more than specific time");
					 BasePage.statusInfo("Time taken to load for Login Page is more than " + totalTime + " ms or " + totalTime/1000 + " seconds");
				 }	
			  Assert.assertEquals(status, true);
		} catch (Exception | AssertionError e){
			e.printStackTrace();			
			Assert.fail();
		}
	}

	//
	@Test(priority = 2,dependsOnMethods = {"Login_Page"})
	public void Journal_Dashboard() {
		long totalTime = 0;
		try {	
			BasePage.CreateTest("Jorunal Dashboard Page");
			Utility.typesKeys(driver, "//input[@name='username']", username);
			Utility.typesKeys(driver, "//input[@name='password']", password);
			Utility.clickElement(driver, "//button[.='LOGIN']");
			long start = Utility.PageLoadTimeStart(driver);
			boolean status = Utility.explicitWait(driver, loadTime, "(//span[@class='jo-dashboard__accordionlink'])[1]");						
			long end = Utility.PageLoadTimeStart(driver);
			totalTime = Utility.totalTimeTaken(start, end, "Journal Page");
			if(status==true) {
				 BasePage.statusPass("Journal Dashboard Page is launched successfully"); 
				 BasePage.statusInfo("Total time taken to load - Journal Dashboard Page is " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }else if(status==false) {
				 BasePage.statusFail("Journal Dashboard Page is launched not successfully and took time to load more than specific time");
				 BasePage.statusInfo("Time taken to load for Journal Dashboard Page is more than " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }	
			
			Assert.assertEquals(status, true);
		} catch (Exception | AssertionError e)  {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 3,dependsOnMethods = {"Journal_Dashboard"})
	public  void Article_Dashboard() {
		long totalTime = 0;
		try {
			BasePage.CreateTest("Article Dashboard Page");
			Utility.clickElement(driver, "(//span[@class='jo-dashboard__accordionlink'])[1]");
			long start = Utility.PageLoadTimeStart(driver);
			boolean status = Utility.explicitWait(driver, loadTime, "(//p[@class='artical-dashboard-list-article-name'])[1]");
			long end = Utility.PageLoadTimeStart(driver);
			totalTime =  Utility.totalTimeTaken(start, end, "Article Page");
			if(status==true) {
				 BasePage.statusPass("Article Dashboard Page is launched successfully"); 	
				 BasePage.statusInfo("Total time taken to load - Article Dashboard Page is " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }else if(status==false) {
				 BasePage.statusFail("Article Dashboard Page is launched not successfully and took time to load more than specific time");
				 BasePage.statusInfo("Time taken to load for Article Dashboard Page is more than " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }		
			 
			  Assert.assertEquals(status, true);
		} catch (Exception | AssertionError e)  {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(priority = 4,dependsOnMethods = {"Article_Dashboard"})
	public void Article_Detail_Page() {
		long totalTime = 0;
		try {
			BasePage.CreateTest("Article Detail Page");
			Utility.clickElement(driver, "(//p[@class='artical-dashboard-list-article-name'])[1]");
			long start = Utility.PageLoadTimeStart(driver);
			boolean status =Utility.explicitWait(driver, loadTime, "//a[text()='Show Submitted Files']");
			long end = Utility.PageLoadTimeStart(driver);
			totalTime = Utility.totalTimeTaken(start, end, "Article Detail Page");
			 if(status==true) {
				 BasePage.statusPass("Article Detail Page is launched successfully"); 	
				 BasePage.statusInfo("Total time taken to load - Article Detail Page is " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }else if(status==false) {
				 BasePage.statusFail("Article Detail Page is launched not successfully and took time to load more than specific time");
				 BasePage.statusInfo("Time taken to load for Article Detail Page is more than " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }	
			
			Assert.assertEquals(status, true);
		} catch (Exception | AssertionError e)  {
			e.printStackTrace();
			Assert.fail();
		}
	}	
	
	@Test(priority = 5,dependsOnMethods = {"Article_Detail_Page"})
	public void Logout() {
		long totalTime = 0;
		try {
			BasePage.CreateTest("Logout");
			Thread.sleep(4000);
			Utility.clickElement(driver, "//div[@class='circle toggle']");
			Utility.explicitWait(driver, 4, "//div[.='Logout']");
			Utility.clickElement(driver, "//div[.='Logout']");
			long start = Utility.PageLoadTimeStart(driver);
		    boolean status = Utility.explicitWait(driver, loadTime, "//button[.='LOGIN']");		   		
			long end = Utility.PageLoadTimeStart(driver);
			totalTime = Utility.totalTimeTaken(start, end, "Logout Page");
			 if(status==true) {
				 BasePage.statusPass("Logout is successfully done"); 	
				 BasePage.statusInfo("Total time taken to load - Logout is " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }else if(status==false) {
				 BasePage.statusFail("Logout is not successfully done and took time to load more than specific time");
				 BasePage.statusInfo("Time taken to load for Logout is more than " + totalTime + " ms or " + totalTime/1000 + " seconds");
			 }			
			
			driver.quit();
			Assert.assertEquals(status, true);			
		} catch (Exception | AssertionError e)  {
			e.printStackTrace();
			Assert.fail();
		}
	}
	

}
