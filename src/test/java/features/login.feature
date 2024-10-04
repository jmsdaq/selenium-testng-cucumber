Feature: User Login

  Background: the user is on the login page

  Scenario: Successful login with valid credentials
    When the user enters "standard_user" and "secret_sauce"
    And clicks on the login button
    Then the user should be redirected to the products page

  Scenario: Unsuccessful login attempts
    When the user enters "Invalid username" and "Invalid password"
    And clicks on the login button
    Then an error message should be displayed

  Scenario: Attempt login with empty fields
    When clicks on the login button
    Then an error message should be displayed for
