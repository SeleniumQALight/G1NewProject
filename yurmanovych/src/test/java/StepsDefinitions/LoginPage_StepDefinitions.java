package StepsDefinitions;

// here are classes stored which connect Gherkin and Java

import cucumber.api.java.en.Given;
import libs.DriverManager;
import pages.LoginPage;

public class LoginPage_StepDefinitions {

    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpensLoginPage(){

        loginPage.openLoginPage();
    }
}