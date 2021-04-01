package libs;

import static java.lang.System.getProperty;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private  static WebDriver webDriver ;
    public static String urlPrefix = "";
    public static String dbPrefix = "";
    public void createDriver () {

        if(getProperty("env")!=null){
            urlPrefix = getProperty("env")+"-";
            dbPrefix = "-" + getProperty("env");
        System.out.println("Env = " + urlPrefix); }

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
