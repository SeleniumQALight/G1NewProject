package Pages;

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
    public void enterLoginSignIn(String login) {
    enterTextInToElement(inputLogin, login);
}

    public void enterPassSignIn(String passWord) {
    enterTextInToElement(intutPassword, passWord);
}

    public void clickButtonSignIn() {
    clickOnElement(buttonSignIn);
}

    public boolean isMessageErrorVisible() {
        try {
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Error')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

// LOGIN 4 IN 1
    public void fillLoginFormAndSubmit(String login, String pass) {
        openLoginPage();
        enterLoginSignIn(login);
        enterPassSignIn(pass);
        clickButtonSignIn();
    }

    public HomePage loginWithValidCred(){
    fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    return new HomePage(webDriver); //result is new page Home page
    }

// REGISTRATION (HOMEWORK 02-20)

    public void enterRegUsername(String username) {
        enterTextInToElement(inputRegUsername, username);
    }

    public void enterRegEmail(String email) {
        enterTextInToElement(inputRegEmail, email);
    }

    public void enterRegPassword(String passWord) {
        enterTextInToElement(inputRegPassword, passWord);
    }

    public void clickButtonSignUp() {
        clickOnElement(buttonSignUp);
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

    public LoginPage checkIsValidationMessageDisplayed (String validationMessage){
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(validationMessageLocator, validationMessage))));
        return this;
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

}





/* OLD TEST enterLoginSignIn
    public void enterLoginSignIn(String login) {
        try{
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + " was inputted into Login Input");
        }catch (Exception e){
            logger.error("Cannot work with login input");
            Assert.fail("Cannot work with login input");
        }
    }


    public LoginPage checkAndCoundValidatiotMessages(int qty){
        List <WebElement> listOfWebElements = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        int counter = listOfWebElements.size();
        logger.info(counter + " validation messages was found");
        Assert.assertEquals(qty, counter);
        return this;
    }
*/
