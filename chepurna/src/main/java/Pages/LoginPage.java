package Pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

// ELEMENTS LOGIN VIA HEADER
public class LoginPage extends ParentPage{
    @FindBy(xpath =".//form[@action='/login']//input[@name='username']")
    private TextInput inputLogin;

    @FindBy(xpath =".//form[@action='/login']//input[@name='password']")
    private TextInput intutPassword;

    @FindBy(xpath =".//button[text()='Sign In']")
    private Button buttonSignIn;

// ELEMENTS FOR REGISTRATION

    @FindBy(id = "username-register")
    private WebElement inputRegUsername;

    @FindBy(id = "email-register")
    private WebElement inputRegEmail;

    @FindBy(id = "password-register")
    private WebElement inputRegPassword;

    @FindBy(xpath =".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

//Locator like String
    final String validationMessageLocator = ".//*[text()='%s']";

    @FindAll(@FindBy(xpath =".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"))
    private List <WebElement> listOfValidatiomMessages;

    @FindBy(xpath = ".//*[contains(@class,'alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

//URL of this page
    @Override
    String getRelativeUrl() {
        return "/";
    }

//=========================================================

//OPEN LOGIN PAGE
    @Step
    public void openLoginPage(){
        try {
            webDriver.get(baseUrl + getRelativeUrl()); // = URL
            logger.info("Login Page was opened");
        }catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }

// LOGIN
@Step
    public void enterLoginSignIn(String login) {
    enterTextInToElement(inputLogin, login);
}
    @Step
    public void enterPassSignIn(String passWord) {
    enterTextInToElement(intutPassword, passWord);
}
    @Step
    public void clickButtonSignIn() {
    clickOnElement(buttonSignIn);
}
    @Step
    public boolean isMessageErrorVisible() {
        try {
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Error')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

// LOGIN 4 IN 1
   @Step
    public void fillLoginFormAndSubmit(String login, String pass) {
        openLoginPage();
        enterLoginSignIn(login);
        enterPassSignIn(pass);
        clickButtonSignIn();
    }

    @Step
    public HomePage loginWithValidCred(){
    fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    return new HomePage(webDriver); //result is new page Home page
    }

// REGISTRATION (HOMEWORK 02-20)
    @Step
    public void enterRegUsername(String username) {
        enterTextInToElement(inputRegUsername, username);
    }

    @Step
    public void enterRegEmail(String email) {
        enterTextInToElement(inputRegEmail, email);
    }

    @Step
    public void enterRegPassword(String passWord) {
        enterTextInToElement(inputRegPassword, passWord);
    }

    @Step
    public void clickButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

// REGISTRATION 5 IN 1 (HOMEWORK 02-20)
    public LoginPage fillRegFormAndSubmit(String username, String email, String passWord){
        openLoginPage();
        enterRegUsername(username);
        enterRegEmail(email);
        enterRegPassword(passWord);
        clickButtonSignUp();
        return this;
    }

// COUNT VALIDATION MESSAGES (HOMEWORK 02-20)
    public LoginPage checkAndCoundValidatiotMessages(int qty){
        // work           List <WebElement> listOfWebElements = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        // does not work  List <WebElement> listOfWebElements = webDriver.findElements(validatiomMessages);
        int counter = listOfValidatiomMessages.size();
        logger.info(counter + " validation messages was found");
        Assert.assertEquals(qty, counter);
        return this;
    }

// CHECK MESSAGES (HOMEWORK 02-20)
    public LoginPage checkIsValidationMessageDisplayed (String validationMessage){
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(validationMessageLocator, validationMessage))));
        return this;
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center ", messageText, alertInCenter.getText());
    }
}






