package com.mange;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.properties.Property;

public class Manage_Emp extends Property
{
public void addEmployee(WebDriver driver) throws Throwable
{
	for (int j = 1; j < super.getrow("OrangeHRM"); j++) 
	{
	for (int i = j; i <= j;) 
	{
	
	driver.findElement(By.id("menu_pim_addEmployee")).click();
	driver.findElement(By.id("firstName")).sendKeys(super.getcelldata("OrangeHRM", "Emp_firstname", i));
	driver.findElement(By.id("lastName")).sendKeys(super.getcelldata("OrangeHRM", "Emp_lastname", i));
	 WebElement emp_id = driver.findElement(By.id("employeeId"));
	emp_id.clear();
	emp_id.sendKeys(super.getcelldata("OrangeHRM", "Emp_ID", i));
	driver.findElement(By.id("photofile")).sendKeys("C:\\Users\\sanjay.ravindra\\Pictures\\Employees\\Emp_"+i+".jpg");	
	driver.findElement(By.id("btnSave")).click();
	driver.findElement(By.id("btnSave")).click();
	
	String male="male";
Thread.sleep(1000);
	if(male==super.getcelldata("OrangeHRM", "Gender", i))
	{
		driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
	}
	else
	{
		driver.findElement(By.xpath("//label[contains(text(),'Female')]")).click();
	}
		WebElement marital_status = driver.findElement(By.id("personal_cmbMarital"));
		marital_status.click();
		marital_status.sendKeys(super.getcelldata("OrangeHRM", "Marital Status", i));
		WebElement nationality = driver.findElement(By.id("personal_cmbNation"));
		nationality.click();
		nationality.sendKeys(super.getcelldata("OrangeHRM", "Nationality", i));
		WebElement dob = driver.findElement(By.id("personal_DOB"));
		dob.click();
		dob.sendKeys(super.getcelldata("OrangeHRM", "DOB", i));
		driver.findElement(By.id("btnSave")).click();
	
	break;
	}
	}
}
	
}
