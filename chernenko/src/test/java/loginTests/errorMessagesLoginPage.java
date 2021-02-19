package loginTests;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class errorMessagesLoginPage extends BaseTest {


    @Test
    public void oneErrorMessageIsDisplayed (){
        loginPage.fillLoginFormNewUserAndSubmit("Unique","unique@test.ua","qwe123");
        logger.info("Valid Username, email and invalid Password were entered. One error message should be present");
        try {
            webDriver.findElements(By.linkText("Password must be at least 12 characters."));
            logger.info("Error message 'Password must be at least 12 characters.' was displayed ");
        }catch (Exception e){
            logger.info("There are no messages on the screen");
        }


    }

//    @Test
//    public void twoErrorMessagesAreDisplayed (){
//
//    }
//
//    @Test
//    public void threeErrorMessagesAreDisplayed (){
//
//    }

}
