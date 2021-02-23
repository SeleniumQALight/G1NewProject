package pages;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import libs.ConfigProperties;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

abstract class ParentPage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    protected static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();
    Logger logger = Logger.getLogger(getClass());
    public ParentPage (WebDriver webDriver){
        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);

        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

    abstract String getRelativeUrl();

    protected void waitChatToBeHide(){
        webDriverWait10.until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    protected void enterTextInToElement(WebElement webElement, String text){
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element" + getElementName(webElement));
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement){
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + "Element was clicked");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement , String elementName){
        try{
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + "Element was clicked");
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
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }


    protected void selectTextInDropDown(WebElement webElement, String text){
        try{
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(webElement));
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value){
        try{
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(webElement));
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

}
