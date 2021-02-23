package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class errorMessagesLoginPage extends BaseTest {


    @Test
    public void oneErrorMessageIsDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.VALID_UNIQUE_USERNAME,TestData.INVALID_EMAIL,TestData.VALID_PASSWORD);
        List<WebElement> list = new ArrayList<>();
        list = loginPage.numberOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",1,list.size());
    }


    @Test
    public void twoErrorMessagesAreDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_SHORT_USERNAME,TestData.VALID_UNIQUE_USER_EMAIL,TestData.INVALID_SHORT_PASSWORD);
        List<WebElement> list = new ArrayList<>();
        list = loginPage.numberOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",2,list.size());
    }

    @Test
    public void threeErrorMessagesAreDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.INVALID_LONG_USERNAME,TestData.INVALID_EMAIL,TestData.INVALID_LONG_PASSWORD);
        List<WebElement> list = new ArrayList<>();
        list = loginPage.numberOfErrorMessageWereDisplayed();
        Assert.assertEquals("Wrong numbers of messages ",3,list.size());
    }

}
