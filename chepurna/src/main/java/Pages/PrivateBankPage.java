package Pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class PrivateBankPage {

    protected WebDriver webDriver;

    public PrivateBankPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
    }

    protected final String PRIVAT_URL = "http://privatbank.ua";

    final String rateBuyUAILocator = ".//td[@id='%s_buy']";

    final String rateSaleUAILocator = ".//td[@id='%s_sell']";
    String rateBuyUAI;
    String rateSellUAI;

    protected WebDriverWait webDriverWait15;

    Logger logger = Logger.getLogger(getClass());

    public void openPrivatBankPage(){
        try {
            webDriver.get(PRIVAT_URL);
            logger.info("PrivatBank Page was opened");
        }catch (Exception e){
            logger.error("Can not open PrivatBank page");
            Assert.fail("Can not open PrivatBank page");
        }
    }

    public void getCurrensyRateToUAHfromUAI(String currensy){
        rateBuyUAI = webDriver.findElement(By.xpath(String.format(rateBuyUAILocator, currensy))).getText();
        rateSellUAI = webDriver.findElement(By.xpath(String.format(rateSaleUAILocator, currensy))).getText();
        logger.info("Курс на сейте Приват Банка " + currensy +
                " к UAH : покупка " + rateBuyUAI + " и продажа " + rateSellUAI);
    }

    public void checkIsRatesEquals() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(rateBuyUAI).isEqualTo(TestData.rateBuyAPI);
        softAssertions.assertThat(rateSellUAI).isEqualTo(TestData.rateSellAPI);
        softAssertions.assertAll();
    }
}

