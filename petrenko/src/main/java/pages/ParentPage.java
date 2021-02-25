package pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ParentPage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }


    protected void waitChatToBeHide() {
        webDriverWait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was input in to element");

        } catch (Exception e) {
            logger.info("");
            printErrorMessageAndStopTest(e);
        }
    }


    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);

    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("element was clicked.");

        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            logger.info("Element displayed:" + state);
            return state;
        } catch (Exception e) {
            logger.info("Element displayed:" + false);
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    protected void checkIsElementUnVisible(WebElement webElement) {
        Assert.assertTrue("Element is visible", !isElementDisplayed(webElement));
    }
protected  void selectTextInDropDown(WebElement webElement, String text){
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info(text + "was selcted in DropDown.");

        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
}

    protected  void selectValueInDropDown(WebElement webElement, String value){
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info(value + "was selcted in DropDown.");

        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

}
