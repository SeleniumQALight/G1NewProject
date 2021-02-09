package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginWithInvalidValues extends BaseTest {
    @Test
    public void invalidLogIn(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPasswordSignIn("123");
        loginPage.clickButtonSignIn();

        checkExpectedResult("SignIn Button is visible", loginPage.isButtonSignInVisible());
        checkExpectedResult("Error message in visible", loginPage.isMessageErrorVisible());
        checkExpectedResult("Error message in visible", loginPage.isButtonSignOutNotVisible());
    }
}
