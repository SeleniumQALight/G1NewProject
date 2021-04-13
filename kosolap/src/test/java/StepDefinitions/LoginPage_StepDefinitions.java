package StepDefinitions;

import cucumber.api.java.en.Given;
import libs.DriverManager;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    private final LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());

    @Given("^User Opens 'Login' page$")
    public void userOpenLoginPage() {
        loginPage.openLoginPage();
    }
}
