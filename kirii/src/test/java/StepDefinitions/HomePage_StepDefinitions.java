package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage(){
        loginPage.loginWithValidCred().checkIsButtonSignOutVisible();
    }

    @When("^User clicks on 'Profile' button$")
    public void userClicksOnProfileButton() {
        homePage.clickOnProfileButton();
    }
}