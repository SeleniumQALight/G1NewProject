package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {


            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterLoginSignIn(String login) {
        try {
            WebElement inputLogin = webDriver.findElement(By.xpath("//*[@placeholder='Username']"));
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + "was inputted into Login Input");

        } catch (Exception e) {
            logger.error("Can not work with Login input");
            Assert.fail("Can not work with Login input");
        }
    }

    public void enterPasswordSignIn(String password) {
        try {
            WebElement inputLogin = webDriver.findElement(By.xpath("//*[@placeholder='Password']"));
            inputLogin.clear();
            inputLogin.sendKeys(password);
            logger.info(password + " was inputted into Login Input");

        } catch (Exception e) {
            logger.error("Can not work with Password input");
            Assert.fail("Can not work with Password input");
        }
    }

    public void clickButtonSignIn() {
        try {
            WebElement button = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            button.click();
            logger.info("Button SignIn was clicked");

        }catch (Exception e){
            logger.error("Can not work with Button SignIn");
            Assert.fail("Can not work with Button SignIn");

        }

    }
}
