package StepDefinitions;

import Pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;

public class LoginPage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpenLoginPage(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void userEntersLoginIntoLoginInputOnLoginPage(String userName) {
        loginPage.enterPassSignIn(userName);
    }

    @And("^User enters '(.*)' passWord into 'passWord' input on 'Login' page$")
    public void userEntersWrongPassPassWordIntoPassWordInputOnLoginPage(String passWord){
        loginPage.enterPassSignIn(passWord);
    }

    @And("^User click on 'SignIn' button on 'Login' page$")
    public void userClickOnSignInButtonOnLoginPage() {
        loginPage.clickButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void userSeesAlertMessageWithTextInvalidUsernamePassword(String messageText) {
        loginPage.checkAlertMessageText(messageText);
    }
}
