package baseTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    @Before
    public void Setup() {
        File fileFF = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());

        webDriver = new ChromeDriver(); /* объявляем переменную, в которую будем записывать драйвер (браузер)*/

        webDriver.manage().window().maximize(); /*открыть браузер полностью (развернуть)*/
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); /*установить дефолтное время, на протяжении которого будет браузер выполнять действие*/
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage= new HomePage(webDriver);

    }
    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");

    }
    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertTrue(message, actualResult);
    }
}
