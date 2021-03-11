package registrationPage;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationRegistration extends BaseTest {
    @Test
    public void validationRegistration(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("12", "tests.ua", "0123456789123");
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
    }
}