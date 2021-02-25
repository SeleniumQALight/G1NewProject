package LoginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class) // it means that class use parameters
public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPassSignIn("123456qwerty");
        loginPage.clickButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutVisible());
    }

//INVALID LOGIN
    @Test
    public void invalidPasswordOLD(){
       loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");
       checkExpectedResult("Button SignOut is visible, but should't", !homePage.isButtonSignOutVisible());
    }

//INVALID LOGIN WITH PARAMETERS
    @Test
    @Parameters({
            "WrongLogin,123456qwerty",
            ",123456qwerty"
    })
    @TestCaseName("inValidLogin: login = {0}, password = {1}")
    public void invalidPassword(String login, String pass){
        loginPage.fillLoginFormAndSubmit(login, pass);
        checkExpectedResult("Button SignOut is visible, but should't", !homePage.isButtonSignOutVisible());
    }

}
