package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
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
        new LoginSteps(context).login("standard_user", "secret_sauce"); // Assuming a login method exists in LoginSteps
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
        int actualCount = productPage.getCartCount();
        assert actualCount == expectedCount : "Expected cart count: " + expectedCount + ", but was: " + actualCount;
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        productPage.proceedToCheckout();
    }

    @Then("the user should see the order confirmation message")
    public void the_user_should_see_the_order_confirmation_message() {
        String confirmationMessage = productPage.getOrderConfirmationMessage();
        assert confirmationMessage.contains("Thank you for your order") : "Confirmation message not displayed.";
    }

}
