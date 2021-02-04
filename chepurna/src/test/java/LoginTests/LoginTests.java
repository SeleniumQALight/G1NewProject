package LoginTests;

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
        File fileFF = new File("./drivers/chrome-driver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();  //full size for window
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //default time for waiting

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//form[@action='/login']//input[@name='username']")).clear();
        webDriver.findElement(By.xpath(".//form[@action='/login']//input[@name='username']")).sendKeys("auto");
        System.out.println("'auto' was inputted");

        webDriver.findElement(By.xpath(".//form[@action='/login']//input[@name='password']")).clear();
        webDriver.findElement(By.xpath(".//form[@action='/login']//input[@name='password']")).sendKeys("123456qwerty");
        System.out.println("'123456qwerty' was inputted");

        webDriver.findElement(By.xpath(".//form[@action='/login']//button")).click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutVisible());

        webDriver.quit();

    }

    private boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath(".//header//button")).isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }
}
