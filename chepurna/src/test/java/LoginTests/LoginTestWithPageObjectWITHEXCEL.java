package LoginTests;

import Pages.ParentPage;
import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@RunWith(Parameterized.class)
public class LoginTestWithPageObjectWITHEXCEL extends BaseTest {
    private String login, pass;

    public LoginTestWithPageObjectWITHEXCEL(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

//USE EXCEL === INVALID LOGIN WITH PARAMETERS
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream spreadsSheet = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH()+"testDataSuit.xls"); //connection
        return new SpreadsheetData(spreadsSheet, "InvalidLogOn").getData();
    }
    @Test

    public void invalidPassword(){
        loginPage.fillLoginFormAndSubmit(login, pass);
        checkExpectedResult("Button SignOut is visible, but should't", !homePage.isButtonSignOutVisible());
    }




}
