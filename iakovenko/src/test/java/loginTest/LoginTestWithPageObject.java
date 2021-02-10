package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin (){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("Auto");
        loginPage.enterPasswordSignIn("123456qwerty");
        loginPage.clickButtonSignIn();
        checkExpectedResult("Button SignOut is not visible",homePage.isButtonSignOutVisible());


    }
}
