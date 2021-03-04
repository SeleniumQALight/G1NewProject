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


public class LoginTestWithPageObject extends BaseTest {


    public LoginTestWithPageObject() throws IOException {
    }

    @Test
    public void validLogin() throws IOException {
        Map<String, String > dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(), "validLogOn");
        loginPage.fillLoginFormAndSubmit(dataForValidLogin.get("login"), dataForValidLogin.get("pass"));
        checkExpectedResult("Button Sign In is not visible.", homePage.isButtonSignOutVisible());

    }

//    @Test
//    @Parameters({
//     "qwerty, 123456qwerty",
//            ", 123456qwerty"
//
//
//    })
//    @TestCaseName("inValidLogin: login = {0}, passWord = {1}")
//
//
//    public void unValidLogin(String login, String password) throws IOException {
//
//        loginPage.openLoinPage();
//        loginPage.enterLoginSignIn(login);
//        loginPage.enterPassWordSignIn(password);
//        loginPage.clickButtonSignIn();
//        checkExpectedResult("Button Sign In is visible.", homePage.isButtonSignOutNotVisible());
//
//    }


}
