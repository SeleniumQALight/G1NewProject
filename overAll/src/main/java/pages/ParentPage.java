package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

abstract public class ParentPage {
    protected String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";
    protected WebDriver webDriver;
    WebDriverWait webDriverWait;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage (WebDriver webDriver){
        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait = new WebDriverWait(webDriver, 5);
    }

    abstract String getRelativeUrl();

    protected void enterTextInToElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info( getElementName(webElement) + " Element was clicked" );

        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName){
        try{
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info( elementName + " Element was clicked" );

        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + "Element displayed : " + state);
            return state;
        }catch (Exception e){
            logger.info(getElementName(webElement) + "Element displayed : false");
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue(getElementName(webElement) +"Element is not visible", isElementDisplayed(webElement));
    }

    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

    public void waitPageLoaded(){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@class='chat-title-bar']")));
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

}
