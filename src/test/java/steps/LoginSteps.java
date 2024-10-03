package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;

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

        }

        @Then("an error message should be displayed")
        public void an_error_message_should_be_displayed(){
            loginPage.clickLoginBtn();
    }
}
