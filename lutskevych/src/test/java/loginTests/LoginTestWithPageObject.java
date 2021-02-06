package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPasswordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible",homePage.isButtonSignOutVisible());
    }
    @Test
    public void inValidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPasswordSignIn("123456");
        loginPage.clickButtonSignIn();

        checkExpectedResult("Allert Errorr is not visible",loginPage.isAllertErrorrVisible());
        checkExpectedResult("Button Sign Up For Our App Is visible",loginPage.buttonSignUpForOurAppIsVisible());
    }

}
