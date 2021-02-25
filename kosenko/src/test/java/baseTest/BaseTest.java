package baseTest;


import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.SinglePostPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static pages.ParentPage.configProperties;

public class BaseTest {
        public WebDriver webDriver;
        protected Logger logger = Logger.getLogger(getClass());
        protected LoginPage loginPage;
        protected HomePage homePage;
        protected SinglePostPage singlePostPage;


        @Before
        public void setUp(){
            File fileFF = new File("./drivers/chrome-driver");
            System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
            webDriver = new ChromeDriver();

            webDriver.manage().window().maximize();  //full size for window
            webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DFFAULT_WAIT(), TimeUnit.SECONDS); //default time for waiting
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
