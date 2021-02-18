package loginTests;

import baseTest.BaseTest;
import org.junit.Test;
import pages.LoginPage;

public class LoginTestWithPageObject extends BaseTest {
@Test
    public void ValidLogin() {
    loginPage.openLoginPage();
    loginPage.enterLoginSignIn("auto");
    loginPage.enterPassWordSignIn("123456qwerty");
    loginPage.clickButtonSignIn();

    checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
}


    @Test
    public void inValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("wrong Login");
        loginPage.enterPassWordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is visible, but button should not be visible", !homePage.isButtonSignOutVisible());

    }

    }

