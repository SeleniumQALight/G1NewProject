package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() throws IOException {

        //02-03-2021
        //1st parameter is path to our file; 2nd is name of file where we're getting our values
        Map<String,String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE()
                , "validLogOn");

        // End of 02-02-2021

        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();
        checkExpectedResult("SignOut Button is not visible", homePage.isButtonSignOutVisible());

    }


    @Test
    @Parameters({
            "WrongLogin, 123456qwerty",
            ",WrongPassword"

    })
    @TestCaseName("inValidLogin : login = {0}, password = {1}")
    public void inValidLogin(String login, String password) {
        loginPage.fillLoginFormAndSubmit(login, password);
        // To be added
        /**
        loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");
        loginPage.fillLoginFormAndSubmit("WrongLogin", "WrongPassword");
         */

        checkExpectedResult("SignOut Button is visible but should not"
                , !homePage.isButtonSignOutVisible());
    }

}
