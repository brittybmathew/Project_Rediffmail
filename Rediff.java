package testng;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Rediff {
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
	}
	@Test(priority=1)
	public void url() {
		driver.get("https://www.rediff.com");
		driver.manage().window().maximize();
	} 
	
	@Test(priority=2)
	public void home() {
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
		
		driver.findElement(By.xpath("//*[@id=\"Register\"]")).click();	
		
		Alert a=driver.switchTo().alert();
		System.out.println("Alert message is: "+a.getText());
		a.accept();
	}
		
}
