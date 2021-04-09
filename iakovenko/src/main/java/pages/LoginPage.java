package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.apache.http.client.entity.EntityBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;

    //    @Name(value = "")
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;
    @FindBy(xpath = ".//*[@id = 'username-register']")
    private WebElement usernameInput;
    @FindBy(xpath = ".//*[@id = 'email-register']")
    private TextInput emailForRegistration;
    @FindBy(xpath = ".//*[@id ='password-register']")
    private WebElement passwordForRegistration;
    @FindBy(xpath = ".//*[contains(@class,'alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl + getRelativeUrl());
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    @Step
    public void enterLoginSignIn(String login) {
        enterTextInToElement(inputLogin, login);
    }

    @Step
    public void enterPasswordSignIn(String passWord) {
        enterTextInToElement(inputPassWord, passWord);
    }

    @Step
    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public void fillLoginFormAndSubmit(String login, String pass) {
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
    }

    @Step
    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    @Step
    public void enterUserName(String userName) {
        enterTextInToElement(usernameInput, userName);
    }

    @Step
    public void enterEmail(String email) {
        enterTextInToElement(emailForRegistration, email);
    }

    @Step
    public void enterPassWord(String passWord) {
        enterTextInToElement(passwordForRegistration, passWord);
    }

    public void checkErrors(String errors) {
        String[] errorsArray = errors.split(";");
        List<WebElement> actualErrorList = webDriver.findElements(By.xpath(
                ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        Assert.assertEquals("Number of messages", errorsArray.length, actualErrorList.size());


        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> textFromErrors = new ArrayList<>();
        for (WebElement element : actualErrorList) {
            textFromErrors.add(element.getText());
        }
        for (int i = 0; i<errorsArray.length;i++){
            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);
        }
        softAssertions.assertAll();
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center", messageText, alertInCenter.getText());
    }
}


