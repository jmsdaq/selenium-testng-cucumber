Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters "standard_user" and "secret_sauce"
    And clicks on the login button
    Then the user should be redirected to the products page

  Scenario: Unsuccessful login attempts
    Given the user is on the login page
    When the user enters "Invalid username" and "Invalid password"
    And clicks on the login button
    Then an error message should be displayed

  Scenario: Attempt login with empty fields
    Given the user is on the login page
    When the user leaves the username and password fields empty
    And clicks on the login button
    Then an error message should be displayed stating "Username is required" or "Password is required"
