package regestrationTests;


import baseTest.BaseTest;
import org.junit.Test;
import pages.LoginPage;


public class CheckErrorMessage extends BaseTest {





    @Test
    public void checkErrorMessageInRegisterForm(){
        loginPage.checkPopUpErrorsAfterRegisterSubmit(LoginPage.unValidRegisterUserName,LoginPage.unValidRegisterEmail,LoginPage.unValidRegisterPassword);

    }

}
