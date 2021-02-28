package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPasswordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();
        checkExpectedResult("SignOut Button is not visible", homePage.isButtonSignOutVisible());

    }


    @Test
    @Parameters({
            "WrongLogin, 123456qwerty",
            ",WrongPassword"

    })
    @TestCaseName("inValidLogin : login = {0}, password = {1}")
    public void inValidLogin(String login, String password) {
        loginPage.fillLoginFormAndSubmit(login, password);
        // To be added
        /**
        loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");
        loginPage.fillLoginFormAndSubmit("WrongLogin", "WrongPassword");
         */

        checkExpectedResult("SignOut Button is visible but should not"
                , !homePage.isButtonSignOutVisible());
    }

}
