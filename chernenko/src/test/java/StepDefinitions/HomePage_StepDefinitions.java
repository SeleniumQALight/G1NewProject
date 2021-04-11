package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home page' page$")
    public void userOpensHomePagePage() {
        loginPage.loginWithValidCred().checkIsButtonSignOutVisible();
    }

    @When("^User click on 'Profile' button$")
    public void userClickOnProfileButton() {
        homePage.clickOnProfileButton();
    }


}