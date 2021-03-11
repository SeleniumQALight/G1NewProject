package loginTest;

import baseTest.BaseTest;
import io.qameta.allure.*;
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
    @Test
    public void validLogin() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("login1"));
        loginPage.enterPasswordSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
     }
    @Test
    @Parameters({
            "WrongLogin, 123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName ("inValidLogin: login = {0}, passWord ={1}")
    public void inValidLogin(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);
        checkExpectedResult("Button SignOut is visible, but shouldn't", !homePage.isButtonSignOutVisible());
    }


}
