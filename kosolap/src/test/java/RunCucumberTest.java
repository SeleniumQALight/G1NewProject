import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
        glue = "StepDefinitions"
)
public class RunCucumberTest {
}
