package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;
import pages.ProductPage;

public class ProductSteps {
    private final PageContext context;
    private ProductPage productPage;

    public ProductSteps(PageContext context) {
        this.context = context;
        this.productPage = new ProductPage(context);
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        new LoginPage(context).login("standard_user", "secret_sauce"); // Assuming a login method exists in LoginSteps
    }

    @When("the user selects the product {string}")
    public void the_user_selects_the_product(String productName) {
        productPage.selectProduct(productName);
    }

    @When("adds the product to the cart")
    public void adds_the_product_to_the_cart() {
        productPage.addToCart();
    }

    @Then("the cart count should increase to {int}")
    public void the_cart_count_should_increase_to(int expectedCount) {
        int cartCount = productPage.getCartCount();
        assert cartCount == expectedCount : "Expected cart count to be " + expectedCount + " but was " + cartCount;
    }

    @Then("the cart count should increase")
    public void the_cart_count_should_increase() {
        int cartCount = productPage.getCartCount();
        assert cartCount > 1 : "Expected cart count to be more than 1, but was " + cartCount;
    }
}
