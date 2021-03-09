package loginTests;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import pages.ParentPage;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(), "validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("logi1n"));
        loginPage.enterPassWordSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible"
                , homePage.isButtonSignOutVisible());
    }

    @Test
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {0}, passWord = {1}")
    public void inValidLogin(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);

        checkExpectedResult("Button SignOut is visible, but should `t"
                , !homePage.isButtonSignOutVisible());

    }






}
