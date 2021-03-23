package loginTests;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.LoginPage;
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
    public void ValidLogin() throws IOException {
    Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");


    loginPage.openLoginPage();
    loginPage.enterLoginSignIn(dataForValidLogin.get("login"));
    loginPage.enterPassWordSignIn(dataForValidLogin.get("pass"));
    loginPage.clickButtonSignIn();

    checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
}


    @Test
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {1}, password = {2}")

    public void inValidLogin(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);

        checkExpectedResult("Button SignOut is visible, but should `t"
                , !homePage.isButtonSignOutVisible());

    }

    //HW-4
    @Test
    public void checkErrorMessageForInvalidRegUsername(){
        loginPage.openLoginPage();
        loginPage.fillRegisterForm("t",null,null);


        checkExpectedResult("Error message for invalid register username is not displayed", loginPage.isErrorMessageForInvalidRegUsernameDisplayed());
    }

    @Test
    public void checkErrorMessageForInvalidEmail(){
        loginPage.openLoginPage();
        loginPage.fillRegisterForm(null,"test",null);

        checkExpectedResult("Error message for invalid email is not displayed", loginPage.isErrorMessageForInvalidEmailDisplayed());
    }

    @Test
    public void checkErrorMessageForInvalidRegPassword(){
        loginPage.openLoginPage();
        loginPage.fillRegisterForm(null,null,"test");

        checkExpectedResult("Error message for invalid register password is not displayed", loginPage.isErrorMessageForInvalidRegPasswordDisplayed());
    }

    @Test
    public void checkErrorMessageForAllEmptyRegInputs(){
        loginPage.openLoginPage();
        loginPage.fillRegisterForm(null,null,null);
        loginPage.clickButtonSignUpForOurApp();

        checkExpectedResult("Error message for invalid register username is not displayed", !loginPage.isErrorMessageForInvalidRegUsernameDisplayed());
        checkExpectedResult("Error message for invalid email is not displayed", loginPage.isErrorMessageForInvalidEmailDisplayed());
        checkExpectedResult("Error message for invalid register password is not displayed", loginPage.isErrorMessageForInvalidRegPasswordDisplayed());
    }

    @Test
    public void checkErrorMessageForAllInvalidRegInputs(){
        loginPage.openLoginPage();
        loginPage.fillRegisterForm("1","test","test");

        checkExpectedResult("Error message for invalid register username is not displayed", loginPage.isErrorMessageForInvalidRegUsernameDisplayed());
        checkExpectedResult("Error message for invalid email is not displayed", loginPage.isErrorMessageForInvalidEmailDisplayed());
        checkExpectedResult("Error message for invalid register password is not displayed", loginPage.isErrorMessageForInvalidRegPasswordDisplayed());
    }

    @Test
    public void checkErrorMessageForInvalidRegUsernameAndEmail(){
        loginPage.openLoginPage();
        loginPage.fillRegisterForm("1","test", "123456qwerty");

        checkExpectedResult("Error message for invalid register username is not displayed", loginPage.isErrorMessageForInvalidRegUsernameDisplayed());
        checkExpectedResult("Error message for invalid email is not displayed", loginPage.isErrorMessageForInvalidEmailDisplayed());
        checkExpectedResult("Error message for invalid register password is displayed", !loginPage.isErrorMessageForInvalidRegPasswordDisplayed());
    }

    }

