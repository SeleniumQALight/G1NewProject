package pages;


import com.google.common.base.Splitter;
import libs.TestData;
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

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoinPage() {

        try {
            webDriver.get(baseUrl + getRelativeUrl());
            logger.info("Login page was open.");
        } catch (Exception e) {
            logger.info("Can not open loginPage.");
            Assert.fail("Can not open loginPage.");
        }

    }

    public void enterLoginSignIn(String login) {
        enterTextInToElement(inputUserNameInLoginIn, login);
    }

    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPasswordInLoginIn, passWord);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoinPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(password);
        clickButtonSignIn();
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    private void enterUserNameRegisterIn(String userName) {
        enterTextInToElement(inputUserNameInRegisterIn, userName);
    }

    private void enterEmailRegisterIn(String email) {
        enterTextInToElement(inputEmailInRegisterIn, email);
    }

    private void enterPasswordRegisterIn(String password) {
        enterTextInToElement(inputPassWordInRegisterIn, password);
    }

    public LoginPage fillRegisterFormAndSubmit(String userName, String email, String password) {
        openLoinPage();
        enterUserNameRegisterIn(userName);
        enterEmailRegisterIn(email);
        enterPasswordRegisterIn(password);
        clickOnElement(buttonSignUpForOurApp);
        return new LoginPage(webDriver);
    }

    public void checkPopUpMessage() {
        Assert.assertTrue("PopUp error userName was not displayed", popUpErrorUnValidUsername.isDisplayed());
    }


     public LoginPage checkCountErrorOfMessagesAfterSubmit(int countUnValidValue) {

        List<WebElement> listOfError = webDriver.findElements(By.xpath(popUpRegisterError));
        Assert.assertEquals("Count of PopUp errors Message is not correct", listOfError.size(), countUnValidValue);
        return new LoginPage(webDriver);
    }

    public void checkTextOfErrors(String textOfErrorMessages) {

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
}

