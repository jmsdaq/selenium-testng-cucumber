package pages;

import base.PageContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage{
    private final PageContext context;

    @FindBy(xpath = "//input[@name='username']")
    WebElement enterUsername;

    @FindBy(xpath = "//input[@name='password']")
    WebElement enterPassword;

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    @FindBy(css = ".oxd-input-field-error-message")  // Correct compound class selector
    WebElement emptyFieldsError;

    @FindBy(css = ".oxd-text.oxd-text--p.oxd-alert-content-text")  // Correct compound class selector
    WebElement invalidCredentialsError;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement dashboardElement;

    public String dashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    public LoginPage(PageContext context) {
        this.context = context;
        PageFactory.initElements(context.getDriver(), this);
    }

    public void navigateToLoginPage() {
        context.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Update URL as necessary
    }

    public void usernameInput(String username){
        context.getWait().until(ExpectedConditions.visibilityOf(enterUsername)).sendKeys(username);
    }
    public void passwordInput(String password){
        context.getWait().until(ExpectedConditions.visibilityOf(enterPassword)).sendKeys(password);
    }

    //    public void setLogin(){
//        context.getWait().until(ExpectedConditions.visibilityOf(enterUsername)).sendKeys(username);
//        context.getWait().until(ExpectedConditions.visibilityOf(enterPassword)).sendKeys(password);
//        context.getWait().until(ExpectedConditions.elementToBeClickable(loginButton)).click();
//    }
    public void loginButton() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    public String isEmptyFieldsErrorDisplayed() {
        return context.getWait().until(ExpectedConditions.visibilityOf(emptyFieldsError)).getText();
    }
    public String isInvalidCredentialsErrorDisplayed() {
        return context.getWait().until(ExpectedConditions.visibilityOf(invalidCredentialsError)).getText();
    }


//    public String getExpectedUrl(){
//        waitForElementVisible(loginButton);
//        return dashboardUrl;
//    }

    public String getDashboardElementText() {
        return context.getWait().until(ExpectedConditions.visibilityOf(dashboardElement)).getText();
    }
}
