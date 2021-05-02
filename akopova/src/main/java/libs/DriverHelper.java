package libs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class DriverHelper {

    private static WebDriver webDriver;



    public void createDriver() throws Exception {

        webDriver = initDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    private WebDriver initDriver() throws Exception {
        String browser = System.getProperty("browser");
        if ("chrome".equalsIgnoreCase(browser)){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ((browser == null) ||"firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browser)){
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        else if ("remote".equals(browser)){
            DesiredCapabilities cap=new DesiredCapabilities();
            cap.setBrowserName("firefox");
            /**
            cap.setPlatform(Platform.WINDOWS);
            cap.setVersion("79");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(cap);
             */
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(cap);
            webDriver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    firefoxOptions);
            return webDriver;
        }

        else {
            throw new Exception("Check browser var");
        }
    }

    public void closeDriver(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
