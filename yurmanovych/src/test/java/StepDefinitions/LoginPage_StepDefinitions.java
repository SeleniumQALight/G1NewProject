package StepDefinitions;

// here are classes stored which connect Gherkin and Java

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverManager;
import pages.LoginPage;

public class LoginPage_StepDefinitions {

    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpensLoginPage(){

        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login on 'Login' page$")
    public void userEntersLoginOnLoginPage(String login) {
        loginPage.enterLoginSignIn(login);
    }

    @When("^User enters '(.*)' password on 'Login' page$")
    public void userEntersPasswordOnLoginPage(String password) {
        loginPage.enterPassWord(password);
    }

    @And("^User clicks on 'SignIn' button on 'Login' page$")
    public void userClicksOnSignInButtonOnLoginPage() {
        loginPage.clickButtonSignIn();
    }

    @Then("^User gets alert message with text '(.*)'$")
    public void userGetsAlertMessageWithTextInvalidUsernamePassword(String errorMassage) {
    loginPage.verifyErrorMessageText(errorMassage);
    }
}
