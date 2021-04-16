package pages;

import io.qameta.allure.Step;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePrivat {
    WebDriver webDriver = DriverHelper.getWebDriver();
    Logger logger = Logger.getLogger(getClass());

    String buy = ".//*[@id = '%s_buy']";
    String sell = ".//*[@id = '%s_sell']";
    String rub = "RUB";

    @Step
    public void openHomePage() {
        try {
            webDriver.get("https://privatbank.ua/");
            logger.info("Login page was open.");
        } catch (Exception e) {
            logger.info("Can not open loginPage.");
            Assert.fail("Can not open loginPage.");
        }

    }

    @Step
    public void saveCurseByValueCurrency(String currency) {
        try {

            TestData.RATE_CURRENCY_BY_UI = webDriver.findElement(By.xpath(String.format(buy, currency))).getText();
            logger.info( TestData.RATE_CURRENCY_BY_UI);
            TestData.RATE_CURRENCY_SELL_UI = webDriver.findElement(By.xpath(String.format(sell, currency))).getText();
            logger.info(TestData.RATE_CURRENCY_SELL_UI);
        }
        catch (Exception e){
            logger.info(e);
        }
    }
}