package regestrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class CheckErrorMessage extends BaseTest {




    @Test
    @Parameters({
           "qwerty,bald2004@ukr.nrtet,123456qwerty, 0, ",
            ",abc@,123456qwerty, 2,Username must be at least 3333 characters.;You must provide a valid email address.",
            ",abc@,56qwerty, 3,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "qwerty,bald2004@ukr.nrtet,12,1,Password must be at least 121221 characters."
    })
    @TestCaseName("checkErrorMessageInRegisterForm: userName = {0}, email = {1}, password = {2}, countUnValidValue = {3}, textOfErrorMessages = {4}")

    public void checkErrorMessageInRegisterForm(String userName, String email, String password, int countUnValidValue, String textOfErrorMessages) {
        loginPage.fillRegisterFormAndSubmit(userName, email, password).checkCountErrorOfMessagesAfterSubmitRegisterIn(countUnValidValue)
                .checkTextOfErrorsInRegisterIn(textOfErrorMessages);


    }





}
