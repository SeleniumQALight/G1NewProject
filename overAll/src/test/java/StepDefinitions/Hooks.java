package StepDefinitions;

import org.apache.log4j.Logger;

import api.ApiHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverManager;
import libs.TestData;

public class Hooks {
    private DriverManager driverManager = new DriverManager();
    Logger logger = Logger.getLogger(getClass());
    protected ApiHelper apiHelper = new ApiHelper();

    @Before( order = 0)
    public void setUp(Scenario scenario){
        driverManager.createDriver();
        scenario.getSourceTagNames();
    }

    @After( order = 0)
    public void tearDown(){
        driverManager.closeDriver();
    }

    @Before(value = "@BeforeDeletingAllPostForDefaultUser", order = 100)
    @After(value = "@AfterDeletingAllPostForDefaultUser", order = 100)
    public void seleteAllPostsForDefaultUser(){
        apiHelper.deletePostsTillPresent(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }

}
