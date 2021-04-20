package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PrivatLandingPage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public PrivatLandingPage(WebDriver webDriver) {
      this.webDriver = webDriver;
    }

    @Step
    public void openPrivatLandingPage(){
        try {
            webDriver.get("https://privatbank.ua/ru");
            logger.info("Privat Landing page was opened");
        } catch (Exception e){
            logger.error("Can not open Landing page");
            Assert.fail("Can not open Landing page");
        }
    }

    @Step
    public void getBuyValueFromUI(String currencyName) {
    openPrivatLandingPage();
    String currencyLocator = String.format(".//*[@id='%s_buy']",currencyName);
        TestData.BUY_VALUE_UI = webDriver.findElement(By.xpath(currencyLocator)).getText();
        logger.info("Currency displayed on UI  " + TestData.BUY_VALUE_UI + " BUY");

    }

    @Step
    public void getSaleValueFromUI(String currencyName) {
        String currencyLocator = String.format(".//*[@id='%s_sell']",currencyName);
        TestData.SALE_VALUE_UI = webDriver.findElement(By.xpath(currencyLocator)).getText();
        logger.info("Currency displayed on UI " + TestData.SALE_VALUE_UI + " SALE");

    }

//    @Step
//    public void checkCurrencyRatesApiAndUiAreEqual(){
//        logger.info("Buy_API = "  + TestData.BUY_VALUE_API);
//        logger.info("Buy_API = "  + TestData.SALE_VALUE_API);
//        logger.info("Buy_UI = "  + TestData.BUY_VALUE_UI);
//        logger.info("Buy_UI = "  + TestData.SALE_VALUE_UI);
//        Assert.assertEquals(String.valueOf(TestData.BUY_VALUE_API),String.valueOf(TestData.BUY_VALUE_UI));
//        Assert.assertEquals(String.valueOf(TestData.SALE_VALUE_API),String.valueOf(TestData.SALE_VALUE_UI));
//    }
}
