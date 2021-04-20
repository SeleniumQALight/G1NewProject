package StepDefinitions;


import api.ApiPrivatHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pages.PrivatLandingPage;

import java.sql.Struct;

public class CurrencyCheck_StepDefinitions {
    Logger logger = Logger.getLogger(getClass());
    private PrivatLandingPage privatLandingPage = new PrivatLandingPage(DriverHelper.getWebDriver());


    @Given("^User gets '(.*)' exchange value from landing page$")
    public void userGetsCurrencyValueUiExchangeValueFromLandingPage(String currencyName) {
        privatLandingPage.getBuyValueFromUI(currencyName);
        privatLandingPage.getSaleValueFromUI(currencyName);
    }


    @Then("^User sees two values are equal$")
    public void userSeesTwoValuesAreEqual() {
    Assert.assertEquals(Float.valueOf(TestData.BUY_VALUE_API),Float.valueOf(TestData.BUY_VALUE_UI));
    Assert.assertEquals(Float.valueOf(TestData.SALE_VALUE_API),Float.valueOf(TestData.SALE_VALUE_UI));
    }

}
