package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/product_selection.feature", // Path to your feature files
        glue = { "steps", "utility"}, // Package where your step definitions are located
        monochrome = true,
        plugin = { "pretty", "html:target/cucumber-reports.html" } // Reporting options
//        tags = "@prod"
)
public class ProductTestRunner extends AbstractTestNGCucumberTests {
}
