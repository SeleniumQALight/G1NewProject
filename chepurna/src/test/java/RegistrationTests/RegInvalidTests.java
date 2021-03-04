package RegistrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import static libs.TestData.*;

@RunWith(JUnitParamsRunner.class)
public class RegInvalidTests extends BaseTest {

// REGISTRATION INVALID (HOMEWORK 02-20)

    @Test
    public void regUserAlreadyExists(){
        loginPage.fillRegFormAndSubmit(TestData.VALID_LOGIN, TestData.EMAIL_ALREDY_EXISTS, TestData.VALID_PASSWORD)
                .checkIsValidationMessageDisplayed("That username is already taken.")
                .checkIsValidationMessageDisplayed("That email is already being used.")
                .checkAndCoundValidatiotMessages(2);
    }

    @Test
    public void regUserInvalidFields(){
        loginPage.fillRegFormAndSubmit(TestData.INVALID_USERNAME, TestData.INVALID_EMAIL, TestData.INVALID_PASSWORD)
                .checkIsValidationMessageDisplayed("Username must be at least 3 characters.")
                .checkIsValidationMessageDisplayed("You must provide a valid email address.")
                .checkIsValidationMessageDisplayed("Password must be at least 12 characters.")
                .checkAndCoundValidatiotMessages(3);
    }

// REGISTRATION INVALID WITH PARAMETERS (HOMEWORK 02-27)
    @Test
    @Parameters(value = {
            VALID_LOGIN + "," + VALID_EMAIL+ "," + VALID_PASSWORD + ",1",
            VALID_LOGIN + "," + EMAIL_ALREDY_EXISTS + "," + VALID_PASSWORD + ",2",
            INVALID_USERNAME + "," + INVALID_EMAIL + "," + INVALID_PASSWORD + ",3"
    })
    @TestCaseName("inValidRegistration: username = {0}, email = {1}, password = {2}, quantity = {3}")
    public void regUserInvalidFieldsWithParmeters(String username, String email, String password, int qty){
        loginPage.fillRegFormAndSubmit(username, email, password)
                .checkAndCoundValidatiotMessages(qty);
    }

}


