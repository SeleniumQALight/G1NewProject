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
public class LoginTestWithPageObjectWithExcel extends BaseTest {
    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");


        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("login"));
        loginPage.enterPassWordSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible",
                homePage.isButtonSignOutVisible());
    }

    @Test
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {0}, password = {1}")
    public void inValidLogin(String login, String pass){
        loginPage.filLoginFormAndSubmit(login,pass);

        checkExpectedResult("Button SignOut is visible, but should not",
                !homePage.isButtonSignOutVisible());
    }


}