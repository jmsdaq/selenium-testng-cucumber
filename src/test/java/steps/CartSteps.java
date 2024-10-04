package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductPage;

public class CartSteps {
    private CartPage cartPage;

    public CartSteps(PageContext context) {
        this.cartPage = new CartPage(context);
    }

    @Given("the user has product in the cart")
    public void the_user_has_product_in_the_cart(){
        Assert.assertTrue(cartPage.getCartCount() > 0, "No products in cart");
    }

    @Then("the cart count should increase")
    public void the_cart_count_should_increase() {
        int currentCartCount = cartPage.getCartCount();
        System.out.println("No.of books in cart: "+ currentCartCount);
        Assert.assertTrue(currentCartCount > 1, "Cart count is not more than 1");
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
    }

    @When("enters valid shipping information")
    public void enters_valid_shipping_information() {
        cartPage.enterShippingInformation();
    }

    @Then("the user should see the order confirmation message")
    public void the_user_should_see_the_order_confirmation_message() {
        String confirmationMessage = cartPage.getOrderConfirmationMessage();
        assert confirmationMessage.contains("Thank you for your order") : "Confirmation message not displayed.";
    }
}
