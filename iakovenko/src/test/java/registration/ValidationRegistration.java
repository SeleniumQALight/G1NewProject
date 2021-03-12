package registration;

import baseTest.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class ValidationRegistration extends BaseTest {
    @Test
    public void validationRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUserName("12");
        loginPage.enterEmail("tests.ua");
        loginPage.enterPassWord("1234");

        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");


    }
}
