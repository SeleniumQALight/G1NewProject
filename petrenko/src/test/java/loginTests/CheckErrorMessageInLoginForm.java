package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CheckErrorMessageInLoginForm extends BaseTest {

    @Test
    @Parameters({
            "bald2004,tetyyytetyyytetyyy,0,",
            ",tetyyytetyyytetyyy,1,Invalid username \\ password",
            "bald2004,,1,Invalid username \\ password",
            ",,1,Invalid username \\ password"
    })
    @TestCaseName("checkErrorMessageInLoginForm: userName = {0}, password = {1}, countUnValidValue = {2}, textOfErrorMessages = {3}")

    public void checkErrorMessageInLoginForm(String userName, String password, int countUnValidValue, String textOfErrorMessages) {

        loginPage.fillLoginFormAndSubmit(userName, password)
                .checkCountErrorOfMessagesAfterSubmitLoginIn(countUnValidValue)
                .checkTextOfErrorsInLoginIn(textOfErrorMessages);

    }


}
