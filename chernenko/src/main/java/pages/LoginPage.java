package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy (xpath =".//*[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy( xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy (xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy (xpath = ".//*[@id = 'username-register']")
    private WebElement pickAUsername;

    @FindBy (xpath = ".//*[@id = 'email-register']")
    private WebElement emailForRegistration;

    @FindBy (xpath = ".//*[@id ='password-register']")
    private WebElement passwordForRegistration;

    @FindBy (xpath = ".//button[contains(text(),'Sign up for OurApp')]")
    private WebElement buttonSignUp;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
       try {
           webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
           logger.info("Login Page was opened");
       } catch (Exception e){
           logger.error("Can not open Login page");
           Assert.fail("Can not open Login page");
       }
    }

    public void enterLoginSignIn(String login) {
        enterTextIntoElement(inputLogin, login);
    }

    public void enterUsernameIntoLogin(String newUsername){
        enterTextIntoElement(pickAUsername, newUsername);
    }

    public void enterPasswordSignIn(String passWord) {
        enterTextIntoElement(inputPassword, passWord);
    }

    public void enterEmailIntoLogin (String userEmail){
        enterTextIntoElement(emailForRegistration, userEmail);
    }

    public void enterPasswordIntoLogin (String newPassword){
        enterTextIntoElement(passwordForRegistration, newPassword);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);

    }

    public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
    }

    public HomePage loginWithValidCred (){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public void fillLoginFormNewUserAndSubmit (String newUsername, String userEmail, String newPassword){
        openLoginPage();
        enterUsernameIntoLogin(newUsername);
        enterEmailIntoLogin(userEmail);
        enterPasswordIntoLogin(newPassword);
        clickButtonSignUp();
    }

    public void clickButtonSignUp() {
        clickOnElement(buttonSignUp);
    }


}
