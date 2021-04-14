package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class HomePage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());
    private MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred().checkIsButtonSignOutVisible();
    }

    @When("^User click on 'Profile' button$")
    public void userClickOnProfileButton() {
        myProfilePage.clickOnMyProfileButton();
    }
}
