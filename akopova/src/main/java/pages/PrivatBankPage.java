package pages;

import api.CurrencyDTO;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

public class PrivatBankPage extends ParentPage {
    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    // Currencies section
    String currencyRateBuy = ".//*[@id = '%s_buy']";
    String currencyRateSale = ".//*[@id = '%s_sell']";
    String currencyRU = "RUB";


    @Step
    public void openPrivatPage() {
        try {

            webDriver.get(basePrivateUrl);
            logger.info("PrivatBank Page opened");
        } catch (Exception e) {
            logger.info("can't open PrivatBank Page");
            fail("can't open PrivatBank Page");
        }
    }


    @Step
    public void getCurrencyRatesViaUI(String currency) {
        logger.info("currency is " + currency);

        if (currency.equals("RUR")) {
            currency = currencyRU;
        }

        try {
            TestData.UI_RATE_BUY = webDriver.findElement(By.xpath(String.format(currencyRateBuy, currency))).getText();
            TestData.UI_RATE_SELL = webDriver.findElement(By.xpath(String.format(currencyRateSale, currency))).getText();
        }
        catch (Exception e) {
            logger.error("Exception occurred, PrivatBank page" + e);
        }

    }

 }