package pages;

import base.PageContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage {
    private final PageContext context;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement cartCount;

    @FindBy(xpath = "//a[@class='btn_action checkout_button']")
    WebElement checkoutButton;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement continueButton;

    @FindBy(xpath = "//h2[normalize-space(text())='THANK YOU FOR YOUR ORDER']")
    WebElement orderConfirmationMessage;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    WebElement finishCheckout;

    public CartPage(PageContext context) {
        this.context = context;
        PageFactory.initElements(context.getDriver(), this);
    }

    public int getCartCount() {
        return Integer.parseInt(context.getWait().until(ExpectedConditions.visibilityOf(cartCount)).getText());
    }

    public void proceedToCheckout() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void enterShippingInformation() {
        context.getWait().until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys("John");
        context.getWait().until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys("Doe");
        context.getWait().until(ExpectedConditions.visibilityOf(postalCodeField)).sendKeys("12345");
        context.getWait().until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getOrderConfirmationMessage() {
        return context.getWait().until(ExpectedConditions.visibilityOf(orderConfirmationMessage)).getText();
    }
}
