package StepDefinitions;


import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverManager;
import libs.TestData;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());



    @Given("^User opens 'Home' page$")
    public void userOpensloginPage() {
        loginPage.openLoginPage();
    }

    @Then("^User sees error messages on 'Home' page$")
    public void usersSeesErrorMessages(List<String> expectedListOfErrors) {
        loginPage.checkErrors(expectedListOfErrors);
    }


    @When("^User enters '(.*)' text in input 'username' in Login form on 'Home' page$")
    public void userEntersCharsTextInInputUsernameInLoginFormOnloginPage(String text) {
        if (text.toLowerCase().equals("defaultuser")){
            text = TestData.VALID_LOGIN;
            System.out.println();
            System.out.println("default user => " + text);
        }
        loginPage.enterLoginSignIn(text);
    }

    @When("^User enters '(.*)' text in input 'password' in Login form on 'Home' page$")
    public void userEntersCharsTextInInputPasswordInLoginFormOnloginPage(String text) {
        if (text.toLowerCase().equals("defaultpassword")){
            text = TestData.VALID_PASSWORD;
            System.out.println();
            System.out.println("default password => " + text);
        }
        loginPage.enterPassWordSignIn(text);
    }

    @When("^User clicks on button 'Sign up' in Login Form on 'Home' page$")
    public void userClicksOnButtonSignUpInLoginFormOnloginPage() {
        loginPage.clickButtonSignIn();
    }

    @Then("^User sees alert with text '(.*)'$")
    public void userSeesAlertWithTextTest(String message) {
        loginPage.checkAllertText(message);
    }
}
