package pages;

import io.qameta.allure.Step;
import libs.MyUtil;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class LoginPage extends ParentPage {

        private final String WRONG_SIGNUP_LOGIN = "@login";

        @FindBy(xpath = ".//*[@placeholder='Username']")
        private TextInput inputLogin;

        // @Name(value="inputPass") - an example
        @FindBy(xpath = ".//*[@placeholder='Password']")
        private TextInput inputPassword;
        @FindBy(xpath = ".//button[text()='Sign In']")
        private Button buttonSignIn;
        @FindBy(xpath = ".//*[@id = 'username-register']")
        public TextInput signUpLogin;
        @FindBy(xpath = ".//*[@id = 'email-register']")
        public TextInput signUpEmail;
        @FindBy(xpath = ".//*[@id = 'password-register']")
        public TextInput signUpPassword;
        //@FindBy (xpath=".//*[@class='py-3 mt-4 btn btn-lg btn-success btn-block']")
        @FindBy(xpath = ".//*[@type = 'submit']")
        private Button buttonSignUp;

        @FindBy(xpath = ".//*[@id='username-register']/following-sibling::*")
        //@FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
        private TextInput loginSignUpErrorField;

        @FindBy(xpath = ".//*[@id='email-register']/following-sibling::*")
        private TextInput emailSignUpErrorField;

        @FindBy(xpath = ".//*[@id='password-register']/following-sibling::*")
        private TextInput passwordSignUpErrorField;

        public static String signUpErrorMessageLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

        public static String loginErrorMessageLocator = ".//*[@id='username-register']/following-sibling::*";
        public static String emailErrorMessageLocator = ".//*[@id='email-register']/following-sibling::*";
        public static String passwordErrorMessageLocator = ".//*[@id='password-register']/following-sibling::*";

        public LoginPage(WebDriver webDriver) {
            super(webDriver);
        }

        @Override
        String getRelativeUrl() {
            return "/";
        }

        public void openLoginPage() {
            try {
                webDriver.get("https://qa-complex-app-for-testing.herokuapp.com" + getRelativeUrl());
                logger.info("Login Page opened");
            } catch (Exception e) {
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
        @Step
        public void enterPasswordSignIn(String password) {

            enterTextIntoElement(inputPassword, password);
        }
        @Step
        public LoginPage enterLoginSignUp(String login) {
            enterTextIntoElement(signUpLogin, login);
            return this;
        }

        @Step
        public LoginPage enterEmailSignUp(String eMail) {
            enterTextIntoElement(signUpEmail, eMail);
            return this;
        }

        @Step
        public LoginPage enterPasswordSignUp(String password) {
            enterTextIntoElement(signUpPassword, password);
            return this;
        }


        @Step
        public void clickButtonSignIn() {
            clickOnElement(buttonSignIn);
            logger.info("SignIn Button clicked");
        }

        @Step
        public void clickButtonSignUp() {
            clickOnElement(buttonSignUp);
            logger.info("Sign-Up Button clicked");
        }

        @Step
        public void fillLoginFormAndSubmit(String login, String pass) {
            openLoginPage();
            enterLoginSignIn(login);
            enterPasswordSignIn(pass);
            clickButtonSignIn();

        }

        @Step
        public HomePage loginWithValidCreds() {
            fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
            //fillLoginFormAndSubmit("auto", "123456qwerty");
            return new HomePage(webDriver);
        }

        public void enterValidSignUpInfo() {
            openLoginPage();
            enterLoginSignUp(TestData.VALID_SIGNUP_LOGIN + MyUtil.getDateAndTimeFormated());
            enterEmailSignUp(TestData.VALID_SIGNUP_EMAIL);
            enterPasswordSignUp(TestData.VALID_SIGNUP_PASSWORD);

        }

        public HomePage registerNewValidUser() {
            enterValidSignUpInfo();
            MyUtil.waitABit(12);

            Assert.assertTrue("SignUp button is not clickable"
                    , isElementEnabled(buttonSignUp));
            clickButtonSignUp();
            // Check that we registered correct User : Homepage should open
            MyUtil.waitABit(5);
            waitForHomePageToLoad();
            return new HomePage(webDriver);
        }


        public LoginPage enterSignUpInfo(String signUpLoginValue
                , String signUpEmailValue
                , String signUpPasswordValue) {
            enterLoginSignUp(signUpLoginValue);
            try {
            waitForErrorMessageToBecomeVisible(loginErrorMessageLocator);
        }
            catch (Exception e){
            logger.error("No such an object as Login Error message");
        }
        enterEmailSignUp(signUpEmailValue);
        try {
            waitForErrorMessageToBecomeVisible(emailErrorMessageLocator);
        }
        catch (Exception e){
            logger.error("No such an object as E-mail Error message");
        }

            enterPasswordSignUp(signUpPasswordValue);
            try {
            waitForErrorMessageToBecomeVisible(passwordErrorMessageLocator);
        }
        catch (Exception e){
            logger.error("No such an object as Password Error message");
        }

            return this;
        }


        public LoginPage checkLoginErrorMessageIsDisplayed() {
            checkIsElementVisible(loginSignUpErrorField);
            return this;
        }

        public LoginPage checkEmailErrorMessageIsDisplayed() {
            checkIsElementVisible(emailSignUpErrorField);
            return this;
        }

        public LoginPage checkPasswordErrorMessageIsDisplayed() {
            checkIsElementVisible(passwordSignUpErrorField);
            return this;
        }


        public LoginPage checkLoginSignUpErrorMessageText(String text) {
            assertEquals("Message does not match", text,
                    loginSignUpErrorField.getText());
            return this;
        }

        public LoginPage checkEmailSignUpErrorMessageText(String text) {
            assertEquals("Message does not match", text,
                    emailSignUpErrorField.getText());
            return this;
        }

        public LoginPage checkPasswordSignUpErrorMessageText(String text) {
            assertEquals("Message does not match", text,
                    passwordSignUpErrorField.getText());
            return this;
        }

        public void checkErrorMessagesAmount(String errorMessageLocator, long expectedErrorMessagesNumber) {
            List<WebElement> listOfErrorMessages;

            try {
                listOfErrorMessages = webDriver.findElements(By.xpath(errorMessageLocator));
                logger.info("Now there are " + listOfErrorMessages.size() + " error messages on login page");
                //compare number of expected and actual error messages numbers
                Assert.assertEquals("Expected and actual number of error messages do not match"
                        , expectedErrorMessagesNumber
                        , listOfErrorMessages.size());
            } catch (Exception e) {
                logger.error("Exception " + e + "happened");
            }

        }

        protected LoginPage waitForErrorMessageToBecomeVisible(String errorMessageLocator) {
            webDriverWait10.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath(errorMessageLocator)));
            return this;
        }

        public HomePage waitForHomePageToLoad() {
            getWebDriverWait20.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath(".//*[@class='header-bar mb-3']")));
            waitChatToBeHide();
            return new HomePage(webDriver);

        }




}
