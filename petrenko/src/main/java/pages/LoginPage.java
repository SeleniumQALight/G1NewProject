package pages;


import com.google.common.base.Splitter;
import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;

public class LoginPage extends ParentPage {


    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'username']")
    private TextInput inputUserNameInLoginIn;
    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'password']")
    private TextInput inputPasswordInLoginIn;

    @FindBy(id = "username-register")
    private TextInput inputUserNameInRegisterIn;
    @FindBy(id = "email-register")
    private TextInput inputEmailInRegisterIn;
    @FindBy(id = "password-register")
    private TextInput inputPassWordInRegisterIn;


    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private Button buttonSignIn;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = 'Username must be at least 3 characters.']")
    private TextBlock popUpErrorUnValidUsername;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = 'You must provide a valid email address.']")
    private TextBlock popUpErrorUnValidEmail;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = 'Password must be at least 12 characters.']")
    private TextBlock popUpErrorUnValidPassword;

    @FindBy(xpath = ".//button[@type = 'submit']")
    private Button buttonSignUpForOurApp;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private TextBlock registerErrorMessage;

    private String popUpRegisterError = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    private String popUpLoginError = ".//div[@class='alert alert-danger text-center']";
    @Override
    String getRelativeUrl() {
        return "/";
    }

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    public void openLoinPage() {

        try {
            webDriver.get(baseUrl + getRelativeUrl());
            logger.info("Login page was open.");
        } catch (Exception e) {
            logger.info("Can not open loginPage.");
            Assert.fail("Can not open loginPage.");
        }

    }
    @Step
    public void enterLoginSignIn(String login) {
        enterTextInToElement(inputUserNameInLoginIn, login);
    }
    @Step
    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPasswordInLoginIn, passWord);
    }
    @Step
    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    @Step
    public LoginPage fillLoginFormAndSubmit(String login, String password) {
        openLoinPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(password);
        clickButtonSignIn();
        return new LoginPage(webDriver);
    }
@Step
    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
    @Step
    private void enterUserNameRegisterIn(String userName) {
        enterTextInToElement(inputUserNameInRegisterIn, userName);
    }
    @Step
    private void enterEmailRegisterIn(String email) {
        enterTextInToElement(inputEmailInRegisterIn, email);
    }
    @Step
    private void enterPasswordRegisterIn(String password) {
        enterTextInToElement(inputPassWordInRegisterIn, password);
    }
    @Step
    public LoginPage fillRegisterFormAndSubmit(String userName, String email, String password) {
        openLoinPage();
        enterUserNameRegisterIn(userName);
        enterEmailRegisterIn(email);
        enterPasswordRegisterIn(password);
        clickOnElement(buttonSignUpForOurApp);
        return new LoginPage(webDriver);
    }
    @Step
    public void checkPopUpMessage() {
        Assert.assertTrue("PopUp error userName was not displayed", popUpErrorUnValidUsername.isDisplayed());
    }

    @Step
     public LoginPage checkCountErrorOfMessagesAfterSubmitRegisterIn(int countUnValidValue) {

         Util.waitABit(5);
        List<WebElement> listOfError = webDriver.findElements(By.xpath(popUpRegisterError));
        logger.info(listOfError.size());
        Assert.assertEquals("Count of PopUp errors Message is not correct", listOfError.size(), countUnValidValue);
        return new LoginPage(webDriver);
    }

    @Step
    public LoginPage checkCountErrorOfMessagesAfterSubmitLoginIn(int countUnValidValue) {

        Util.waitABit(5);
        List<WebElement> listOfError = webDriver.findElements(By.xpath(popUpLoginError));

        Assert.assertEquals("Count of PopUp errors Message is not correct", listOfError.size(), countUnValidValue);
        return new LoginPage(webDriver);
    }

    @Step
    public void checkTextOfErrorsInRegisterIn(String textOfErrorMessages) {

        if(textOfErrorMessages.isEmpty()){
            logger.info("textOfErrorMessages ia empty.");
        }
        else {
            String[] words = textOfErrorMessages.split(";");

            for (int i = 0; i < words.length; i++) {
                Assert.assertTrue("--------- " + words[i] + " message is not correct in PopUp. ", listOfTextsOfWebElementByXpath(popUpRegisterError).contains(words[i]));
            }
        }

    }
    @Step
    public void checkTextOfErrorsInLoginIn(String textOfErrorMessages) {

        if(textOfErrorMessages.isEmpty()){
            logger.info("textOfErrorMessages ia empty.");
        }
        else {
            Assert.assertEquals("", textOfErrorMessages, webDriver.findElement(By.xpath(popUpLoginError)).getText() );
        }

    }
}

