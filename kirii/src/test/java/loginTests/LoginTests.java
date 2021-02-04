package loginTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginTests {
    WebDriver webDriver;

    @Test
    public void validLogin(){
        File fileFF = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();                           //во весь экран
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  //дефолтное неявное ожидание

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//*[@placeholder='Username']")).clear();     //нашли поле, чистим поле
        webDriver.findElement(By.xpath(".//*[@placeholder='Username']")).sendKeys("auto");  //жмем кнопки
        System.out.println("'auto' was inputted into LoginInput");

        webDriver.findElement(By.xpath(".//*[@placeholder='Password']")).clear();     //нашли поле, чистим поле
        webDriver.findElement(By.xpath(".//*[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("'123456qwerty' was inputted into InputPass");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button Signin was clicked");

        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutVisible()); //проверка асерт что кнопка СайнАут появилась

        webDriver.quit();  //полностью сварачивает весь браузер (close -только вкладку)
    }

    private boolean isButtonSignOutVisible() {
        try {

            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isEnabled();
        }catch (Exception e){
            return false;
        }
    }
}
