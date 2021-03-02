package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;
//@Name(value = "")
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private TextInput inputRegUsername;

    @FindBy(xpath = ".//input[@id='email-register']")
    private TextInput inputEmail;

    @FindBy(xpath = ".//input[@id='password-register']")
    private TextInput inputRegPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUpForOurApp;




    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + getRelativeUrl());
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open Login page");
        }

    }

    public void enterLoginSignIn(String login) {
     /*   try {
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + "was inputted into Login Input");
        } catch (Exception e) {
            logger.error("Can not work with login input");
            Assert.fail("Can not work with login input");

        }

      */
        enterTextInToElement(inputLogin, login);
    }

    public void enterPassWordSignIn(String passWord) {
      /*  try {
            inputPassword.clear();
            inputPassword.sendKeys(passWord);
            logger.info(passWord + "was inputted into PassWord Input");
        } catch (Exception e) {
            logger.error("Can not work with Password input");
            Assert.fail("Can not work with Password input");
        }

       */
        enterTextInToElement(inputPassword, passWord);
    }

    public void clickButtonSignIn() {
      /*  try {
            buttonSignIn.click();
            logger.info("Button Sign In was clicked");
        } catch (Exception e) {
            logger.error("Can not work with Button SignIn");
            Assert.fail("Can not work with Button SignIn");
        }

       */
       clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(pass);
        clickButtonSignIn();
    }
    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

//HW-4

    public void clickButtonSignUpForOurApp(){
        clickOnElement(buttonSignUpForOurApp);
    }



    public void fillRegisterForm(String login, String email, String pass){
        enterTextInToElement(inputRegUsername, login);
        enterTextInToElement(inputEmail, email);
        enterTextInToElement(inputRegPassword, pass);
    }


    public boolean isErrorMessageForInvalidRegUsernameDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='Username must be at least 3 characters.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


    public boolean isErrorMessageForInvalidEmailDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='You must provide a valid email address.']")).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }


    public boolean isErrorMessageForInvalidRegPasswordDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='Password must be at least 12 characters.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


}
