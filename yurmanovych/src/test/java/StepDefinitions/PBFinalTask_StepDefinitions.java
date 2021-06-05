package StepDefinitions;

import api.ApiHelperPrivatbank;
import api.ExchangeRateDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverManager;
import pages.PrivatbankMainPage;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PBFinalTask_StepDefinitions {

    private ExchangeRateDTO[] privatbankResponse;
    private final PrivatbankMainPage privatbankMainPage = new PrivatbankMainPage((DriverManager.getWebDriver()));


    @Given("^Exchange rates are received from Privatbank API$")
    public void getExchangeRatesAreReceivedFromPrivatbankAPI() {
        privatbankResponse = ApiHelperPrivatbank.getExchangeRatesPB();
        assertNotNull("Exchange rates API response is null", privatbankResponse);
    }

    @When("^Privatbank 'Main Page' is open and the exchange rates caption is visible$")
    public void privatbankMainPageIsOpenAndExchangeRatesCaptionVisible() {
        assertTrue(privatbankMainPage
                .openMainPage()
                .isExchRatesCaptionDisplayed());
    }

    @Then("^Verify the exchange rates on the 'Main Page' are same as received from the API$")
    public void verifyTheExchangeRatesOnTheMainPageAreSameAsReceivedFromTheAPI() {
        List<String> ccyDisplayed = privatbankMainPage.getCcyDisplayedList();
        Arrays.stream(privatbankResponse).forEach(apiVal -> {
            String ccy = apiVal.getCcy();
            if (ccyDisplayed.contains(ccy)||ccy.equals("RUR")) {
                String[] uiCcyBuySell = privatbankMainPage.getCcyRate(apiVal.getCcy());
                assertEquals(
                        String.format("Buy rates are different for %s currency", apiVal.getCcy()),
                        apiVal.getBuy(),
                        uiCcyBuySell[0]
                );
                assertEquals(
                        String.format("Sell rates are different for %s currency", apiVal.getCcy()),
                        apiVal.getSale(),
                        uiCcyBuySell[1]
                );
            }
        });
    }
}
