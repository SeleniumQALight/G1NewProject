package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;



public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {

        loginPage.fillLoginFormAndSubmit("auto", "123456qwerty");
        checkExpectedResult("Button Sign In is not visible.", homePage.isButtonSignOutVisible());

    }

    @Test
    @Parameters({
     "qwerty, 123456qwerty",
            ", 123456qwerty"


    })
    @TestCaseName("inValidLogin: login = {0}, passWord = {1}")
    public void unValidLogin(String login, String password) {

        loginPage.openLoinPage();
        loginPage.enterLoginSignIn(login);
        loginPage.enterPassWordSignIn(password);
        loginPage.clickButtonSignIn();
        checkExpectedResult("Button Sign In is visible.", homePage.isButtonSignOutNotVisible());

    }


}
