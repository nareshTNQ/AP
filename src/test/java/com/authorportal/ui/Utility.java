package com.authorportal.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utility extends BasePage {		
	
	public static boolean clickElement(WebDriver driver, String xpath) {	
		boolean flag = false;
		try {
			driver.findElement(By.xpath(xpath)).click();
			flag= true;
		} catch (Exception e) {
			e.printStackTrace();
			}
		return flag;		
	}
	
	public static boolean typesKeys(WebDriver driver, String xpath, String sendsKeysValue) {
		boolean flag = false;
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(sendsKeysValue);
			flag= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;		
	}
	
	
	public static boolean explicitWait(WebDriver driver, int seconds, String xpath) {	
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,seconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			flag= true;
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return flag;
	}
	
	
	public static boolean explicitWaitUntilClickable(WebDriver driver, int seconds, String xpath) {	
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,seconds);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			flag= true;
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return flag;
	}
	
	public static boolean explicitWaitUntilElementIsVisiable(WebDriver driver, int seconds, String xpath) {	
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,seconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			flag= true;
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return flag;
	}
	
	
	public static long PageLoadTimeStart(WebDriver driver) {					
			 long start = System.currentTimeMillis();
			 return start;	
	}
	
	public static long PageLoadTimeEnd(WebDriver driver) {		
		 long end = System.currentTimeMillis();
		 return end;	
   }
	
	public static long totalTimeTaken(long start, long end, String webPageName) {		
		long totalTime = end - start; 
		System.out.println("Total taken to load for the page "+ webPageName + " is " + totalTime +" ms or " + totalTime/1000 + " seconds" );
		 return totalTime;	
  }	
	
	 
}
