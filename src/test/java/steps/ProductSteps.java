package steps;

import base.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;
import pages.ProductPage;

import static org.testng.AssertJUnit.assertEquals;

public class ProductSteps {
    private final PageContext context;
    private ProductPage productPage;

    public ProductSteps(PageContext context) {
        this.context = context;
        this.productPage = new ProductPage(context);
    }

    @Given("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        new LoginPage(context)
                .usernameInput("standard_user")
                .passwordInput("secret_sauce")
                .clickLoginBtn();
    }

    @When("the user adds {string} to the cart")
    public void the_user_adds_product_to_cart(String productName) {
        productPage.addToCart(productName);
    }

    @Then("the user should be redirected to the products page")
    public void the_user_should_be_redirected_to_the_products_page(){
        String actualTitle = new LoginPage(context).getProductPageLabel(); // Update with your dashboard page title
        String expectedTitle = "Products";
        assertEquals(expectedTitle, actualTitle);
    }
    @Then("the cart count should increase to {int}")
    public void the_cart_count_should_increase_to(int expectedCount) {
        int cartCount = productPage.getCartCount();
        assert cartCount == expectedCount : "Expected cart count to be " + expectedCount + " but was " + cartCount;
    }

    @Then("the cart count should increase")
    public void the_cart_count_should_increase() {
        int cartCount = productPage.getCartCount();
        System.out.println("No.of books in cart: "+ cartCount);
        assert cartCount > 1 : "Expected cart count to be more than 1, but was " + cartCount;
    }
}
