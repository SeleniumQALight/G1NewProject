package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverManager;
import libs.TestData;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverManager.getWebDriver());
    private HomePage homePage = new HomePage(DriverManager.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred().checkIsButtonSignOutVisible();
    }

    @When("^User clicks 'Profile' button$")
    public void userClicksProfileButton() {
        homePage.clickOnProfileButton();
    }
}
