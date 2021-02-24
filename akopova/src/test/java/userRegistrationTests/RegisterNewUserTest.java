package userRegistrationTests;

import baseTest.BaseTest;
import libs.MyUtil;
import libs.TestData;
import org.junit.Test;

public class RegisterNewUserTest extends BaseTest {


    @Test
    public void registerValidUser() {
        loginPage.openLoginPage();
        loginPage.enterValidSignUpInfo();

    }

    @Test
    public void checkErrorMessages() {
        // Insert incorrect Sign Up Login (Username)
        loginPage.openLoginPage();
        MyUtil.waitABit(5);
        //loginPage.countErrorMessages();
        loginPage.enterLoginSignUp(TestData.INVALID_SIGNUP_LOGIN_SIGN);
        MyUtil.waitABit(5);
        loginPage.checkLoginErrorMessageIsDisplayed()
                .checkLoginSignUpErrorMessageText("Username can only contain letters and numbers.")
                .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 1)
        ;

        // Insert another wrong login text
        loginPage.signUpLogin.clear();
        loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);
        loginPage.enterLoginSignUp(TestData.INVALID_SIGNUP_LOGIN_SHORT);
        MyUtil.waitABit(5);
        loginPage.checkLoginErrorMessageIsDisplayed()
                .checkLoginSignUpErrorMessageText("Username must be at least 3 characters.")
                .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);

        loginPage.enterEmailSignUp(TestData.INVALID_SIGNUP_EMAIL);
        MyUtil.waitABit(5);
        loginPage.checkEmailErrorMessageIsDisplayed()
                .checkEmailSignUpErrorMessageText("You must provide a valid email address.")
                .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 1);


        loginPage.enterPasswordSignUp(TestData.INVALID_SIGNUP_PASSWORD);
        MyUtil.waitABit(5);
        loginPage.checkPasswordErrorMessageIsDisplayed()
                .checkPasswordSignUpErrorMessageText("Password must be at least 12 characters.")
                .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2)
        ;

        loginPage.signUpEmail.clear();
        loginPage.enterEmailSignUp(TestData.VALID_SIGNUP_EMAIL);
        MyUtil.waitABit(5);
        loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 3);

        loginPage.signUpPassword.clear();
        loginPage.enterPasswordSignUp(TestData.VALID_PASSWORD);
        MyUtil.waitABit(5);
        loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);
    }

    @Test
    public void checkErrorMessagesAmounts() {

        loginPage.openLoginPage();
        loginPage.enterIvValidSignUpInfo(TestData.INVALID_SIGNUP_LOGIN_SHORT
                , TestData.INVALID_SIGNUP_EMAIL, TestData.INVALID_SIGNUP_PASSWORD);
        MyUtil.waitABit(5);
        loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 3);
    }
}
