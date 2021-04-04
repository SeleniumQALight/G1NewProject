package StepDedinitions;

import com.sun.istack.internal.logging.Logger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;

public class Hooks {
    private DriverHelper driverHelper = new DriverHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(Scenario scenario){
        driverHelper.createDriver();
    }

    @After
    public void reamDown(){
        driverHelper.closeDriver();
    }
}
