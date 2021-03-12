package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationRegistration extends BaseTest {
    @Test
    public void validationRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUsername("12");
        loginPage.enterEmail("tests.ua");
        loginPage.enterPassWord("0123456789123");

        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");

    }

}
