package StepDedinitions;

import com.sun.istack.internal.logging.Logger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverManager;

public class Hooks {
    private DriverManager driverManager = new DriverManager();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(Scenario scenario){
        driverManager.createDriver();
    }

    @After
    public void reamDown(){
        driverManager.closeDriver();
    }
}
