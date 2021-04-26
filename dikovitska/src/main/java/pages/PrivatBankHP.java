package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class PrivatBankHP {
    protected WebDriver webDriver;

    public PrivatBankHP(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
    }

    protected final String PRIVATBANK_HP_URL = "http://privatbank.ua";

    final String rateBuyUILocator = ".//td[@id='%s_buy']";

    final String rateSellUILocator = ".//td[@id='%s_sell']";
    String rateBuyUI;
    String rateSellUI;

    protected WebDriverWait webDriverWait10;

    Logger logger = Logger.getLogger(getClass());

    public void openPrivatBankHP(){
        try {
            webDriver.get(PRIVATBANK_HP_URL);
            logger.info("PrivatBank Home Page was opened");
        }catch (Exception e){
            logger.error("Can not open PrivatBank Home Page");
            Assert.fail("Can not open PrivatBank Home Page");
        }
    }

    public void getRateFromUI (String currency){
        rateBuyUI = webDriver.findElement(By.xpath(String.format(rateBuyUILocator, currency))).getText();
        rateSellUI = webDriver.findElement(By.xpath(String.format(rateSellUILocator, currency))).getText();
        logger.info("Exchange rate on PrivatBank site" + currency +
                " - buy " + rateBuyUI + " and sell " + rateSellUI);
    }

    public void checkIsRatesEquals() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(rateBuyUI).isEqualTo(TestData.rateBuyApi);
        softAssertions.assertThat(rateSellUI).isEqualTo(TestData.rateSellApi);
        softAssertions.assertAll();
    }
}
