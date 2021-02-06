package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    //open Login page method
    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        }catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }

    public void enterLoginSignIn(String login) {
        try{
            WebElement inputLogin = webDriver.findElement(By.xpath(".//form[@action='/login']//input[@name='username']"));
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + "was inputted into Login Input");
        }catch (Exception e){
            logger.error("Cannot work with login input");
            Assert.fail("Cannot work with login input");
        }
    }

    public void enterPassSignIn(String passWord) {
        try{
            WebElement inputLogin = webDriver.findElement(By.xpath(".//form[@action='/login']//input[@name='password']"));
            inputLogin.clear();
            inputLogin.sendKeys(passWord);
            logger.info(passWord + "was inputted into passWord Input");
        }catch (Exception e){
            logger.error("Cannot work with passWord input");
            Assert.fail("Cannot work with passWord input");
        }
    }

    public void clickButtonSignIn() {
        try {
            WebElement button = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            button.click();
            logger.info("Button Sign In was clicked");

        } catch (Exception e){
            logger.error("Cannot work with Button input");
            Assert.fail("Cannot work with Button input");
        }
    }
}
