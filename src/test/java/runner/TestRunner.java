package runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features/", // Path to your feature files
        glue = { "steps", "utility"}, // Package where your step definitions are located
        monochrome = true,
        plugin = { "pretty", "html:target/cucumber-reports.html" } // Reporting options
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
