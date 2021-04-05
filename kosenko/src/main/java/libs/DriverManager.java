package libs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver webDriver;

    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    public void closeDriver(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
