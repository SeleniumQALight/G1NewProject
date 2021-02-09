package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin(){

        loginPage.openLoinPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPassWordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();
        checkExpectedResult("Button Sign In is not visible.", homePage.isButtonSignOutVisible());

    }

    @Test
    public void unValidLogin(){

        loginPage.openLoinPage();
        loginPage.enterLoginSignIn("qwerty");
        loginPage.enterPassWordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();
        checkExpectedResult("Button Sign In is visible.", homePage.isButtonSignOutNotVisible());

    }


}
