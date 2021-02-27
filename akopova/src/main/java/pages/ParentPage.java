package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

abstract class ParentPage {

    @FindBy(xpath = ".//*[contains(text(),'Complex app for testing')]")
    private WebElement homePageLink;

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, getWebDriverWait15, getWebDriverWait20;

    // creating object of config.properties and create pairs of keys listed there
    protected static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();

    Logger logger = Logger.getLogger(getClass());
    public ParentPage (WebDriver webDriver)
    {
        this.webDriver = webDriver;
        //PageFactory.initElements(webDriver, this);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver))
                ,this);

        webDriverWait10 = new WebDriverWait(webDriver, 10);
        getWebDriverWait15 = new WebDriverWait(webDriver, 15);
        getWebDriverWait20 = new WebDriverWait(webDriver, 20);

    }

    abstract String getRelativeUrl();

    protected void waitChatToBeHide() {
        webDriverWait10.until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }


    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            getWebDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("text " + text + " was inserted into element" + getElementName(webElement));
        }
        catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName="";
        if (webElement instanceof TypifiedElement) {
            elementName=" '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;

    }

    public void clickOnElement(WebElement webElement) {
        try {
            getWebDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + "Element was clicked");
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    public void clickOnElement(WebElement webElement, String elementName) {
        try {
            getWebDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }



    protected boolean isElementDisplayed(WebElement webElement) {
        try{
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + "Element displayed : " + state);
            return state;
        }
        catch (Exception e){
            logger.info(getElementName(webElement) + "Element is not yet displayed : false");
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue( "element is not visible", isElementDisplayed(webElement));

    }

    protected void checkIsElementEnabled(WebElement webElement) {
        Assert.assertTrue("Element " + webElement.getTagName() + " is not Clickable"
        , isElementEnabled(webElement));
    }

    protected boolean isElementEnabled(WebElement webElement) {
        try{
            boolean state = webElement.isEnabled();
            logger.info(getElementName(webElement) + "Element enabled : " + state);
        return state;

    }
        catch (Exception e){
            logger.info(getElementName(webElement) + "Element is not yet enabled : false");
            return false;
    }
    }




    protected void selectTextInDropDown(WebElement webElement, String text) {
        try{
            Select select = new Select(webElement);
            // Here webElement is closed dropdown
            select.selectByVisibleText(text);
            logger.info(text + "was selected in dropdown" + getElementName(webElement));
        }
        catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try{
            Select select = new Select(webElement);
            // Here webElement is closed dropdown
            select.selectByValue(value); // values are to be sent to backenend
            logger.info(value + "was selected in dropdown" + getElementName(webElement));
        }
        catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
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

    public ParentPage clearElement (WebElement webElement) {
        webElement.clear();
        return this;
    }

}