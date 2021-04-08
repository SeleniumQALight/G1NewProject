package StepDefinitions;

import api.ApiHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverManager;
import libs.TestData;
import org.apache.log4j.Logger;

public class Hooks {
    private DriverManager driverManager = new DriverManager();
    Logger LOG = Logger.getLogger(getClass());
    private ApiHelper apiHelper = new ApiHelper();

    @Before(order=0)
    public void setup(Scenario scenario){
        LOG.info(scenario.getName() + " has been launched.");
        driverManager.createDriver();

    }

    @After(order=100)
    public void teardown(Scenario scenario){
        driverManager.closeDriver();
        LOG.info(scenario.getName() + " has ended with status: "+scenario.getStatus());
    }

    @Before(value = "@BeforeDeleteAllPostsForDefaultUser", order=100)
    @After(value = "@AfterDeleteAllPostsForDefaultUser", order=0)
    public void deleteAllPostsForDefaultUser(){
        apiHelper.deletePostsByUser(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }
}
