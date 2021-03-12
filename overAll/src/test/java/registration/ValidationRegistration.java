package registration;

import org.junit.Test;

import baseTest.BaseTest;

public class ValidationRegistration extends BaseTest {
    @Test
    public void validationRegistration() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterUserName("12");
        loginPage.enterEmail("tests.ua");
        loginPage.enterPassWord("0123456789123");

        loginPage.checkErrors("Username must be at least 3 characters111;You must provide a valid email address555.");
    }
}
