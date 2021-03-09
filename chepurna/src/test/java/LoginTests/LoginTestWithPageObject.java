package LoginTests;

import Pages.ParentPage;
import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

@RunWith(JUnitParamsRunner.class) // it means that class use parameters
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Story("Base support for bdd annotations")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)

  /*  @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPassSignIn("123456qwerty");
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
    }

   */

//INVALID LOGIN
    @Test
    public void invalidPasswordOLD(){
       loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");
       checkExpectedResult("Button SignOut is visible, but should't", !homePage.isButtonSignOutVisible());
    }

//INVALID LOGIN WITH PARAMETERS
    @Test
    @Ignore
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {0}, password = {1}")
    public void invalidPassword(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);
        checkExpectedResult("Button SignOut is visible, but should't", !homePage.isButtonSignOutVisible());
    }

//USE EXCEL
    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("login"));
        loginPage.enterPassSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
    }


}
