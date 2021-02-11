package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver){ //constrictor
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this); //initialization elements FindBy for "this" page
    }

    //======METHODS WITH ELEMENTS================

    // ENTER TEXT TO THE ELEMENT
    protected void enterTextInToElement (WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    // CLICK ON THE ELEMENT
    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    // IS ELEMENT DISPLAYED????
    protected boolean isElementDisplayed(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            logger.info("Element displayed : " + state);
            return state;
        } catch (Exception e){
            logger.info("Element displayed : false");
            return false;
        }

    }

      // IN CASE OF EXCEPTION
    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element" + e); // will write to log
        Assert.fail("Can not work with element" + e); // will write to report
    }

    protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }
}
