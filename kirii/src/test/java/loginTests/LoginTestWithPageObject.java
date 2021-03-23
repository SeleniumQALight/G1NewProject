package loginTests;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Assert;
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
    public void validLogIn() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(), "validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
    }

    /*@Test
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {0}, password = {1}")
    public void invalidLogIn(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);

        checkExpectedResult("Button SignOut is visible, but shouldn't 't", !homePage.isButtonSignOutVisible());
    }*/
    @Test
    @Parameters({
            "us,,,1",
            ",email,,1",
            ",,password,1",
            "us,11,password,3",
            "username,email,123456qwerty,1"
    })
    @TestCaseName("checkErrorMessagesForInvalidCredentials: login = {0}, email = {1}, password = {2}, count error messages {3}")
    public void checkErrorMessagesForInvalidCredentials(String login, String email, String pass, int expectedCountOfErrorMessages) {
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(login, email, pass);
        int actualCountOfErrorMessages = loginPage.countValidateMessages();
        Assert.assertEquals("Wrong error messages number", expectedCountOfErrorMessages, actualCountOfErrorMessages);
    }
}