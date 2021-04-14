package StepDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import api.ApiHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import libs.TestData;

public class Hooks {
    private DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());
    private ApiHelper apiHelper = new ApiHelper();

    @Before(order = 0)
    public void setUp(Scenario scenario){
        logger.info(scenario.getName() + " was started");
        driverHelper.createDriver();
    }

    @After(order = 0)
    public void tearDown(Scenario scenario){
        driverHelper.closeDriver();
        logger.info(scenario.getName() + " was ended with status " + scenario.getStatus());
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)
    public void deletingAllPostsForDefaultUser(){
        apiHelper.deletePostsTillPresent(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }

    @After(order = 100)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + DriverHelper.getWebDriver().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) DriverHelper.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");  // Stick it in the report
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.out.println(somePlatformsDontSupportScreenshots.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }

}
