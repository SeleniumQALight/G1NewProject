package loginTests;
import baseTest.BaseTest;
import org.junit.Test;


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
    public void inValidLogin(){
        loginPage.filLoginFormAndSubmit("WrongLogin", "123456qwerty");
        checkExpectedResult("Button SignOut is visible, but should not",
                !homePage.isButtonSignOutVisible());
    }


}
