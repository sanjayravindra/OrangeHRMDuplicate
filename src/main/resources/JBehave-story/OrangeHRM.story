OrangeHRM Project

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario: Verify the user is able to login and add employee
Given User login to the application
When User add an employee
Then User logout from the application
		
									 
Scenario: Verify the user is able to add employee and edit employee details
Given User should login to the application  
When User should able to add and edit employee details
Then User should logout from the application


Scenario: Verify the user is able to search an employee and edit employee
Given User should able to login for the application
When User should able to search and edit employee details
Then User should able to logout from the application
And user validate in database
