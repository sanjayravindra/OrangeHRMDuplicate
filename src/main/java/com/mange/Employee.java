package com.mange;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.properties.Database_validation;
import com.properties.Property;

public class Employee extends Property
{
	static Logger log = Logger.getLogger(Employee.class);	
	static WebDriver driver;
	
	public void navigate()
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\sanjay.ravindra\\eclipse-workspace\\OrangeHRM\\src\\main\\resources\\Driver\\chromedriver.exe"); 
	driver = new ChromeDriver(); 
	driver.manage().window().maximize(); 
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	log.info("Browser Opened");
	}
	
	
	public void login() throws Throwable
	{
		log.info("Login Page");
		super.screenshot(driver, "loginpage");
		driver.get("http://127.0.0.1/orangehrm/symfony/web/index.php/auth/login");
		driver.findElement(By.id("txtUsername")).sendKeys(super.getProperty("username"));
		
		driver.findElement(By.id("txtPassword")).sendKeys(super.decode(super.getProperty("password")));
		log.info("Credentials entered");
		driver.findElement(By.id("btnLogin")).click();
		log.info("User login to the application");
		super.screenshot(driver, "Dashboard");
		log.info("Dashboard");
	}

	
	public void addEmployee(String sheetname,String edit,String search) throws Throwable
	{		
		String e = "Yes";
		String s= "No";
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		log.info("PIM Module page");
		super.screenshot(driver, "PIM");
	
		
		Thread.sleep(500);
		for (int j = 1; j <=super.getrow(sheetname) ; j++) 
		{

			if( edit.equalsIgnoreCase(super.getcelldata(sheetname, "edit", j)) && search.equalsIgnoreCase(super.getcelldata(sheetname, "search", j)))

			{
	
			for(int i=j;i<=j;)
			{

					Thread.sleep(500);
					
					
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		try
		{
		String addemp = driver.findElement(By.xpath("//h1[contains(text(),'Add Employee')]")).getText();
			
		log.info(addemp+" page");
		super.screenshot(driver, addemp+" page");
		Thread.sleep(500);
		driver.findElement(By.id("firstName")).sendKeys(super.getcelldata(sheetname, "Emp_firstname", i));
		driver.findElement(By.id("lastName")).sendKeys(super.getcelldata(sheetname, "Emp_lastname", i));
		 WebElement emp_id = driver.findElement(By.id("employeeId"));
		emp_id.clear();
		emp_id.sendKeys(super.getcelldata(sheetname, "Emp_ID", i));
		driver.findElement(By.id("photofile")).sendKeys("C:\\Users\\sanjay.ravindra\\Pictures\\Employees\\Emp_"+i+".jpg");	
		driver.findElement(By.id("btnSave")).click();
		try
		{
			driver.findElement(By.xpath("//div[@class='message warning fadable']"));

			log.fatal("failed employee already exists");
			super.screenshot(driver, "Failed employee already exists- Employee"+i);
			super.writeExcel(sheetname, "Result", i, "Fail");
		}
		catch(NoSuchElementException exc)
		{
			log.info("Redirect to personal details page");
			
		}
	try
	{
		Thread.sleep(200);
		driver.findElement(By.xpath("//h1[contains(text(),'Personal Details')]"));
		super.screenshot(driver, "Employee"+i);
		log.info("Employee added page");
		Thread.sleep(200);
		super.writeExcel(sheetname, "Result", i, "Pass");
		
	}

		catch(NoSuchElementException ex)
		{
			log.fatal("Adding Employee failed");
			super.screenshot(driver, "Personal Details page-Employee "+ i + " failed");
			super.writeExcel(sheetname, "Result", i, "Fail");
			
		}
		
		}
		catch(NoSuchElementException a)
		{
			log.fatal("Add Employee page failed");
			super.screenshot(driver, "Add Employee page-Employee "+ i + " failed");
			super.writeExcel(sheetname, "Result", i, "Fail");
		}
	
		if(e.equalsIgnoreCase(super.getcelldata(sheetname, "edit", i))&&s.equalsIgnoreCase(super.getcelldata(sheetname, "search", i)))
		{
			this.editEmployee(sheetname,i);
		}
	
	break;	
		}			
		}
	}
		
	}
	
	
	public void editEmployee(String sheetname, int i) throws Throwable
	{

		Thread.sleep(100);
		String gender="Male";
	driver.findElement(By.id("btnSave")).click();
	log.info("textbox are enable to edit");
	super.screenshot(driver, "Edit Page");
		if(gender.equalsIgnoreCase(super.getcelldata(sheetname, "Gender", i)))
		{
			 WebElement male = driver.findElement(By.id("personal_optGender_1"));			 
			String maleValue = male.getAttribute("value");
			male.click();
			super.writeExcel(sheetname, "Gender code", i, maleValue);
		}
		else
		{
			WebElement female = driver.findElement(By.id("personal_optGender_2"));
			String femaleValue = female.getAttribute("value");
			super.writeExcel(sheetname, "Gender code", i, femaleValue);
			female.click();
		}
			WebElement marital_status = driver.findElement(By.id("personal_cmbMarital"));
			marital_status.click();
			marital_status.sendKeys(super.getcelldata(sheetname, "Marital Status", i));
			
			Select drop = new Select (driver.findElement(By.id("personal_cmbNation")));
			drop.selectByVisibleText(super.getcelldata(sheetname, "Nationality", i));
			String value = drop.getFirstSelectedOption().getAttribute("value");
			super.writeExcel(sheetname, "Nation code", i, value);

			WebElement dob = driver.findElement(By.id("personal_DOB"));
			dob.click();
			dob.sendKeys(super.getcelldata(sheetname, "DOB", i));
			log.info("Personal Details are entered");
			driver.findElement(By.id("btnSave")).click();

			Thread.sleep(500);
			try
			{
	
			WebElement msg = driver.findElement(By.xpath("//div[@class='message success fadable']"));
	
				String message = msg.getText();
	
				String text = message.substring(0,message.lastIndexOf("C"));
	
			super.screenshot(driver,"Employee"+i+"Saved");

			log.info(text);
			super.writeExcel(sheetname, "Result", i, "Pass");
			Thread.sleep(200);
			
			}
			catch(NoSuchElementException e)
			{
				log.fatal("failed");
				super.screenshot(driver, "Failed to add personal Details- Employee"+i);
				super.writeExcel(sheetname, "Result", i, "Fail");
			}
			
			
			Thread.sleep(200);
			}
	
	
	public void searchEmployee(String sheetname) throws Throwable
	{
		String flag= "Yes";
		for (int j = 1; j <=super.getrow(sheetname) ; j++) 
		{
			for(int i=j;i<=j;)
			{
				Thread.sleep(500);
				if(flag.equalsIgnoreCase(super.getcelldata(sheetname,"search",j)))
				{
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		

		try
		{
			
		driver.findElement(By.xpath("//a[@class='toggle tiptip']"));

		
		super.screenshot(driver, "Search page");
		log.info("Redirect to search page");
		WebElement search = driver.findElement(By.id("empsearch_id"));
		search.clear();		
		search.sendKeys(super.getcelldata(sheetname, "Emp_ID", i));
		Thread.sleep(100);
		driver.findElement(By.id("searchBtn")).click();
		super.screenshot(driver, "Searched Record"+i);
		
		Thread.sleep(200);
		driver.findElement(By.xpath("//a[contains(text(),'"+super.getcelldata(sheetname, "Emp_ID", i)+"')]")).click();
		log.info("Employee found");
		super.writeExcel(sheetname, "Result", i, "Pass");
		}
		catch(Exception exc)
		{
			log.fatal("No Record found");
			super.screenshot(driver, "No Records");
			super.writeExcel(sheetname, "Result", i, "Fail");
				
		}
		Thread.sleep(200);
		log.info("Redirect to personal detials page");
		this.editEmployee(sheetname, i);
		}
		break;
			}
		}
		
		
		
	}
		
	
	public void logout() throws Throwable
	{
		driver.findElement(By.id("welcome")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
	super.screenshot(driver, "Logout");	
	log.info("User logout from the application");
	
	}

	
	public void closeBrowser()
	{
	driver.close();
	log.info("Browser closed");
	}

	
	public void Db_val(String sheetname) throws Throwable
	{
		log.info("Database validation started");

		Database_validation data = new Database_validation();
		data.test(sheetname);
		

		log.info("Database validation ended");
	
	}
}
