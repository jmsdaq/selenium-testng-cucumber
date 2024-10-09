package utility;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private PageContext context;

    public Hooks(PageContext context) {
        this.context = context;
    }

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        System.out.println("Im in beforeScenario");
        RemoteWebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        context.setDriver(driver);
        context.setWait(wait);
        context.getDriver().get("https://www.saucedemo.com/v1/");
        Options manage =context.getDriver().manage();
//        manage.timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        manage.window().maximize();
    }

    @After("@cleanCart")
    public void cleanUp() throws InterruptedException {
        Thread.sleep(3000);
        WebElement cartButton = context.getDriver().findElement(By.xpath("//a[contains(@class,'shopping_cart_link fa-layers')]"));
        WebElement badge = context.getDriver().findElement(By.cssSelector(".shopping_cart_badge"));
        cartButton.click();
        Thread.sleep(5000);
        context.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_item")));

        // Loop to remove all items from the cart
        boolean itemsRemoved = true;
            while (itemsRemoved) {
            // Find all remove buttons for items in the cart
            List<WebElement> removeButtons = context.getDriver().findElements(By.cssSelector(".btn_secondary.cart_button"));

            if (removeButtons.isEmpty()) {
                itemsRemoved = false; // No more items to remove
            } else {
                removeButtons.getFirst().click();  // Click the first remove button

                // Optionally wait for the cart to update (e.g., an element indicating the item was removed)
                context.getWait().until(ExpectedConditions.stalenessOf(removeButtons.getFirst())); // Wait for the first button to be stale
            }
            }
//        WebElement badge = context.getDriver().findElement(By.cssSelector(".shopping_cart_badge"));
//        assertFalse(badge.isDisplayed(), "The shopping cart badge should not be visible after clearing the cart.");
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        boolean failed = scenario.isFailed();
        System.out.println("is Failed? "+failed);
        if (scenario.isFailed()) {
            byte[] screenshot = context.getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot on Failure");
        }
        System.out.println("Captured screenshot for failed scenario: " + scenario.getName());
        context.getDriver().quit();
    }
}