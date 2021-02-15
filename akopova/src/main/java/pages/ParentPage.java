package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {

    @FindBy(xpath = ".//*[contains(text(),'Complex app for testing')]")
    private WebElement homePageLink;

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage (WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("text " + text + " was inserted into element");
        }
        catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    public void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }


    protected boolean isElementDisplayed(WebElement webElement) {
        try{
            boolean state = webElement.isDisplayed();
            logger.info("Element displayed : " + state);
            return state;
        }
        catch (Exception e){
            logger.info("Element displayed : false");
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue("element is not visible", isElementDisplayed(webElement));

    }


    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    public void clickOnHomePageLink() {
        try {
            clickOnElement(homePageLink);
            Thread.sleep(1000);
            logger.info("Homepage opened");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}