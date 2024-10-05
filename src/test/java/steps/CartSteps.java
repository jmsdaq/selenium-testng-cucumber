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
    private ProductPage productPage;

    public CartSteps(PageContext context) {
        this.cartPage = new CartPage(context);
        this.productPage = new ProductPage(context);
    }

    @Given("the user has product in the cart")
    public void the_user_has_product_in_the_cart(){
        productPage.addItemBtn();
        cartPage.clickCart();
        Assert.assertTrue(cartPage.getCartCount() > 0, "No products in cart");
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
    }

    @When("enters valid shipping information")
    public void enters_valid_shipping_information() {
        cartPage.enterShippingInformation();
        cartPage.setFinishCheckout();
    }

    @Then("the user should see the order confirmation message")
    public void the_user_should_see_the_order_confirmation_message() {
        String confirmationMessage = cartPage.getOrderConfirmationMessage();
        assert confirmationMessage.contains("Thank you for your order") : "Confirmation message not displayed.";
    }
}
