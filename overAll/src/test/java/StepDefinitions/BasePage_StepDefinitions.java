package StepDefinitions;

import static libs.DriverManager.getWebDriver;

import java.util.ArrayList;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import libs.DriverManager;


public class BasePage_StepDefinitions {
    @And("^Set Cookie to browser$")
    public void setCookieToBrowser(Cookie cookie) {
        getWebDriver().manage().deleteCookieNamed("connect.sid");
        getWebDriver().manage().addCookie(cookie);
    }

    @And("^User refreshes page$")
    public void userRefreshesPage() {
        getWebDriver().navigate().refresh();
    }

    @And("^Users presses key 'Tab' (\\d+) time$")
    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(getWebDriver());
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();

        }

    }

    @And("^Users presses key 'Enter' (\\d+) time$")
    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(getWebDriver());
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();

        }
    }

    @When("^User opens new Tab and switch to it$")
    public void userOpensNewTab() {
        ((JavascriptExecutor)getWebDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs.get(1));
    }

}
