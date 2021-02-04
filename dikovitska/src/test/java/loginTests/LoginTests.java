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
    public void validLogin(){ /*название метода = название тест кейса*/
//drivers

        File fileFF = new File("./drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());

         webDriver = new ChromeDriver(); /* объявляем переменную, в которую будем записывать драйвер (браузер)*/

        webDriver.manage().window().maximize(); /*открыть браузер полностью (развернуть)*/
        webDriver. manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); /*установить дефолтное время, на протяжении которого будет браузер выполнять действие*/

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//*[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//*[@placeholder='Username']")).sendKeys("auto");
        System.out.println("'auto' was inputted into LoginInput");

        webDriver.findElement(By.xpath(".//*[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//*[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("'123456qwerty' was inputted into InputPass");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button SignIn was clicked");

        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutVisible()); /*класс, который проверяет условия: если то, что мы ему даем, подходит, - тест проходит, если не подходит, - то тест не проходит*/



        webDriver.quit(); /*свернуть браузер полностью. Есть еще метод close- он просто сворачивает вкладку браузера*/


    }

    private boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


}
