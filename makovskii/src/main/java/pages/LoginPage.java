package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;


   // @Name(value = "")
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;


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
        enterTextIntoElement(inputLogin, login);
    }
    @Step
    public void enterPassWordSignIn(String passWord) {
        enterTextIntoElement(inputPassword, passWord);
    }
    @Step
    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    @Step
    public void filLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(pass);
        clickButtonSignIn();
    }
    @Step
    public HomePage loginWithValidCred(){
        filLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

}
