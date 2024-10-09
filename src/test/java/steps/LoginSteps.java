package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;

import java.util.Objects;

import static org.testng.AssertJUnit.*;

public class LoginSteps {
    private final PageContext context;
    private final LoginPage loginPage;

    public LoginSteps(PageContext context) {
        this.context = context;
        this.loginPage = new LoginPage(context);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page( ) {
        String expectedUrl = "https://www.saucedemo.com/v1/"; // Replace with your expected URL
        String currentUrl = context.getDriver.getCurrentUrl();
        assert Objects.equals(currentUrl, expectedUrl) : "Expected URL: " + expectedUrl + ", but got: " + currentUrl;
    }

//    @When("the user enters {string} and {string}")
//    public void the_user_enters_and(String username, String password) {
//        loginPage.usernameInput(username)
//                .passwordInput(password);
//    }
    @When("the user enters {string} in the username field")
    public void the_user_enters_in_the_username_field(String username) {
        loginPage.usernameInput(username);
    }

    @When("the user enters {string} in the password field")
    public void the_user_enters_in_the_password_field(String password) {
        loginPage.passwordInput(password);
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginPage.clickLoginBtn();
    }


    @Then("an error message should be displayed as {string}")
    public void an_error_message_should_be_displayed_as(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.errorMessage();
        System.out.println("Error message: " + actualErrorMessage);
        assertNotNull("Error message should not be null!", actualErrorMessage);
        assertTrue("Expected error message to contain: " + expectedErrorMessage,
                actualErrorMessage.contains(expectedErrorMessage));
    }
}
