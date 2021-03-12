package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationRegistration extends BaseTest {

    @Test
    public void validationRegistration() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterUserName("12");
        loginPage.enterEmaiL("tests.ua");
        loginPage.enterPassword("0123456789123");
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");

    }


}
