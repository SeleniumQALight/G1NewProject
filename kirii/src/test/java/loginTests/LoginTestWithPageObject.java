package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogIn(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPasswordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
    }
}