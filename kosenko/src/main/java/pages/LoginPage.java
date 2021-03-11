package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;
   // @Name(value = "")

    @FindBy(xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassWord;

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
    public void openLoginPage(){
        try {
            webDriver.get(baseUrl + getRelativeUrl());
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
    }

