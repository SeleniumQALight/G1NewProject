package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {
    @FindBy (xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;

//    @Name(value = "")
   @FindBy (xpath =".//*[@placeholder='Password']")
    private TextInput inputPassWord;

    @FindBy (xpath =".//button[text()='Sign In']" )
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
     public void fillLoginFormAndSubmit (String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
     }
    @Step
     public HomePage loginWithValidCred (){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
       return new HomePage(webDriver);
     }
}


