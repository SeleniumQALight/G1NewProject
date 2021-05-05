package StepDefinitions;


import api.CurrencyHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import  pages.PrivatBankPage;
import static org.junit.Assert.fail;

public class PrivatBank_StepDefinitions {

    PrivatBankPage privatBankPage = new PrivatBankPage(DriverHelper.getWebDriver());
    CurrencyHelper currencyHelper = new CurrencyHelper();
    Logger logger = Logger.getLogger(getClass());
    SoftAssertions softAssertions = new SoftAssertions();

    @Given("^User gets '(.*)' rates via PrivatBank API$")
    public void userGetCurrencyRateViaAPI(String currency){
    currencyHelper.getCurrencyRateViaAPI(currency);
    }
    @When("'PrivatBank' main page opens")
    public void userOpensPrivatBankMainPage(){
        privatBankPage.openPrivatPage();
    }

    @Given("^User gets '(.*)' rates via PrivatBank UI$")
    public void userGetsCurrenciesVisAPI(String currency){
        privatBankPage.getCurrencyRatesViaUI(currency);
    }

    @Then("^User compares PrivatBank Expected and Actual '(.*)' Buy and Sale rates$")
    public void userComparesCurrenciesRates(String currency){
        logger.info("Currency : " + currency);
        logger.info("Buy rate by API is " + TestData.API_RATE_BUY);
        logger.info("Sale rate by API is "  + TestData.API_RATE_SELL);
        logger.info("Buy rate by UI is " + TestData.UI_RATE_BUY);
        logger.info("Sale rate by UI is "  + TestData.UI_RATE_SELL);

        // Compare Buy Rates
            softAssertions.assertThat(Double.valueOf(TestData.API_RATE_BUY)
                    .equals(Double.valueOf(TestData.UI_RATE_BUY)));


        // Compare Sale Rates
            softAssertions.assertThat(Double.valueOf(TestData.API_RATE_SELL)
                    .equals(Double.valueOf(TestData.API_RATE_SELL)));

    }

}
