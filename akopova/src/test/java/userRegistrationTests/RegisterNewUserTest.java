package userRegistrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.*;
import org.testng.annotations.BeforeClass;

@RunWith(JUnitParamsRunner.class)
public class RegisterNewUserTest extends BaseTest {


    @Test
    public void registerValidUser() {
        loginPage.registerNewValidUser();
        homePage.checkIsRedirectedOnHomePage();

    }

    /**
     * @Test public void checkErrorMessages() {
     * // Insert incorrect Sign Up Login (Username)
     * loginPage.openLoginPage();
     * MyUtil.waitABit(5);
     * //loginPage.countErrorMessages();
     * loginPage.enterLoginSignUp(TestData.INVALID_SIGNUP_LOGIN_SIGN);
     * MyUtil.waitABit(5);
     * loginPage.checkLoginErrorMessageIsDisplayed()
     * .checkLoginSignUpErrorMessageText("Username can only contain letters and numbers.")
     * .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 1)
     * ;
     * <p>
     * // Insert another wrong login text
     * loginPage.signUpLogin.clear();
     * loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);
     * loginPage.enterLoginSignUp(TestData.INVALID_SIGNUP_LOGIN_SHORT);
     * MyUtil.waitABit(5);
     * loginPage.checkLoginErrorMessageIsDisplayed()
     * .checkLoginSignUpErrorMessageText("Username must be at least 3 characters.")
     * .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);
     * <p>
     * loginPage.enterEmailSignUp(TestData.INVALID_SIGNUP_EMAIL);
     * MyUtil.waitABit(5);
     * loginPage.checkEmailErrorMessageIsDisplayed()
     * .checkEmailSignUpErrorMessageText("You must provide a valid email address.")
     * .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 1);
     * <p>
     * <p>
     * loginPage.enterPasswordSignUp(TestData.INVALID_SIGNUP_PASSWORD);
     * MyUtil.waitABit(5);
     * loginPage.checkPasswordErrorMessageIsDisplayed()
     * .checkPasswordSignUpErrorMessageText("Password must be at least 12 characters.")
     * .checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2)
     * ;
     * <p>
     * loginPage.signUpEmail.clear();
     * loginPage.enterEmailSignUp(TestData.VALID_SIGNUP_EMAIL);
     * MyUtil.waitABit(5);
     * loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 3);
     * <p>
     * loginPage.signUpPassword.clear();
     * loginPage.enterPasswordSignUp(TestData.VALID_PASSWORD);
     * MyUtil.waitABit(5);
     * loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);
     * }
     */


    @Test
    public void checkErrorMessagesAmounts() {

        loginPage.openLoginPage();
        loginPage.enterSignUpInfo(TestData.INVALID_SIGNUP_LOGIN_SHORT
                , TestData.VALID_SIGNUP_EMAIL
                , TestData.INVALID_SIGNUP_PASSWORD
        );
        loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, 2);
    }


    @Test
    @Parameters(
            /**{
                    "auto@, myMail@gmail.com, 098765poiuyt, 1"
            }
            */

            {
                "auto@, myMail@gmail.com, 098765poiuyt, 1",
                "au, myMail_gmail.com, 098765poiuyt, 2",
                "auto@, myMail@gmail.com, @@@, 2",
                "autoNew, myMail_gmail.com, 098765poiuyt, 1",
                "autoNew, myMail_gmail.com, @@@, 2",
                "autoNew, myMail@gmail.com, QQQ, 1",
                "au, myMail_gmail.com, @@@, 3"
            }

    )
    @TestCaseName
    ("tryToRegisterInvalidUser : login = {o}, eMail  = {1}, password = {2}, expectedErrorsNumber={3}")

    public void tryToRegisterInvalidUser(String login
            , String email
            , String password
            , int expectedErrorsNumber
            ) {

        loginPage.openLoginPage();
        loginPage.enterSignUpInfo(login, email, password);
        loginPage.checkErrorMessagesAmount(loginPage.signUpErrorMessageLocator, expectedErrorsNumber);
        checkExpectedResult("Sign Out Button visible but should not"
        , !homePage.isButtonSignOutVisible());


    }




}
