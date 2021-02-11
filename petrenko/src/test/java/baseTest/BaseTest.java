package baseTest;

import org.apache.log4j.Logger;
import org.junit.After;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreatePostPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CreatePostPage createPostPage;

    @Before
    public void setup() {
        File fileFF = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Browser was open.");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
      //  createPostPage = new

    }


    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");

    }


    protected void checkExpectedResult(String massage, boolean aktualResult){
        Assert.assertTrue(massage, aktualResult);
    }

}
