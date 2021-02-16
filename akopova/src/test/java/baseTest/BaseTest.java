package baseTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    // Here will be all PRE & POST-conditions for our tests

    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected SinglePostPage singlePostPage;
    protected MyProfilePage myProfilePage;


    @Before
    public void setUp() {
        File fileFF = new File("./drivers/chromedriver.exe");

        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Browser opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        singlePostPage = new SinglePostPage(webDriver);
        myProfilePage = new MyProfilePage(webDriver);



    }

    @After
    public void testDown() {

        webDriver.quit();
        logger.info("Browser closed");
    }

    protected void checkExpectedResult(String message, boolean actualResult) {
        Assert.assertTrue(message, actualResult);

    }
}
