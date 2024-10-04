package pages;

import base.PageContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage{
    private final PageContext context;

    @FindBy(xpath = "//input[@data-test='username']")
    WebElement enterUsername;

    @FindBy(xpath = "//input[@data-test='password']")
    WebElement enterPassword;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")  // Correct compound class selector
    WebElement emptyFieldsError;

    @FindBy(xpath = "product_label")
    WebElement productPageLabel;

    public String dashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    public LoginPage(PageContext context) {
        this.context = context;
        PageFactory.initElements(context.getDriver(), this);
    }

    public LoginPage navigateToLoginPage() {
        context.getDriver().get("https://www.saucedemo.com/v1/"); // Update URL as necessary
        return this;
    }

    public LoginPage usernameInput(String username){
        context.getWait().until(ExpectedConditions.visibilityOf(enterUsername)).sendKeys(username);
        return this;
    }
    public LoginPage passwordInput(String password){
        context.getWait().until(ExpectedConditions.visibilityOf(enterPassword)).sendKeys(password);
        return this;
    }

    public LoginPage clickLoginBtn() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    public String errorMessage() {
        return context.getWait().until(ExpectedConditions.visibilityOf(emptyFieldsError)).getText();
    }

    public String getProductPageLabel() {
        return context.getWait().until(ExpectedConditions.visibilityOf(productPageLabel)).getText();
    }
    // New login method
    public void login(String username, String password) {
        this.navigateToLoginPage()
                .usernameInput(username)
                .passwordInput(password)
                .clickLoginBtn();
    }
}
