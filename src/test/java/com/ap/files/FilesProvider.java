package com.ap.files;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author 9395
 *
 */
public class FilesProvider {

	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH").format(new Date());
//	public static String extentReportFilePath="//tnqfs07/TESTING_SERVICES/Projects/AutomationTesting/AuthorPortal/Live_Monitoring/TestReport_"+timeStamp+".html";
	public static String extentReportFilePath = "./reports/AuthorPortal_LiveMonitoring.html";
	 

}
