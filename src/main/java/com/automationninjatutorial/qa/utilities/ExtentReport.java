package com.automationninjatutorial.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportFile);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setReportName("Tutorial Ninja Test Automation Results.");
		extentSparkReporter.config().setDocumentTitle("TN Auomtion Report");
		extentSparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(extentSparkReporter);
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\automationninjatutorial\\qa\\config\\config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();			
		}
		extentReport.setSystemInfo("Allication URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Email", prop.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Usernmae", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
		
	}

}
