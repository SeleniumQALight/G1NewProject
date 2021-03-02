package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.LoginPage;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
@Test
    public void ValidLogin() {
    loginPage.openLoginPage();
    loginPage.enterLoginSignIn("auto");
    loginPage.enterPassWordSignIn("123456qwerty");
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

