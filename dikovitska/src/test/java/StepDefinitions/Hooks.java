package StepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import org.apache.log4j.Logger;

public class Hooks {
    private DriverHelper driverManager = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(Scenario scenario){
        driverManager.createDriver();
    }

    @After
    public void tearDown(){
        driverManager.closeDriver();
    }
}