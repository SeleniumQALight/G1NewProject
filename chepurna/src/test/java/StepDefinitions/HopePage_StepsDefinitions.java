package StepDefinitions;

import Pages.HomePage;
import Pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;

public class HopePage_StepsDefinitions {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred()
                .isButtonSignOutVisible();
    }

    @When("^User click on 'Profile' button$")
    public void userClickOnProfileButton() {
        homePage.clickOnMyProfileButton();
    }

}
