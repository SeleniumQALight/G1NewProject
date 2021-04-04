package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverManager;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());
    private HomePage homePage = new HomePage(DriverManager.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred().checkIsButtonSignOutVisible();

    }

    @When("^User click on 'Profile' button$")
    public void userClickOnProfileButton() {
        homePage.clickOnProfileButton();
    }

    @Then("^User is redirect to 'Home' page$")
    public void userIsRedirectToHomePage() {
        homePage.checkIsButtonSignOutVisible();
    }
}
