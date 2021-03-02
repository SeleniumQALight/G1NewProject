package loginTests;
import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginSignIn("auto");
        loginPage.enterPassWordSignIn("123456qwerty");
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
