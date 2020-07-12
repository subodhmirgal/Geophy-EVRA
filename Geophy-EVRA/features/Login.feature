@LoginFeature
Feature: Login to EVRA Application
	
  Scenario: Login_001 A user must be able to login with his valid credentials 
    Given I navigate to EVRA login page
    When I enter the Email "qaskillschallenge@geophy.com"
    And I enter the password "qaskillschallenge@geophy.com"
    And I click on LOG IN button
    Then the following text must be visible "Welcome back, QA Geophy."
    

  Scenario: Login_002 Error message must be displayed when user enters incorrect password

    Given I navigate to EVRA login page
    When I enter the Email "qaskillschallenge@geophy.com"
    And I enter the password "qaskillschallenge@geophy.co"
    And I click on LOG IN button
    Then Error message "There was an error with your e-mail or password, please try entering your login credentials again." must be displayed


  Scenario: Login_003  The login page must contain links to Forgot password,Sign Up and Remember me checkbox
    Given I navigate to EVRA login page
    Then the link to "https://evra.geophy.com/password/reset" with text "Forgot password? Click here to reset"  must be visible
