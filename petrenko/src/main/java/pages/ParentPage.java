package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    protected void  enterTextInToElement(WebElement webElement, String text){
        try{

            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was input in to element");

        }catch (Exception e){
            logger.info("");
           printErrorMessageAndStopTest(e);
        }
    }


    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);

    }

    protected void clickOnElement(WebElement element) {
        try {

            element.click();
            logger.info("element was clicked.");

        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }
    protected  boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            logger.info("Element displayed:" + state);
        return     state;
        }catch (Exception e){
            logger.info("Element displayed:" + false);
            return  false;
        }
    }

        protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
        }

        protected  void checkIsElementUnVisible(WebElement webElement){
            Assert.assertTrue("Element is visible", !isElementDisplayed(webElement));
        }






}
