package loginTests;

import baseTest.BaseTest;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ParentPage;

import javax.imageio.stream.FileImageOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//RinWithnadded on 02-03-2021
@RunWith(Parameterized.class)
public class LoginTestWithPageObjectWithExcel extends BaseTest {

    private String login, pass;

    public LoginTestWithPageObjectWithExcel(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    //02-03-2021
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
//here we have to point path to gine (02-03-2020)
        InputStream spreadSheet = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH()
        + "testDataSuite.xls");
    return new SpreadsheetData(spreadSheet, "InvalidLogin").getData();

    }


    @Test

    public void inValidLogin(String login, String password) {
        loginPage.fillLoginFormAndSubmit(login, password);
        // To be added
        /**
        loginPage.fillLoginFormAndSubmit("WrongLogin", "123456qwerty");
        loginPage.fillLoginFormAndSubmit("WrongLogin", "WrongPassword");
         */

        checkExpectedResult("SignOut Button is visible but should not"
                , !homePage.isButtonSignOutVisible());
    }

}
