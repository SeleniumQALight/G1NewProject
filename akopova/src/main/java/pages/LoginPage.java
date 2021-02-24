package pages;

import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static org.junit.Assert.*;

public class LoginPage extends ParentPage{

    private final String WRONG_SIGNUP_LOGIN = "@login";

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;

    // @Name(value="inputPass") - an example
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;
    @FindBy(xpath=".//*[@id = 'username-register']")
    public WebElement signUpLogin;
    @FindBy(xpath=".//*[@id = 'email-register']")
    public WebElement signUpEmail;
    @FindBy(xpath=".//*[@id = 'password-register']")
    public WebElement signUpPassword;
    @FindBy(xpath = ".//*[@type = 'submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//*[@id='username-register']/following-sibling::*")
    //@FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement loginSignUpErrorField;

    @FindBy(xpath = ".//*[@id='email-register']/following-sibling::*")
    private WebElement emailSignUpErrorField;

    @FindBy(xpath = ".//*[@id='password-register']/following-sibling::*")
    private WebElement passwordSignUpErrorField;

    public static String signUpErrorMessageLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

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
            logger.info("Login Page opened");
        }
        catch (Exception e) {
            logger.info("can't open Login Page");
            fail("can't open Login Page");
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

    public LoginPage enterLoginSignUp(String login){
        try {
            enterTextIntoElement(signUpLogin, login);
        }
        catch (Exception e){
            logger.error(e + "login signup exception");
        }

        return this;
    }




    public LoginPage enterEmailSignUp(String eMail){
        try {
            enterTextIntoElement(signUpEmail, eMail);
        }
        catch (Exception e){
            logger.error(e + "Email signup exception");
        }

        return this;
    }

    public LoginPage enterPasswordSignUp(String password){
        try {
            enterTextIntoElement(signUpPassword, password);
        }
        catch (Exception e){
            logger.error(e + "Email signup exception");
        }

        return this;
    }

    public LoginPage enterDataForSignUp(WebElement element, String text){
        try {
            enterTextIntoElement(element, text);
        }
        catch (Exception e){
            logger.error(e + "Signup exception");
        }

        return this;
    }





    public void clickButtonSignIn() {
        try{

            clickOnElement(buttonSignIn);
            logger.info("SignIn Button clicked");
        }
        catch (Exception e) {
            logger.error("Can't work with SignIn Button");
            fail("Can't work with SignIn Button");

        }
    }

    public void clickButtonSignUp() {
        try{

            clickOnElement(buttonSignUp);
            logger.info("SignIn Button clicked");
        }
        catch (Exception e) {
            logger.error("Can't work with SignUp Button");
            fail("Can't work with SignUp Button");

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

    public void enterValidSignUpInfo() {
        enterDataForSignUp(signUpLogin, TestData.VALID_SIGNUP_LOGIN);
        enterDataForSignUp(signUpEmail, TestData.VALID_SIGNUP_EMAIL);
        enterDataForSignUp(signUpPassword, TestData.VALID_SIGNUP_PASSWORD);
    }

    //TODO check that we registered the correct Isr; maybe redirect to HomePage

    public LoginPage enterIvValidSignUpInfo(String signUpLoginValue, String signUpEmailValue, String signUpPasswordValue) {
        enterDataForSignUp(signUpLogin, signUpLoginValue);
        enterDataForSignUp(signUpEmail, signUpEmailValue);
        enterDataForSignUp(signUpPassword, signUpPasswordValue);

        return this;
    }




/**
    public void checkErrorMessage(WebElement webElement, String text, String locator){
        //enterDataForSignUp(webElement, text);
        //checkIsElementVisible(webDriver.findElement(By.xpath(locator)));
        checkIsElementVisible(webElement);
    }
 */
    public LoginPage checkLoginErrorMessageIsDisplayed(){
        checkIsElementVisible(loginSignUpErrorField);
        return this;

}

    public LoginPage checkEmailErrorMessageIsDisplayed(){
        checkIsElementVisible(emailSignUpErrorField);
        return this;

    }

    public LoginPage checkPasswordErrorMessageIsDisplayed(){
        checkIsElementVisible(passwordSignUpErrorField);
        return this;

    }


    public  LoginPage checkLoginSignUpErrorMessageText(String text) {
        assertEquals("Message does not match", text,
                loginSignUpErrorField.getText());
        return this;
    }

    public  LoginPage checkEmailSignUpErrorMessageText(String text) {
        assertEquals("Message does not match", text,
                emailSignUpErrorField.getText());
        return this;
    }

    public  LoginPage checkPasswordSignUpErrorMessageText(String text) {
        assertEquals("Message does not match", text,
                passwordSignUpErrorField.getText());
        return this;
    }

    public void checkErrorMessagesAmount(String errorMessageLocator,long expectedErrorMessagesNumber ){
        List<WebElement> listOfErrorMessages;

       // errorMessageLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
      try {
          listOfErrorMessages = webDriver.findElements(By.xpath(errorMessageLocator));
          logger.info("Now there are " + listOfErrorMessages.size() + " error messages on login page");
          //compare number of expected and actual error messages numbers
          Assert.assertEquals("Expected and actual number of error messages do not match"
                  ,expectedErrorMessagesNumber
                  ,listOfErrorMessages.size() );

      }
      catch (Exception e){
          logger.error("Exception " + e + "happened");
      }

    }




}
