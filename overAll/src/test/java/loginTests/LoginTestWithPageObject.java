package loginTests;

import java.io.IOException;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import pages.ParentPage;

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
        //test
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(), "validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("log1in"))
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
