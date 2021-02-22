package baseTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.SinglePostPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected SinglePostPage singlePostPage;
    protected MyProfilePage myProfilePage;
    @Before
    public void setUp(){
        File fileFF = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        myProfilePage = new MyProfilePage(webDriver);
        singlePostPage = new SinglePostPage(webDriver);
    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }
    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertTrue(message,actualResult);
    }

}
