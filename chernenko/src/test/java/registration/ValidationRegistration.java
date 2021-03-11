package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationRegistration extends BaseTest {

    @Test
    public void validationRegistration()  {
        loginPage.openLoginPage();
        loginPage.enterUsernameIntoLogin("12");
        loginPage.enterEmailIntoLogin("test.test.ua");
        loginPage.enterPasswordIntoLogin("123456qwerty");
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");


    }
}
