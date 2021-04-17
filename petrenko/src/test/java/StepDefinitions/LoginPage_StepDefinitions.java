package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinitions {

    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")

    public void OpenLoginPag() {

        loginPage.openLoinPage();


    }

    @When("^User enters '(.*)' login into 'Login'  input on 'Login' page$")
    public void userEntersLoginLoginIntoLoginInputOnLoginPage(String userName) {

        loginPage.enterLoginSignIn(userName);

    }


    @And("^User click on 'SignIn' button on 'Login' page$")
    public void userClickOnSignInButtonOnLoginPage() {
        loginPage.clickButtonSignIn();
    }

    @Then("^Users sees alert message with text '(.*)'$")
    public void usersSeesAlertMessageWithTextInvalidUsernamePassword(String messageText) {
        loginPage.checkTextOfErrorsInLoginIn(messageText);

    }

    @And("^User enters '(.*)' login into 'passWord'  input on 'Login' page$")
    public void userEntersWrongPassWordLoginIntoPassWordInputOnLoginPage(String passWord) {
        loginPage.enterPassWordSignIn(passWord);
    }
}
