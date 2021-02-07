package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Site was opened");
        }catch (Exception e){
            logger.error("Can't open Login page");
            Assert.fail("Can't open Login page");
        }
    }

    public void enterLoginSignIn(String login) {
        try{
            WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + " was inputted into Login Input");
        }catch (Exception e){
            logger.error("Can't work with Login Input");
            Assert.fail("Can't work with Login Input");
        }
    }
    public void enterPassWordSignIn(String passWord) {
        try{
            WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
            inputLogin.clear();
            inputLogin.sendKeys(passWord);
            logger.info(passWord + " was inputted into PassWord Input");
        }catch (Exception e){
            logger.error("Can't work with PassWord Input");
            Assert.fail("Can't work with PassWord Input");
        }
    }

    public void clickButtonSignIn() {
        try{
            WebElement button = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            button.click();
logger.info("Button Sign In was clicked");

        }catch (Exception e) {
            logger.error("Can't work with Button SignIn");
            Assert.fail("Can't work with Button SignIn");
        }
    }
}
