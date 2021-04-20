package StepDefinitions;

import api.CurrencyAPI;
import cucumber.api.java.en.And;


public class PrivatBank_API_StepDefinition {
    private CurrencyAPI currencyAPI = new CurrencyAPI();

    @And("^User gets '(.*)' to UAH Exchange Rate via API$")
            public void getCurrensyRateToUAHviaAPI(String currensy) {
        CurrencyAPI.getCurrensyAPI(currensy);
    }
}



