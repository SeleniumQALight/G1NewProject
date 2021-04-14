package StepDefinitions;


import api.CurrencyHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pages.HomePagePrivat;

import static libs.TestData.*;

public class GetCurrencyRateByApi {
    HomePagePrivat homePagePrivat = new HomePagePrivat();
    CurrencyHelper currencyHelper = new CurrencyHelper();
    Logger logger = Logger.getLogger(getClass());

    @Given("^User remembers '(.*)' kurs from PrivatBank by API$")
    public void userRemembersCurrencyKursFromPrivatBankByAPI(String currency) {
        currencyHelper.saveCurseByValueCurrency(currency);

    }


    @When("^User open 'Home' page$")
    public void userOpenHomePage() {
        homePagePrivat.openHomePage();

    }

    @Given("^User remembers '(.*)' kurs from 'HomePage' page$")
    public void userRemembersCurrencyKursFromHomePagePage(String currency) {
        homePagePrivat.saveCurseByValueCurrency(currency);

    }


    @Then("^check '(.*)' API with UI$")
    public void checkCurrencyAPIWithUI(String currency) {
        logger.info(RATE_CURRENCY_BY_API +"---"+currency+"--"+ TestData.RATE_CURRENCY_BY_UI);
        logger.info(RATE_CURRENCY_SALE_API +"---"+currency+"--"+ RATE_CURRENCY_SALE_UI);
        Assert.assertTrue("Curse by is not correct", RATE_CURRENCY_BY_API.contains(RATE_CURRENCY_BY_UI));
        Assert.assertTrue("Curse sell is not correct", RATE_CURRENCY_SALE_API.contains(RATE_CURRENCY_SALE_UI));
    }
}
