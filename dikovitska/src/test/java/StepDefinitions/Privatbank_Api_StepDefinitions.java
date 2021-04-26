package StepDefinitions;
import api.CurrencyApi;
import cucumber.api.java.en.And;

public class Privatbank_Api_StepDefinitions {
    private CurrencyApi currencyAPI = new CurrencyApi();

    @And("^User gets '(.*)' rate from API$")
    public void getCurrencyRateFromAPI(String currency) {
        CurrencyApi.getCurrencyApi(currency);
    }
}
