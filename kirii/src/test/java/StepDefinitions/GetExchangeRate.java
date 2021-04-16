package StepDefinitions;

import api.CurrencyHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import pages.HomePagePrivat;

public class GetExchangeRate {

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
    @Then("^check currency rate API with UI$")
    public void checkCurrencyAPIWithUI() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(TestData.RATE_CURRENCY_BY_API).as("Curse by is not correct").contains(TestData.RATE_CURRENCY_BY_UI);
        softAssertions.assertThat(TestData.RATE_CURRENCY_SELL_API).as("Curse sell is not correct").contains(TestData.RATE_CURRENCY_SELL_UI);
        softAssertions.assertAll();
    }
}