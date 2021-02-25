package pages;


import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {
    public static String validRegisterUserName = Util.getDateAndTimeFormated();
    public static String validRegisterEmail = Util.getDateAndTimeFormated() + "@" + "ukr.net";
    public static String validRegisterPassword = Util.getDateAndTimeFormated() + Util.getDateAndTimeFormated();
    public static String unValidRegisterUserName = "ab";
    public static String unValidRegisterEmail = "123";
    public static String unValidRegisterPassword = "123";
    int countUnValidValueForRegisterForm = 0;
    int countPopUpAfterSubmitRegister = 0;

    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'username']")
    private TextInput inputUserNameInLoginIn;
    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'password']")
    private WebElement inputPasswordInLoginIn;

    @FindBy(id = "username-register")
    private TextInput inputUserNameInRegisterIn;
    @FindBy(id = "email-register")
    private TextInput inputEmailInRegisterIn;
    @FindBy(id = "password-register")
    private TextInput inputPassWordInRegisterIn;


    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private Button buttonSignIn;
    @FindBy(xpath = ".//*[text() = 'Username must be at least 3 characters.']")
    private WebElement popUpErrorUnValidUsername;

    @FindBy(xpath = ".//*[text() = 'You must provide a valid email address.']")
    private WebElement popUpErrorUnValidEmail;

    @FindBy(xpath = ".//*[text() = 'Password must be at least 12 characters.']")
    private Button popUpErrorUnValidPassword;
    @FindBy(xpath = ".//button[@type = 'submit']")
    private Button buttonSignUpForOurApp;

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


}
