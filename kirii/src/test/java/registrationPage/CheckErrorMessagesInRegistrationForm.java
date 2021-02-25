package registrationPage;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CheckErrorMessagesInRegistrationForm extends BaseTest {

    public void checkNumberOfErrorMessagesInRegistrationForm(WebDriver webDriver) {
        checkExpectedResult("Allert Errorr is not visible",loginPage.isAlertErrorVisible());
        checkExpectedResult("Alert Errorr is not visible",loginPage.isAlertErrorVisible());
        checkExpectedResult("Button Sign Up For Our App Is visible",loginPage.signUpButtonIsVisible());
    }
    @Test
    public void checkErrorMessageForInvalidId(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("qa",null,null);

        checkExpectedResult("Alert for invalid id is not visible", loginPage.isAlertForInvalidIdVisible());
    }
    @Test
    public void checkErrorMessageForInvalidEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(null,"11",null);

        checkExpectedResult("Alert for invalid email is not visible", loginPage.isAlertForInvalidEmailVisible());
    }
    @Test
    public void checkErrorMessageForInvalidPass(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(null,null,"qwer");

        Util.waitABit(3);
        checkExpectedResult("Alert for invalid pass is not visible", loginPage.isAlertForInvalidPassVisible());
    }
    @Test
    public void checkErrorMessageForInvalidIdAndPassAndEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("qa","11","qwer");

        checkExpectedResult("Alert for invalid id is not visible", loginPage.isAlertForInvalidIdVisible());
        Util.waitABit(3);
        checkExpectedResult("Alert for invalid pass is not visible",loginPage.isAlertForInvalidPassVisible());
        checkExpectedResult("Alert for invalid email is not visible",loginPage.isAlertForInvalidEmailVisible());
    }
    @Test
    public void checkErrorMessageForEmptyIdAndPassAndEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(null ,null,null);
        loginPage.clickSignUpButton();

        checkExpectedResult("Alert for invalid id is not visible",loginPage.isAlertForInvalidIdVisible());
        checkExpectedResult("Alert for invalid pass is not visible",loginPage.isAlertForInvalidPassVisible());
        checkExpectedResult("Alert for invalid email is not visible",loginPage.isAlertForInvalidEmailVisible());
    }
    @Test
    public void checkErrorMessageForInvalidIdAndEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("qa","11", "123456qwerty");

        checkExpectedResult("Alert for invalid id is not visible",loginPage.isAlertForInvalidIdVisible());
        checkExpectedResult("Alert for invalid email is not visible",loginPage.isAlertForInvalidEmailVisible());
        checkExpectedResult("Alert for invalid pass is visible",loginPage.isAlertForInvalidPassInvisible());
    }
}
