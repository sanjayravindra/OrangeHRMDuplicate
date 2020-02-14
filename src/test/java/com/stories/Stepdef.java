package com.stories;

import org.jbehave.core.annotations.*;

import com.mange.Employee;

public class Stepdef extends Employee
{
	@Given("User login to the application")
	
	public void givenUserLoginToTheApplication() throws Throwable
	{
		super.navigate();
		super.login();
	}

	@When("User add an employee")
	
	public void whenUserAddAnEmployee() throws Throwable
	{
		super.addEmployee("OrangeHRM","No","Yes");
	}

	@Then("User logout from the application")
	
	public void thenUserLogoutFromTheApplication() throws Throwable
	{
		super.logout();
	}


	
	@Given("User should login to the application")
	
	public void givenUserShouldLoginToTheApplication() throws Throwable
	{
		super.login();
	}

	@When("User should able to add and edit employee details")
	
	public void whenUserShouldAbleToAddAndEditEmployeeDetails() throws Throwable
	{
		super.addEmployee("OrangeHRM","Yes","No");
	}

	@Then("User should logout from the application")
	
	public void thenUserShouldLogoutFromTheApplication() throws Throwable
	{
		super.logout();
	}


	
	@Given("User should able to login for the application")
	
	public void givenUserShouldAbleToLoginForTheApplication() throws Throwable
	{
		super.login();
	}

	@When("User should able to search and edit employee details")
	
	public void whenUserShouldAbleToSearchAndEditEmployeeDetails() throws Throwable
	{
		super.searchEmployee("OrangeHRM"); 
	}

	@Then("User should able to logout from the application")
	
	public void thenUserShouldAbleToLogoutFromTheApplication() throws Throwable
	{
		super.logout();
		super.closeBrowser();
	}
	@Then("user validate in database")

	public void thenUserValidateInDatabase() throws Throwable 
	{
		super.Db_val("OrangeHRM");

	}
}
