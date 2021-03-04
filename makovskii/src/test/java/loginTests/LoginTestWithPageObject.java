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
import java.util.ArrayList;
import java.util.Collection;


@RunWith(Parameterized.class)
public class LoginTestWithPageObject extends BaseTest {
    private String login, pass;

    public LoginTestWithPageObject(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }



    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream speadsSheet = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(speadsSheet, "InvalidLogOn").getData();
    }


    @Test
    public void inValidLogin(){
        loginPage.filLoginFormAndSubmit(login,pass);

        checkExpectedResult("Button SignOut is visible, but should not"
                , !homePage.isButtonSignOutVisible());
    }


}
