package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinitions {

    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());



    @When("^User click on 'Profile' button$")
    public void userClickOnProfileButton() {
        homePage.clickOnProfileButton();
    }

    @Given("^User opens 'Home' page$")
    public void userOpensHomePage(){
        loginPage.loginWithValidCreds().checkIsButtonSignOutVisible();
        homePage.clickOnProfileButton();



    }

}
