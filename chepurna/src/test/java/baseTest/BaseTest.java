package baseTest;

import Pages.HomePage;
import Pages.LoginPage;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;


    @Before
    public void setUp(){
        File fileFF = new File("./drivers/chrome-driver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();  //full size for window
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //default time for waiting
        logger.info("Browser was opend");
        loginPage = new LoginPage(webDriver); // from this go to constructor
        homePage = new HomePage(webDriver);
    }
    @After
    public void teamDown(){
       webDriver.quit();
       logger.info("Browser was closed");

    }

    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertTrue(message, actualResult);
    }
}
