package StepDefinitions;

import api.PrivateApiHelper;
import cucumber.api.java.en.Given;

public class Private_API_StepDefinitions {
    private PrivateApiHelper privateApiHelper = new PrivateApiHelper();


    @Given("^User sends API request and saves received cash rates for '(.*)'$")
    public void userSendAPIRequestAndSaveReceivedCashRatesForCurrency(String currency) {
        privateApiHelper.getAndSaveCashRate(currency);
    }
}
