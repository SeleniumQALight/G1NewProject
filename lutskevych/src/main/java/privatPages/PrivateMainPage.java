package privatPages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrivateMainPage extends PrivateParentPage{

    public PrivateMainPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    public void openMainPrivatePage(){
        try {
            webDriver.get(baseUrlPrivate);
            logger.info("Main Page was opened");
        }catch(Exception e){
            logger.error("Can not open Main page");
            Assert.fail("Can not open Main page");
        }
    }
    public float getCurrencySellRate (String currency) {
        String id = currency + "_sell";
        return Float.parseFloat(getCurrencyRate(id));
    }

    public float getCurrencyBuyRate (String currency) {
        String id = currency + "_buy";
        return Float.parseFloat(getCurrencyRate(id));
    }

    private String getCurrencyRate(String elementId) {
        return webDriverWait10.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)))
                .getText();
    }

}
