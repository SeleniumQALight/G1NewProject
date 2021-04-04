package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverManager;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpenLoginPage(){
        loginPage.openLoginPage();
    }


    @When("^User enters '(.*)' text in input 'username' in Login form on 'Home' page$")
    public void userEntersWrongLoginTextInInputUsernameInLoginFormOnHomePage(String userName) {
        loginPage.enterLoginSignIn(userName);
    }

    @And("^User enters '(.*)' text in input 'password' in Login form on 'Home' page$")
    public void userEntersUserTextInInputPasswordInLoginFormOnHomePage(String passWord) {
        loginPage.enterPassWordSignIn(passWord);
    }


    @When("^User clicks on button 'Sign up' in Login Form on 'Home' page$")
    public void userClicksOnButtonSignUpInLoginFormOnHomePage() {
        loginPage.clickButtonSignIn();
    }


    @Then("^User sees alert with text '(.*)'$")
    public void userSeesAlertWithTextInvalidUsernamePassword(String alertText) {
        loginPage.checkAlertMessageText(alertText);
    }


}
