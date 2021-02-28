package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;



import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
@RunWith(JUnitParamsRunner.class)
public class errorMessagesLoginPage extends BaseTest {

    @Test
    public void oneErrorMessageIsDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.VALID_UNIQUE_USERNAME,TestData.INVALID_EMAIL,TestData.VALID_PASSWORD);
        List<WebElement> list = new ArrayList<>();
        list = loginPage.listOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",1,list.size());
    }


    @Test
    public void twoErrorMessagesAreDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_SHORT_USERNAME,TestData.VALID_UNIQUE_USER_EMAIL,TestData.INVALID_SHORT_PASSWORD);
        List<WebElement> list = new ArrayList<>();
        list = loginPage.listOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",2,list.size());
    }

    @Test
    public void threeErrorMessagesAreDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_LONG_USERNAME,TestData.INVALID_EMAIL,TestData.INVALID_LONG_PASSWORD);
        List<WebElement> list = new ArrayList<>();
        list = loginPage.listOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",3,list.size());
    }

    @Test
    @Parameters({
            "qa,test_unique@test.com,123456qwerty, 1",
            "testnameunique,test_unique,123, 2",
            "1234567890qwertyuiopqwertyuiop1,test,123456qwerty123456qwerty123456qwerty123456qwerty123456qwerty, 3"
    })
    @TestCaseName(
            "numbersOfMessagesWereDisplayed: username = {1}, email = {2}, password = {3} "
    )
    public void checkErrorMessagesTestWithParameters(String username, String email, String password, int expectedResult){
        loginPage.fillLoginFormNewUserAndSubmit(username, email, password);
        Assert.assertEquals("ERROR: The number of displayed errors is wrong. ", expectedResult, loginPage.numberOfErrorMessageWereDisplayed());
    }
}
