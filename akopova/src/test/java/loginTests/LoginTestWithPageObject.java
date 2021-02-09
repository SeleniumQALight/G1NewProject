package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

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
    public void inValidLogin() {
        loginPage.fillLoginFormAndSubmit("auto", "WrongPassword");
        // To be added
        /**
        loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");
        loginPage.fillLoginFormAndSubmit("WrongLogin", "WrongPassword");
         */

        checkExpectedResult("SignOut Button is visible but should not", !homePage.isButtonSignOutVisible());




    }

}
