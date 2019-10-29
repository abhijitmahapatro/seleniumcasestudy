package mycasestudy.testmeapp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.framework.Assert;
import utility.Drivers;

public class OnlineShoppingTest {
	WebDriver d;
	ExtentHtmlReporter htmlReporter; 
    ExtentReports extent;
    ExtentTest logger;
    @BeforeTest 
    public void startReport() {
    	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport23.html");
    	 extent = new ExtentReports();
         extent.attachReporter(htmlReporter);
         htmlReporter.config().setDocumentTitle("Extent Report Name");//extentreport command
         htmlReporter.config().setReportName("Test Report");//test report command
         htmlReporter.config().setTheme(Theme.STANDARD);//theme set 
    }
    @AfterMethod
    public void getResultAfterMethod(ITestResult result) throws IOException{
    	if(result.getStatus() == ITestResult.FAILURE) 
    	{ logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
        
        TakesScreenshot snapshot =   (TakesScreenshot)d;
        File src = snapshot.getScreenshotAs(OutputType.FILE);
        String Path = System.getProperty("user.dir") +"/test-output/screens/"+result.getName()+".png";
        FileUtils.copyFile(src, new File(Path));
        logger.addScreenCaptureFromPath(Path, result.getName());
        logger.fail(result.getThrowable());
    }
    else if(result.getStatus() == ITestResult.SUCCESS) {
        logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
    }
    else {
        logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
        logger.skip(result.getThrowable());
    }
    } 
    @AfterTest
    public void tearDown() 
    {
         //to write or update test information to reporter
        extent.flush();
    }
    
    
  @Test(priority=1)
  public void testRegistration() throws InterruptedException {
	  logger = extent.createTest("Test registration", "sucessful registration");
	  d=Drivers.getDriver("chrome");
	  d.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	  d.manage().window().maximize();
	  d.findElement(By.linkText("SignUp")).click();
	 WebElement el1= d.findElement(By.xpath("//input[@name='userName']"));
	 el1.sendKeys("kponNEW");
	 Actions act=new Actions(d);
	 act.sendKeys(Keys.ENTER).build().perform();
	 String el7=d.findElement(By.xpath("//*[@id=\"err\"]")).getText();
	 String el8 = "Available";
	 Thread.sleep(1000);
	 Assert.assertEquals(el7, el8);
//	 if(el7.equalsIgnoreCase(el8))
//	 {
	 WebElement el2= d.findElement(By.xpath("//input[@name='firstName']"));
	 el2.sendKeys("abhijit");
	 
	 WebElement el3= d.findElement(By.xpath("//input[@name='lastName']"));
	 el3.sendKeys("mahapatro");
	 
	 WebElement el4= d.findElement(By.xpath("//input[@name='password']"));
	 el4.sendKeys("Password1234");
	 
	 WebElement el5= d.findElement(By.xpath("//input[@name='confirmPassword']"));
	 el5.sendKeys("Password1234");
	 
	 d.findElement(By.xpath("//input[@name='gender' and @value='Male']")).click();
	 
	 d.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("situn1997bam@gmail.com");
	 
	 d.findElement(By.xpath("//input[@name='mobileNumber']")).sendKeys("9909978788");
	  
	 d.findElement(By.id("address")).sendKeys("suraj vihar");
	 
	 WebElement question=d.findElement(By.xpath("//select[@name='securityQuestion']"));
	 Select el6=new Select(question);
	  el6.selectByIndex(1);
	  
	  d.findElement(By.xpath("//input[@name='answer']")).sendKeys("red");
	  Thread.sleep(2000);
	  
	  d.findElement(By.xpath("//input[@name='Submit']")).click();
	  d.close();}
//	 else {
//		 System.out.println("user name already exists");
//		 Thread.sleep(2000);
//	 }
	 

	  
  
  @Test(priority=2)
  public void testLogin() {
	  logger = extent.createTest("testLogin","login the testmeapp");
	  d=Drivers.getDriver("chrome");
	  d.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	  d.manage().window().maximize();
	  d.findElement(By.linkText("SignIn")).click();
	  d.findElement(By.xpath("//input[@name='userName']")).sendKeys("lalitha");
	  d.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
	  d.findElement(By.xpath("//input[@name='Login']")).click();
		 String atext = d.getTitle();
		 String etext = "Home";
		 Assert.assertEquals(etext, atext);
//		if(atext.equals(etext))
//	 {
//		 System.out.println("login sucessful");	 
//	 }
//		 else
//		 {
//			 System.out.println("login unsucessful");
//		 }
	 d.close();
  }
  @Test(priority=3)
  public void testCart() throws InterruptedException {
	  logger =extent.createTest("testCart","testcart opening sucessfully");
	  d=Drivers.getDriver("chrome");
	  d.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	  d.manage().window().maximize();
	  d.findElement(By.linkText("SignIn")).click();
	  d.findElement(By.xpath("//input[@name='userName']")).sendKeys("lalitha");
	  d.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
	  d.findElement(By.xpath("//input[@name='Login']")).click();
	  d.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span")).click();
	  d.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/ul/li[1]/a/span")).click();
	  Thread.sleep(3000);
	  d.findElement(By.xpath("/html/body/header/div[2]/div/div/ul/li[2]/ul/li[1]/ul/li[1]/a/span")).click();
	  
	  
	  d.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
	  Thread.sleep(2000);
	 d.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
	 Thread.sleep(1000);
	 String atext = d.getTitle();
	 String etext = "View Cart";
	 Assert.assertEquals(etext, atext);
//	if(atext.equals(etext))
// {
//	 System.out.println("add to cart sucessful");	 
// }
//	 else
//	 {
//		 System.out.println("add to cart unsucessful");
//	 }
	  d.close();
  }
  @Test(priority=4)
  public void testPayment() throws InterruptedException {
	  logger =extent.createTest("testPayment","testPayment opened sucessfully");
	  d=Drivers.getDriver("chrome");
	  d.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	  d.manage().window().maximize();
	  d.findElement(By.linkText("SignIn")).click();
	  d.findElement(By.xpath("//input[@name='userName']")).sendKeys("lalitha");
	  d.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
	  d.findElement(By.xpath("//input[@name='Login']")).click();
	  d.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span")).click();//all categories
	  d.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/ul/li[1]/a/span")).click();//electronics
	  Thread.sleep(3000);
	  d.findElement(By.xpath("/html/body/header/div[2]/div/div/ul/li[2]/ul/li[1]/ul/li[1]/a/span")).click();//headphone
	  
	  
	  d.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();//addto cart
	  Thread.sleep(2000);
	 d.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();//CART
	 Thread.sleep(1000);
	 d.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();//CHECKOUT
	 Thread.sleep(1000);
	 d.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();//PROCEED TO PAY
	 Thread.sleep(6000);
	 d.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div/label/i")).click();//andhrabankbutton
	 d.findElement(By.xpath("//*[@id=\"btn\"]")).click();//continue
	 d.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456");//bank login
	 d.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456");//bank password
	 
	 d.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[3]/input")).click();//login
	 d.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input")).sendKeys("Trans@456");//transaction password
	 d.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[2]/input")).click();//bank click
	 Thread.sleep(1000);
	 String atext = d.getTitle();
	 String etext = "Order Details";
	 Assert.assertEquals(etext, atext);
//	if(atext.equals(etext))
// {
//	 System.out.println("payment sucessful");	 
// }
//	 else
//	 {
//		 System.out.println("payment unsucessful");
//	 }
	  d.close();
	  
  }
  
}
