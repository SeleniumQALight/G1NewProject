package RegistrationTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class RegInvalidTests extends BaseTest {

// HOMEWORK 02-20

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

}


