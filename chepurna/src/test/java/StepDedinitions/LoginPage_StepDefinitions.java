package StepDedinitions;

import Pages.LoginPage;
import cucumber.api.java.en.Given;
import libs.DriverManager;

public class LoginPage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpenLoginPage(){
        loginPage.openLoginPage();
    }
}
