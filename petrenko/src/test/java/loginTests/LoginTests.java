//package loginTests;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//public class LoginTests {
//    WebDriver webDriver;
//
//    @Test
//
//    public void validLogin(){
//// drivers
//
//        File fileFF = new File("./drivers/chromedriver.exe");
//
//        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
//        webDriver = new ChromeDriver();
//
//
//        webDriver.manage().window().fullscreen();
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
//        System.out.println("Site was opened");
//        webDriver.findElement(By.xpath(".//form[@action = '/login']//input[@name = 'username']")).clear();
//        webDriver.findElement(By.xpath(".//form[@action = '/login']//input[@name = 'username']")).sendKeys("auto");
//        System.out.println("'auto' was inputted into LoginInput");
//        webDriver.findElement(By.xpath(".//form[@action = '/login']//input[@name = 'password']")).clear();
//        webDriver.findElement(By.xpath(".//form[@action = '/login']//input[@name = 'password']")).sendKeys("123456qwerty");
//        System.out.println("'123456qwerty' was inputted into InputPass");
//
//
//        webDriver.findElement(By. xpath(".//button[@class='btn btn-primary btn-sm']")).click();
//        System.out.println("Sign in' was clicked");
//
//        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutIsVisible());
//
//
//        webDriver.quit();
//    }
//
//    private boolean isButtonSignOutIsVisible() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text() = 'Sign Out']")).isDisplayed();
//        }catch (Exception e){
//            return  false;
//        }
//
//    }
//}
