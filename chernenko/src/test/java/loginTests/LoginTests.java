package loginTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginTests {
    WebDriver webDriver ;
    @Test
    public void validLogIn(){
//drivers
        File fileFF = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();


        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//*[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//*[@placeholder='Username']")).sendKeys("auto");
        System.out.println("'auto' was added to field Username");

        webDriver.findElement(By.xpath(".//*[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//*[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("'123456qwerty' was added to field Password");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button Sign in was clicked");

        Assert.assertTrue("Button SignOut is noy displayed",isButtonSignOutVisible());




        webDriver.quit();

    }

    private boolean isButtonSignOutVisible() {
        try{
        return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
    }catch (Exception e){
            return false;
        }
    }
}
