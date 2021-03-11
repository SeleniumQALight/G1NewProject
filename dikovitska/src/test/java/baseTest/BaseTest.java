package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static pages.ParentPage.configProperties;

public class BaseTest {
    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws Exception{
        logger.info("-------" + testName + " was ended ------");
        File fileFF = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = initDriver(); /* объявляем переменную, в которую будем записывать драйвер (браузер)*/

        webDriver.manage().window().maximize(); /*открыть браузер полностью (развернуть)*/
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); /*установить дефолтное время, на протяжении которого будет браузер выполнять действие*/
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage= new HomePage(webDriver);

    }

    private WebDriver initDriver() throws Exception {
        String browser = System.getProperty("browser");
        if ((browser == null ) || ("chrome".equalsIgnoreCase(browser))){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            throw new Exception("Check browser var");
        }
    }

    @After
    public void tearDown() {
        // webDriver.quit();
        // logger.info("Browser was closed");
        logger.info("-------" + testName + " was ended ------");
    }

        @Rule
        public TestWatcher watchman = new TestWatcher() {
            @Override
            protected void failed(Throwable e, Description description) {
                screenshot();
            }
            @Attachment(value = "Page screenshot", type = "image/png")
            public byte[] saveScreenshot(byte[] screenShot) {
                return screenShot;
            }
            public void screenshot() {
                if (webDriver == null) {
                    logger.info("Driver for screenshot not found");
                    return;
                }
                saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
            }
            @Override
            protected void finished(Description description) {
                logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
                try {
                    webDriver.quit();
                    logger.info("Browser was closed");
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        };


    @Step
    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertTrue(message, actualResult);
    }
}
