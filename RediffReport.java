package testng;

import java.io.File;



import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class RediffReport {
	org.openqa.selenium.WebDriver driver;
	String baseurl="https://www.rediff.com";
	ExtentHtmlReporter reporter;
	ExtentTest test;
	ExtentReports extent;
	
	@BeforeTest
	public void beftest() {
		reporter=new ExtentHtmlReporter("./Reports/RediffReport.html");
		reporter.config().setDocumentTitle("AutomationReport");
		reporter.config().setReportName("Functional test");
		reporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Hostname", "Localhost");
		extent.setSystemInfo("OS", "Windows11");
		extent.setSystemInfo("TesterName", "Britty B Mathew");
		extent.setSystemInfo("BrowserName", "Chrome");
		
		driver=new ChromeDriver();
	}
		
	@Test(priority=1)
	public void url() {
		test=extent.createTest("Url_Loading");
		driver.get("https://www.rediff.com");
		driver.manage().window().maximize();
	}
	
	@Test(priority=2)
	public void home() {
		test=extent.createTest("Logo_Verification");
		boolean logo=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/span")).isDisplayed();
		if(logo) {
			System.out.println("Rediff logo is displayed");
		}
		else {
			System.out.println("Rediff logo is not displayed");
		}
		driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[2]")).click();
	}
	
	@Test(priority=3)
	public void createaccount() {
		test=extent.createTest("Checkbox_Test");
		boolean avbutton=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[2]")).isEnabled();
		if(avbutton) {
			System.out.println("Check availability button is enabled");
		}
		else {
			System.out.println("Check availability button is disabled");
		}
		boolean checkbox=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[15]/td[2]/table/tbody/tr/td[1]/input")).isEnabled();
		if(checkbox) {
			System.out.println("Checkbox is enabled");			
		}
		else {
			System.out.println("Checkbox is disabled");
		}
	}
	@Test(priority=4)
	public void fillfields() {
		test=extent.createTest("Fields_Test");
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[3]/td[3]/input")).sendKeys("abcd efg");
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[1]")).sendKeys("abcd.2023");
		
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[2]")).click();
		
		
		driver.findElement(By.xpath("//*[@id=\"newpasswd\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"eyeiconNew\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"newpasswd1\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"eyeiconRe\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"eyeiconNew\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"eyeiconRe\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[15]/td[2]/table/tbody/tr/td[1]/input")).click();
		
		WebElement qstn=driver.findElement(By.xpath("//*[@id=\"div_hintQS\"]/table/tbody/tr[2]/td[3]/select"));
		Select secqstn=new Select(qstn);
	    secqstn.selectByValue("What is your favourite pass-time?");
		
	    driver.findElement(By.xpath("//*[@id=\"div_hintQS\"]/table/tbody/tr[4]/td[3]/input")).sendKeys("Reading");
	    driver.findElement(By.xpath("//*[@id=\"div_hintQS\"]/table/tbody/tr[6]/td[3]/input")).sendKeys("abcdefgh");
	    driver.findElement(By.xpath("//*[@id=\"mobno\"]")).sendKeys("9876543201");
	    
	    WebElement day=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[1]"));
	    Select birthday=new Select(day);
	    birthday.selectByValue("03");
	    WebElement month=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[2]"));
		Select birthmonth=new Select(month);
		birthmonth.selectByValue("04");
		WebElement year=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[3]"));
		Select birthyear=new Select(year);
		birthyear.selectByValue("2000");
		
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[24]/td[3]/input[2]")).click();
		
		WebElement city=driver.findElement(By.xpath("//*[@id=\"div_city\"]/table/tbody/tr[1]/td[3]/select"));
		Select cityname=new Select(city);
		cityname.selectByValue("Cochin");
		
		driver.findElement(By.xpath("//*[@id=\"Register\"]")).click();
		
	}
	
	@Test(priority=5)
	public void createbutton() {
		test=extent.createTest("Button_Test");
		driver.findElement(By.xpath("//*[@id=\"Register\"]")).click();		
		Alert a=driver.switchTo().alert();
		System.out.println("Alert message is: "+a.getText());
		a.accept();
	}
	@AfterTest
	public void teardown() {
		extent.flush();
	}
	
	@AfterMethod
	public void browserclose(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "testcase failed is "+result.getName());
			test.log(Status.FAIL, "testcase failed is "+result.getThrowable());
			String screenshotpath=ExtentedReports.screenshotMethod(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "testcase skipped is "+result.getName());
			String screenshotpath=ExtentedReports.screenshotMethod(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "testcase passed is "+result.getName());
			String screenshotpath=ExtentedReports.screenshotMethod(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
		}
	}

	static String screenshotMethod(WebDriver driver, String screenshotname) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destination="./Screenshot/"+screenshotname+".png";
		FileHandler.copy(src, new File(destination));
		
		return destination;
	}
		
}
