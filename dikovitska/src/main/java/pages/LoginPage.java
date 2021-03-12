package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;
//@Name(value = "")
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private TextInput inputRegUsername;

    @FindBy(xpath = ".//input[@id='email-register']")
    private TextInput inputEmail;

    @FindBy(xpath = ".//input[@id='password-register']")
    private TextInput inputRegPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUpForOurApp;

    @FindBy(xpath = ".//*[@id = 'username-register']") //дубль
    private WebElement userNameInput;

    @FindBy (xpath = ".//*[@id = 'email-register']") //дубль
    private TextInput emailForRegistration;

    @FindBy (xpath = ".//*[@id ='password-register']") //дубль
    private WebElement passwordForRegistration;


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
            logger.error("Can not open login page");
            Assert.fail("Can not open Login page");
        }

    }

    @Step
    public void enterLoginSignIn(String login) {
     /*   try {
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + "was inputted into Login Input");
        } catch (Exception e) {
            logger.error("Can not work with login input");
            Assert.fail("Can not work with login input");

        }

      */
        enterTextInToElement(inputLogin, login);
    }
    @Step
    public void enterPassWordSignIn(String passWord) {
      /*  try {
            inputPassword.clear();
            inputPassword.sendKeys(passWord);
            logger.info(passWord + "was inputted into PassWord Input");
        } catch (Exception e) {
            logger.error("Can not work with Password input");
            Assert.fail("Can not work with Password input");
        }

       */
        enterTextInToElement(inputPassword, passWord);
    }
    @Step
    public void clickButtonSignIn() {
      /*  try {
            buttonSignIn.click();
            logger.info("Button Sign In was clicked");
        } catch (Exception e) {
            logger.error("Can not work with Button SignIn");
            Assert.fail("Can not work with Button SignIn");
        }

       */
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

//HW-4
    @Step
    public void clickButtonSignUpForOurApp(){
        clickOnElement(buttonSignUpForOurApp);
    }


    @Step
    public void fillRegisterForm(String login, String email, String pass){
        enterTextInToElement(inputRegUsername, login);
        enterTextInToElement(inputEmail, email);
        enterTextInToElement(inputRegPassword, pass);
    }

    @Step
    public boolean isErrorMessageForInvalidRegUsernameDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='Username must be at least 3 characters.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    @Step
    public boolean isErrorMessageForInvalidEmailDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='You must provide a valid email address.']")).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    @Step
    public boolean isErrorMessageForInvalidRegPasswordDisplayed(){
        try{
            return webDriver.findElement(By.xpath(".//*[text()='Password must be at least 12 characters.']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    @Step
    public void enterUsername(String userName) {
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

        List<WebElement> actualErrorList = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));

        Assert.assertEquals("Number of Message", errorsArray.length, actualErrorList.size());

        SoftAssertions softAssertions = new SoftAssertions();

        ArrayList<String> textFromErrors = new ArrayList<>();
        for (WebElement element : actualErrorList){
            textFromErrors.add(element.getText());
        }

        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);

        }
    }
}
