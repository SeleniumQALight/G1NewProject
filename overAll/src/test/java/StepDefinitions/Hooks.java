package StepDefinitions;


import org.apache.log4j.Logger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverManager;



public class Hooks {
    private DriverManager driverManager = new DriverManager();
    Logger logger = Logger.getLogger(getClass());

    @Before(value = "~@R009", order = 0)
    public void setUp(Scenario scenario){
        System.out.println("------------------------------");
        System.out.println("Start: " + scenario.getName());
        System.out.println("------------------------------");
        driverManager.createDriver();
    }

    @After(value = "~@R009")
    public void tearDown(Scenario scenario){
        //makeScreenshot();
        driverManager.closeDriver();
        System.out.println();
        System.out.println("------------------------------");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        System.out.println("------------------------------");
    }

}
