package StepDefinitions;

import Pages.PrivateBankPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;

public class PrivatBank_StepDefinitions {
    private PrivateBankPage privateBankPage = new PrivateBankPage(DriverHelper.getWebDriver());

    @Given("^User opens 'PrivateBank' page$")
    public void userOpensPrivateBankPage() {
        privateBankPage.openPrivatBankPage();
    }

    @When("^User gets '(.*)' to UAH Exchange Rate from UAI$")
    public void userGetsCurrensyToUAHExchangeRateFromUAI(String currensy) {
        privateBankPage.getCurrensyRateToUAHfromUAI(currensy);
    }

    @Then("^check is Rate from UAI and Rate via API for equals$")
    public void checkIsRateFromUAIAndRateViaAPIEquals() {
        privateBankPage.checkIsRatesEquals();
    }

}
