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

    }

