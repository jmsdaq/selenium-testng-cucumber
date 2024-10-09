Feature: User Login

  Background: the user is on the login page

  Scenario: Successful login with valid credentials
    When the user enters "standard_user" in the username field
    And the user enters "secret_sauce" in the password field
    And clicks on the login button
    Then the user should be redirected to the products page

  Scenario Outline: Unsuccessful login attempts
    When the user enters "<username>" in the username field
    And the user enters "<password>" in the password field
    And clicks on the login button
    Then an error message should be displayed as "<errorMessage>"

    Examples:
      | username        | password        | errorMessage                |
      | InvalidUser     | InvalidPass     | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce    | Epic sadface: Username is required |
      | standard_user   |                 | Epic sadface: Password is required |
      |                 |                 | Epic sadface: Username is required |

#  Scenario: Unsuccessful login attempts
#    When the user enters "Invalid username" and "Invalid password"
#    And clicks on the login button
#    Then an error message should be displayed
#
#  Scenario: Attempt login with empty fields
#    When clicks on the login button
#    Then an error message should be displayed for
