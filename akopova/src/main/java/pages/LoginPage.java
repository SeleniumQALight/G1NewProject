package pages;

import libs.TestData;
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
            logger.info("Login Page opened");
        }
        catch (Exception e) {
            logger.info("can't open Login Page");
            Assert.fail("can't open Login Page");
        }

    }

    public void enterLoginSignIn(String login) {
      /**
        try {

            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + "was inputted into Login Input");
        }
        catch (Exception e){
            logger.error("Can't work with Login Input");
            Assert.fail("Can't work with Login Input");
        }
       */
      enterTextIntoElement(inputLogin, login);
    }

    public void enterPasswordSignIn(String password) {
        enterTextIntoElement(inputPassword, password);
    }

    public void clickButtonSignIn() {
        try{

            clickOnElement(buttonSignIn);
            logger.info("SignIn Button clicked");
        }
        catch (Exception e) {
            logger.error("Can't work with SignIn Button");
            Assert.fail("Can't work with SignIn Button");

        }
    }

    public void fillLoginFormAndSubmit(String login, String pass) {
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();

    }

    public HomePage loginWithValidCreds() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        //fillLoginFormAndSubmit("auto", "123456qwerty");
        return new HomePage(webDriver);
    }

}
