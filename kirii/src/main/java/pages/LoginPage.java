package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;


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
//        try {
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted into Login Input");
//        }catch (Exception e){
//            logger.error("Can not work with Login Input");
//            Assert.fail("Can not work with Login Input");
//        }
        enterTextIntoElement(inputLogin, login);
    }
    public void enterPasswordSignIn(String password) {
//        try {
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info(password + " was inputted into Password Input");
//        }catch (Exception e){
//            logger.error("Can not work with Password Input");
//            Assert.fail("Can not work with Password Input");
//        }
        enterTextIntoElement(inputPassword, password);
    }

    public void clickButtonSignIn() {
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            logger.error("Can not work with Button SignIn");
//            Assert.fail("Can not work with Button SignIn");
//        }
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
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
