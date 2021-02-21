package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.ParentPage;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class errorMessagesLoginPage extends BaseTest {




    @Test
    public void numberOfErrorMessageWereDisplayed (){
        // Use for the test date from TestData file
        loginPage.fillLoginFormNewUserAndSubmit(TestData.VALID_LOGIN,TestData.UNIQUE_USER_EMAIL,TestData.VALID_PASSWORD);
        Util.waitABit(2);
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".// div[contains(@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible')]"));
        logger.info(list.size() + "  error messages were displayed");
    }



}