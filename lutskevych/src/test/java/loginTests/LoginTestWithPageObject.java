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
    public void validLogin() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(),"validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLoginSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordSignIn(dataForValidLogin.get("pass"));
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible",homePage.isButtonSignOutVisible());
    }
//    @Test
//    @Parameters({
//            "WrongLogin,123456qwerty",
//            ",123456qwerty",
//            "WrongLogin,"
//    })
//    public void inValidLogin(String login, String pass){
////        loginPage.openLoginPage();
//////        loginPage.enterLoginSignIn("auto");
//////        loginPage.enterPasswordSignIn("123456");
//////        loginPage.clickButtonSignIn();
//        loginPage.fillLoginFormAndSubmit(login, pass);
//
//        checkExpectedResult("Alert Errorr is not visible",loginPage.isAllertErrorrVisible());
//        checkExpectedResult("Button Sign Up For Our App Is visible",loginPage.buttonSignUpForOurAppIsVisible());
//    }
    @Test
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {0}, password {1}")
    public void inValidLogin(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);

        checkExpectedResult("Button SignOut is visible, but should `t"
                , !homePage.isButtonSignOutVisible());

    }
    @Test
    @Parameters({
            "id,,,1",
            ",12,,1",
            ",,pass,1",
            "id,98,pass,3",
            "id,95,123456qwerty,2"
    })
    @TestCaseName("checkErrorMessagesForInvalidCredentials: login = {0}, email = {1}, password = {2}, count error messages {3}")
    public void checkErrorMessagesForInvalidCredentials(String login,String email, String pass, int expectedCountOfErrorMessages){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(login,email,pass);
        int actualCountOfErrorMessages = loginPage.countValidateMessages();
        Assert.assertEquals("Wrong number of error messages", expectedCountOfErrorMessages, actualCountOfErrorMessages);

    }



    @Test
    public void checkErrorMessageForInvalidId(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("id",null,null);

        checkExpectedResult("Alert for invalid id is not visible", loginPage.isAlertForInvalidIdVisible());
    }
    @Test
    public void checkErrorMessageForInvalidEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(null,"12",null);

        checkExpectedResult("Alert for invalid email is not visible", loginPage.isAlertForInvalidEmailVisible());
    }
    @Test
    public void checkErrorMessageForInvalidPass(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(null,null,"pass");

        checkExpectedResult("Alert for invalid pass is not visible", loginPage.isAlertForInvalidPassVisible());
    }
    @Test
    public void checkErrorMessageForInvalidIdPassEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("id","98","pass");

        checkExpectedResult("Alert for invalid id is not visible", loginPage.isAlertForInvalidIdVisible());
        checkExpectedResult("Alert for invalid pass is not visible",loginPage.isAlertForInvalidPassVisible());
        checkExpectedResult("Alert for invalid email is not visible",loginPage.isAlertForInvalidEmailVisible());
    }
    @Test
    public void checkErrorMessageForEmptyIdPassEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm(null,null,null);
        loginPage.clickButtonSignUpForOurApp();

        checkExpectedResult("Alert for invalid id is not visible",loginPage.isAlertForInvalidIdVisible());
        checkExpectedResult("Alert for invalid pass is not visible",loginPage.isAlertForInvalidPassVisible());
        checkExpectedResult("Alert for invalid email is not visible",loginPage.isAlertForInvalidEmailVisible());
    }
    @Test
    public void checkErrorMessageForInvalidIdEmail(){
        loginPage.openLoginPage();
        loginPage.fullFillLoginForm("id","95", "123456qwerty");

        checkExpectedResult("Alert for invalid id is not visible",loginPage.isAlertForInvalidIdVisible());
        checkExpectedResult("Alert for invalid email is not visible",loginPage.isAlertForInvalidEmailVisible());
        checkExpectedResult("Alert for invalid pass is visible",loginPage.isAlertForInvalidPassInvisible());
    }


}
