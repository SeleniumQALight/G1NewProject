package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.assertj.core.api.SoftAssertionError;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;
   // @Name(value = "")

    @FindBy(xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy (xpath = ".//*[@id = 'email-register']")
    private TextInput emailForRegistration;
    @FindBy (xpath = ".//*[@id ='password-register']")
    private WebElement passwordForRegistration;
    @FindBy (xpath = ".//*[@id = 'username-register']")
    private TextInput userNameInput;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/" + getRelativeUrl());
            logger.info("Site was opened");
        }catch (Exception e){
            logger.error("Can't open Login page");
            Assert.fail("Can't open Login page");
        }
    }
    @Step
    public void  enterLoginSignIn(String login) {
        enterTextInToElement(inputLogin, login);
    }
    @Step
    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPassWord, passWord);
    }
    @Step
    public void clickButtonSignIn() {
       clickOnElement(buttonSignIn);
        }
    @Step
        public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(pass);
        clickButtonSignIn();
        }
    @Step
        public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
        }
        @Step
    public void enterUserName(String userName) {
        enterTextInToElement(userNameInput, userName);
        }
        @Step
    public void enterEmail(String email) {
        enterTextInToElement(emailForRegistration, email);
        }
        @Step
    public void enterPassWord(String passWord) {
        enterTextInToElement(passwordForRegistration, passWord);
        }
        @Step
    public void checkErrors(String errors) {
        String[] errorsArray = errors.split(";");

            List<WebElement> actualErrorsList = webDriver.findElements(By.xpath(
                    ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));

            Assert.assertEquals("Number of Messages", errorsArray.length, actualErrorsList.size());

            SoftAssertions softAssertions = new SoftAssertions();

            ArrayList<String> textFromErrors = new ArrayList<>();

            for (WebElement element : actualErrorsList){
                textFromErrors.add(element.getText());

            }
            for (int i = 0; i < errorsArray.length; i++) {
                softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);

            }
            softAssertions.assertAll();
        }
    }

