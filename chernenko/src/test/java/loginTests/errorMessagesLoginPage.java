package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebElement;



import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class errorMessagesLoginPage extends BaseTest {

    @Test
    public void oneErrorMessageIsDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_LONG_USERNAME,TestData.INVALID_EMAIL,TestData.INVALID_LONG_PASSWORD);
        int number = loginPage.numberOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",1,number);
    }


    @Test
    public void twoErrorMessagesAreDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_SHORT_USERNAME,TestData.VALID_UNIQUE_USER_EMAIL,TestData.INVALID_SHORT_PASSWORD);
        int number = loginPage.numberOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",2,number);
    }

    @Test
    public void threeErrorMessagesAreDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_LONG_USERNAME,TestData.INVALID_EMAIL,TestData.INVALID_LONG_PASSWORD);
        int number = loginPage.numberOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",3,number);
    }


}
