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
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class LoginTestWithPageObjectWithExcel extends BaseTest {
    private String login, pass;

    public LoginTestWithPageObjectWithExcel(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Parameterized.Parameters(name = "Parameters are {0} and {1}")
    public static Collection testData() throws IOException {
        InputStream spreadsSheet = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH()
                + "testDataSuit.xls");
        return new SpreadsheetData(spreadsSheet, "InvalidLogOn").getData();
    }

    @Test
    public void inValidLogin(){
        loginPage.fillLoginFormAndSubmit(login, pass);

        checkExpectedResult("Button SignOut is visible, but should `t"
                , !homePage.isButtonSignOutVisible());

    }
}
