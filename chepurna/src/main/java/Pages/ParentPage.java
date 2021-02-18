package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParentPage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver){ //constrictor
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this); //initialization elements FindBy for "this" page
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

     // to aviod chat
protected void waitChatToBeHide(){
    webDriverWait10.until(ExpectedConditions
            .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
}

    //======METHODS WITH ELEMENTS================

    // ENTER TEXT TO THE ELEMENT
    protected void enterTextInToElement (WebElement webElement, String text){
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
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
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
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

    // SELECT IN DROP DOWN === JAVA need time
    protected void selectTestinDropDown(WebElement webElement, String text){
        try{
            Select select = new Select (webElement);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

// SELECT VALUE THAT WILL GO TO DB === JAVA quick
    protected void selectValueinDropDown(WebElement webElement, String value){
        try{
            Select select = new Select (webElement);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
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
