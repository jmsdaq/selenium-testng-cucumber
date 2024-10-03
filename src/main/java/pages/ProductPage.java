package pages;

import base.PageContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {
    private final PageContext context;

    @FindBy(xpath = "//button[contains(text(), 'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement cartCount;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//h2[text()='Thank you for your order']")
    private WebElement orderConfirmationMessage;

    public ProductPage(PageContext context) {
        this.context = context;
        PageFactory.initElements(context.getDriver(), this);
    }

    public ProductPage selectProduct(String productName) {
        WebElement product = context.getDriver().findElement(By.xpath("//div[contains(text(), '" + productName + "')]"));
        product.click();
        return this; // Return current instance for chaining
    }

    public ProductPage addToCart() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        return this; // Return current instance for chaining
    }

    public int getCartCount() {
        String countText = context.getWait().until(ExpectedConditions.visibilityOf(cartCount)).getText();
        return Integer.parseInt(countText);
    }

    public ProductPage proceedToCheckout() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return this; // Return current instance for chaining
    }

    public String getOrderConfirmationMessage() {
        return context.getWait().until(ExpectedConditions.visibilityOf(orderConfirmationMessage)).getText();
    }
}
