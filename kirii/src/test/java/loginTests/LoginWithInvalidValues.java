package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginWithInvalidValues extends BaseTest {
    @Test
    public void invalidLogIn(){
//        loginPage.openLoginPage();
//        loginPage.enterLoginSignIn("auto");
//        loginPage.enterPasswordSignIn("123");
//        loginPage.clickButtonSignIn();
        loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");

        checkExpectedResult("SignIn Button is visible", loginPage.isButtonSignInVisible());
        checkExpectedResult("Error message is visible", loginPage.isMessageErrorVisible());
        checkExpectedResult("SignOut Button is not visible", loginPage.isButtonSignOutNotVisible());
    }
}
