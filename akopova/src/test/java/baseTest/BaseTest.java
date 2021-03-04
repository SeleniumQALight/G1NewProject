package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import posts.CreateNewPostTest;

import java.io.File;
import java.util.concurrent.TimeUnit;
import static pages.ParentPage.configProperties;

public class BaseTest {

    // Here will be all PRE & POST-conditions for our tests

    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected SinglePostPage singlePostPage;
    protected MyProfilePage myProfilePage;
    protected CreateNewPostTest createNewPostTest;



        @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws Exception {
        logger.info("----" + testName.getMethodName() + " was started -----");

        // later on can be deleted
        /**
        File fileFF = new File("./drivers/chromedriver.exe");

        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
         */

        //webDriver = new ChromeDriver();
        webDriver = initDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DFFAULT_WAIT(), TimeUnit.SECONDS);
        logger.info("Browser opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        singlePostPage = new SinglePostPage(webDriver);
        myProfilePage = new MyProfilePage(webDriver);



    }

    private WebDriver initDriver() throws Exception {
        // Switch between varios web drivers
        String browser = System.getProperty("browser");

        if ((browser == null) || ("chrome".equalsIgnoreCase(browser))) {
            // Create new instance of browser
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();

            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        else {
            throw new Exception("check browser variable");
        }

    }

    @After
    public void testDown() {

        webDriver.quit();
        logger.info("Browser closed");
        logger.info("----" + testName.getMethodName() + " was ended -----");
    }



    protected void checkExpectedResult(String message, boolean actualResult) {
        Assert.assertTrue(message, actualResult);

    }
}
