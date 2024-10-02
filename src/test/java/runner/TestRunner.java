import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = { "steps", "utility"}, // Package where your step definitions are located
        plugin = { "pretty", "html:target/cucumber-reports.html" } // Reporting options
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
