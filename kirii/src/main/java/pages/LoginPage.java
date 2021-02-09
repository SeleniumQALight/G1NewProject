package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        }catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }

    public void enterLoginSignIn(String login) {
        try {
            WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + "was inputted into Login Input");
        }catch (Exception e){
            logger.error("Can not work with Login Input");
            Assert.fail("Can not work with Login Input");
        }
    }
    public void enterPasswordSignIn(String password) {
        try {
            WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
            inputLogin.clear();
            inputLogin.sendKeys(password);
            logger.info(password + "was inputted into Password Input");
        }catch (Exception e){
            logger.error("Can not work with Password Input");
            Assert.fail("Can not work with Password Input");
        }
    }

    public void clickButtonSignIn() {
        try {
            WebElement button = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            button.click();
            logger.info("Button Sign In was clicked");
        }catch (Exception e){
            logger.error("Can not work with Button SignIn");
            Assert.fail("Can not work with Button SignIn");
        }
    }

    public boolean isButtonSignInVisible() {
        try {
            logger.info("Button Sign In visible");
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isMessageErrorVisible() {
        try {
            logger.info("Message error visible");
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Error')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isButtonSignOutNotVisible() {
        try {
            logger.info("Button Sign Out not visible");
            return !webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return true;
        }
    }
}
