package StepsDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverManager;
import org.apache.log4j.Logger;

public class Hooks {
    private DriverManager driverManager = new DriverManager();
    Logger LOG = Logger.getLogger(getClass());

    @Before
    public void setup(Scenario scenario){
        driverManager.createDriver();

    }

    @After
    public void teardown(){
        driverManager.closeDriver();
    }
}
