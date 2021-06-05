package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrivatbankMainPage extends ParentPage {

    private final DecimalFormat df = new DecimalFormat("0.00000");

    @FindBy(xpath = "//h4[text()='Новини']")
    private WebElement newsCaption;

    @FindBy(xpath = "//h4[text()='Курси валют']")
    private WebElement exchRatesCaption;

    @FindBy(xpath="//div[@data-cource_type='posts_course']//tbody/tr/td[1]")
    private List<WebElement> ccyDisplayedList;

    @FindBy(id = "EUR_buy")
    private WebElement buyEuro;

    @FindBy(id = "EUR_sell")
    private WebElement sellEuro;

    @FindBy(id = "USD_buy")
    private WebElement buyUSD;

    @FindBy(id = "USD_sell")
    private WebElement sellUSD;

    @FindBy(id = "RUB_buy")
    private WebElement buyRUB;

    @FindBy(id = "RUB_sell")
    private WebElement sellRUB;


    public PrivatbankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeUrl() {
        return "/";
    }

    public boolean verifyMainPageOpened() {
        return isElementDisplayed(newsCaption);
    }

    @Step
    public boolean isExchRatesCaptionDisplayed() {
        return (isElementDisplayed(exchRatesCaption));
    }

    @Step
    public PrivatbankMainPage openMainPage() {
        try {
            webDriver.get(baseUrlPrivatbank + getRelativeUrl());
            assertTrue(verifyMainPageOpened());
            logger.info("Main Page is opened");
        } catch (Exception e) {
            fail("Can not open Main Page");
        }
        return this;
    }

    @Step
    public List<String> getCcyDisplayedList(){
        logger.info("Getting the list of currencies displayed on th Main page");
        List<String> ccyListString = new ArrayList<>();
        ccyDisplayedList.forEach(val->ccyListString.add(val.getText()));
        logger.info("Currencies list is: " + ccyListString);
        return ccyListString;
    }

    @Step
    public String[] getCcyRate(String ccy) {
        logger.info("Getting exchange rates [buy,sell] for ccy: "+ccy);
        String[] exchRates = new String[2];
        switch (ccy) {
            case "EUR":
            case "Euro":
                exchRates[0] = getCcyValueFormatted(buyEuro);
                exchRates[1] = getCcyValueFormatted(sellEuro);
                break;
            case "USD":
                exchRates[0] = getCcyValueFormatted(buyUSD);
                exchRates[1] = getCcyValueFormatted(sellUSD);
                break;
            case "RUB":
            case "RUR":
                exchRates[0] = getCcyValueFormatted(buyRUB);
                exchRates[1] = getCcyValueFormatted(sellRUB);
                break;
            default: fail("no valid currency name was given");
        }
        return exchRates;
    }

    private String getCcyValueFormatted(WebElement webElement) {
        checkIsElementVisible(webElement);
        return df.format(Double.valueOf(webElement.getText()));
    }
}
