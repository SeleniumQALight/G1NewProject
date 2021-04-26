package StepDefinitions;
import libs.DriverHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.PrivatBankHP;

public class PrivatBank_HP_StepDefinitions {
    private PrivatBankHP privatBankHP = new PrivatBankHP (DriverHelper.getWebDriver());

    @Given("^User opens Privatbank HomePage$")
    public void userOpensPrivatBankHP() {
        privatBankHP.openPrivatBankHP();
    }

    @When("^User gets '(.*)' rate from UI$")
    public void userGetsRateFromUI(String currency) {
        privatBankHP.getRateFromUI(currency);
    }

    @Then("^User checks that rates from UI and  API are equals$")
    public void checkThatRateFromUIAndApiEquals() {
        privatBankHP.checkIsRatesEquals();
    }
}
