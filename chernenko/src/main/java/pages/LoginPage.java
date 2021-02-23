package pages;

import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

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

    @FindBy (xpath = "")
    private WebElement WrongPassword;

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
//        try{
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted into Login Input");
//        }catch (Exception e){
//            logger.error("Can not work with Login input");
//            Assert.fail("Can not work with Login input");
//        }
        enterTextIntoElement(inputLogin, login);
    }

    public void enterUsernameIntoLogin(String newUsername){
        enterTextIntoElement(pickAUsername, newUsername);
    }

    public void enterPasswordSignIn(String passWord) {
//        try{
////            inputPassword.clear();
////            inputPassword.sendKeys(passWord);
////            logger.info(passWord + " was inputted into Password Input");
////        }catch (Exception e){
////            logger.error("Can not work with PassWord input");
////            Assert.fail("Can not work with PassWord input");
////        }
        enterTextIntoElement(inputPassword, passWord);
    }

    public void enterEmailIntoLogin (String userEmail){
        enterTextIntoElement(emailForRegistration, userEmail);
    }

    public void enterPasswordIntoLogin (String newPassword){
        enterTextIntoElement(passwordForRegistration, newPassword);
    }

    public void clickButtonSignIn() {
//        try{
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            logger.error("Can not work with Button input");
//            Assert.fail("Can not work with Button input");
//        }
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

    public boolean isErrorMessageWrongPassword() {

        return isElementDisplayed(WrongPassword);
    }

    public ArrayList numberOfErrorMessageWereDisplayed (){
        // Use for the test date from TestData file
        //fillLoginFormNewUserAndSubmit(TestData.UNIQUE_USERNAME,TestData.INVALID_EMAIL,TestData.SORT_PASSWORD);
        Util.waitABit(2);
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".// div[contains(@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible')]"));
        return (ArrayList) list;

    }
}
