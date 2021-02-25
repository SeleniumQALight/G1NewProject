package regestrationTests;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;


public class CheckErrorMessage extends BaseTest {

    @Test
    public void checkErrorMessageInRegisterForm() {
        loginPage.fillRegisterFormAndSubmit
                (LoginPage.unValidRegisterUserName, LoginPage.unValidRegisterEmail, LoginPage.validRegisterPassword)
                .countErrorRegisterForm();


    }

}
