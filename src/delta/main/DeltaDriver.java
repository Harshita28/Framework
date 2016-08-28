package delta.main;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;
import generics.Property;

public class DeltaDriver extends BaseDriver{
	public WebDriver driver;
	public ExtentReports eReport;
	public ExtentTest testReport;
	
	@BeforeSuite
	public void initFrameWork(){
		eReport= new ExtentReports("./report/results.html");
	}
	
	@BeforeMethod
	public void launchApp()
	{
		driver=new FirefoxDriver();
		String configPath = "./config/config.properties";
		String appURL=Property.getPropertyValue(configPath, "URL");
		String timeOut = Property.getPropertyValue(configPath, "TimeOut");
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(timeOut), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	//@Test(dataProviderClass=BaseDriver.class,dataProvider="getScenarios")
	@Test(dataProvider="getScenarios")
	public void testSceanrios(String scenarioSheet){
		
		String scenariosPath="./scripts/Scenarios.xlsx";
		//String scenarioSheet="Scenario1";
		testReport = eReport.startTest(scenarioSheet);
		int stepCount=Excel.getRowCount(scenariosPath, scenarioSheet);
		
		for (int i=1 ; i<stepCount; i++){
		String description = Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
		String action = Excel.getCellValue(scenariosPath, scenarioSheet, i,1);
		String input1 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
		String input2 = Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
		testReport.log(LogStatus.INFO,"description: "+description+" action: "+action+" input1: "+input1+" input2: "+input2);
		KeyWord.executeKeyWord(driver, action, input1, input2);
		}
	}
	
	@AfterMethod
	public void quitApp(){
		driver.close();
		eReport.endTest(testReport);
	}
	
	@AfterSuite
	public void endFrameWork(){
		eReport.flush();
	}
}
