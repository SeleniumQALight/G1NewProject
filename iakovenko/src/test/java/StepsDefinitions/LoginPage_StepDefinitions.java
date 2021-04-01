package StepsDefinitions;

import cucumber.api.java.en.Given;
import libs.DriverManager;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    private LoginPage loginPage =new LoginPage(DriverManager.getWebDriver());

    @Given("^User open 'Login' page$")
    public void userOpenLoginPage(){
        loginPage.openLoginPage();

    }
}
