package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpensLoginPage() {
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void userEntersLoginIntoLoginInputOnLoginPage(String userName) {
        loginPage.enterLoginSignIn(userName);
    }

    @And("^User enters '(.*)' passWord into 'Password' input on 'Login' page$")
    public void userEntersWrongPassPassWordIntoPasswordInputOnLoginPage(String passWord) {
        loginPage.enterPasswordSignIn(passWord);
    }

    @And("^User clicks on 'SignIn' button on 'Login' page$")
    public void userClicksOnSignInButtonOnLoginPage() {
        loginPage.clickButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void userSeesAlertMessageWithTextInvalidUsernamePassword(String messageText) {
        loginPage.checkAlertMessageText(messageText);
    }
}