package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage (WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);

    }
    public void enterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

    public void clickOnElement(WebElement webElement){
        try{
            webElement.click();
            logger.info("Element was clicked");

        }catch(Exception e){
           printErrorMessageAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            logger.info("Element displayed : " + state);
            return state;
        }catch (Exception e){
            logger.info("Element displayed : false");
            return false;
        }
    }

}
