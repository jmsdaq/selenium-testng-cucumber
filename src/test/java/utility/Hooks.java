package utility;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.Test;

public class Hooks {
    private PageContext context;
    private String browser = "chrome"; // Default browser
    private boolean headless = true; // Set to true for headless mode

    public Hooks(PageContext context) {
        this.context = context;
    }

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        System.out.println("In beforeScenario");

        @Test
        @Description("Test to validate login functionality")
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
            options.addArguments("--window-size=1920,1080"); // Set window size for headless
//            options.addArguments("--no-sandbox"); // Required for some CI environments
//            options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        }

//        // Initialize WebDriver with options
//        RemoteWebDriver driver = new ChromeDriver(options);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initialize WebDriver using BrowserDriverFactory
        String browser = System.getProperty("browser", "chrome");
        RemoteWebDriver driver = BrowserDriverFactory.createDriver(browser);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        context.setDriver(driver);
        context.setWait(wait);
        context.getDriver().get("https://www.saucedemo.com/v1/");
        Options manage = context.getDriver().manage();
//        manage.window().maximize(); // This can be omitted in headless mode
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
            List<WebElement> removeButtons = context.getDriver().findElements(By.cssSelector(".btn_secondary.cart_button"));

            if (removeButtons.isEmpty()) {
                itemsRemoved = false; // No more items to remove
            } else {
                removeButtons.get(0).click();  // Click the first remove button

                context.getWait().until(ExpectedConditions.stalenessOf(removeButtons.get(0))); // Wait for the first button to be stale
            }
        }
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        boolean failed = scenario.isFailed();
        System.out.println("Is Failed? " + failed);
        if (failed) {
            byte[] screenshot = context.getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot on Failure");
        }
        System.out.println("Captured screenshot for failed scenario: " + scenario.getName());
        context.getDriver().quit();
    }
}
