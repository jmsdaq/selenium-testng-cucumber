package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;

import static org.testng.AssertJUnit.*;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(PageContext context) {
        this.loginPage = new LoginPage(context);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page( ) {
        loginPage.navigateToLoginPage();
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String username, String password) {
        loginPage.usernameInput(username).passwordInput(password);
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginPage.clickLoginBtn();
    }

    @Then("the user should be redirected to the products page")
    public void the_user_should_be_redirected_to_the_products_page(){
        String actualTitle = loginPage.getProductPageLabel(); // Update with your dashboard page title
        String expectedTitle = "Product";
        assertEquals(expectedTitle, actualTitle);
    }
    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed(){
        String errorMessageText = loginPage.errorMessage();
        System.out.println(errorMessageText);
        assertNotNull(errorMessageText, "Error message should not be null!");
    }

    @Then("an error message should be displayed for")
    public void an_error_message_should_be_displayed_for(){
        String emptyLogin = loginPage.errorMessage();
        System.out.println(emptyLogin);
    }
}
