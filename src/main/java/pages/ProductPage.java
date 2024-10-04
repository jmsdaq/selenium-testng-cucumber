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
    WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement cartCount;

    public ProductPage(PageContext context) {
        this.context = context;
        PageFactory.initElements(context.getDriver(), this);
    }

    public ProductPage selectProduct(String productName) {
        WebElement product = context.getDriver().findElement(By.xpath("//div[contains(text(), '" + productName + "')]"));
        product.click();
        return this;
    }

    public ProductPage addToCart() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        return this;
    }

    public int getCartCount() {
        String countText = context.getWait().until(ExpectedConditions.visibilityOf(cartCount)).getText();
        return Integer.parseInt(countText);
    }

}
