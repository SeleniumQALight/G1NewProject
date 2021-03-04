package loginTests;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ParentPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTestWithPageObjectWithExel extends BaseTest {
private  String login, password;

    public LoginTestWithPageObjectWithExel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Parameters are {0} and {1}")
    public static Collection testData() throws IOException {
        InputStream spreadsSheet = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH() + "testDataSuit.xls");

        return new SpreadsheetData(spreadsSheet, "InvalidLogOn").getData();


            }


    @Test

    public void unValidLogin()  {

        loginPage.openLoinPage();
        loginPage.enterLoginSignIn(login);
        loginPage.enterPassWordSignIn(password);
        loginPage.clickButtonSignIn();
        checkExpectedResult("Button Sign In is visible.", homePage.isButtonSignOutNotVisible());

    }


}

