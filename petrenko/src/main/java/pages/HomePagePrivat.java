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

    String buy = "%s_buy";
    String sell = "%s_sell";

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

            TestData.RATE_CURRENCY_BY_UI = webDriver.findElement(By.id(String.format(buy, currency))).getText();
            logger.info( TestData.RATE_CURRENCY_BY_UI);
            TestData.RATE_CURRENCY_SALE_UI = webDriver.findElement(By.id(String.format(sell, currency))).getText();
            logger.info(TestData.RATE_CURRENCY_SALE_UI);
        }
        catch (Exception e){
            logger.info(e);
        }

    }
}
