package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import libs.PrivateTestData;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import privatPages.PrivateMainPage;

public class Private_Home_Page_StepDefinitions {
    PrivateMainPage privateMainPage = new PrivateMainPage(DriverHelper.getWebDriver());
    private float expectedCurrencyBuyRate;
    private float expectedCurrencySellRate;

    @And("^User saves cash rates for '(.*)' from UI$")
    public void userSaveCashRatesForCurrencyFromUI(String currency) {
        privateMainPage.openMainPrivatePage();
        expectedCurrencyBuyRate = privateMainPage.getCurrencyBuyRate(currency);
        expectedCurrencySellRate = privateMainPage.getCurrencySellRate(currency);

    }


    @Then("^User check that values from API and UI are equals$")
    public void userCheckThatValuesFromAPIAndUIAreEquals() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(PrivateTestData.SALE_RATE_API).isEqualTo(expectedCurrencySellRate);
        softAssertions.assertThat(PrivateTestData.BUY_RATE_API).isEqualTo(expectedCurrencyBuyRate);
        softAssertions.assertAll();
    }
}

