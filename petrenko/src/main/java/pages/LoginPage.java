package pages;

import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement inputLoginLoginIn;
    @FindBy(id = "username-register")
    private WebElement inputLoginRegisterIn;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegisterIn;
    @FindBy(id = "password-register")
    private WebElement inputPassWordRegisterIn;

    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//*[text() = 'Username must be at least 3 characters.']")
    private WebElement popUpErrorUnValidUsername;

    @FindBy(xpath = ".//*[text() = 'You must provide a valid email address.']")
    private WebElement popUpErrorUnValidEmail;

    @FindBy(xpath = ".//*[text() = 'Password must be at least 12 characters.']")
    private WebElement popUpErrorUnValidPassword;
    @FindBy(xpath = ".//button[@type = 'submit']")
    private WebElement buttonSignUpForOurApp;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoinPage() {

        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was open.");
        } catch (Exception e) {
            logger.info("Can not open loginPage.");
            Assert.fail("Can not open loginPage.");
        }

    }

    public void enterLoginSignIn(String login) {
        enterTextInToElement(inputLoginLoginIn, login);
    }


    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPassword, passWord);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void clickButtonSignUpForOurApp() {
        clickOnElement(buttonSignUpForOurApp);
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

    public boolean checkIsDisplayedErrorPopUpOverUserNameFieldInRegisterFormAfterSubmit(String userName) {

        if (popUpErrorUnValidUsername.isDisplayed() && userName == this.unValidRegisterUserName) {
            logger.info("UnValid userName error is displayed.");

            logger.info(userName + "---- username----");
            logger.info(unValidRegisterUserName + "---- unValidRegisterUserName----");
            logger.info(validRegisterUserName + "---- validRegisterUserName----");
            return true;
        }
        else if (popUpErrorUnValidUsername.isDisplayed() && userName == this.validRegisterUserName) {
            logger.info("Valid userName error is not displayed.");

            return true;
        } else {
            logger.info("PopUp error is not displayed.");
            return false;
        }

    }

    public boolean checkIsDisplayedErrorPopUpOverRegisterEmailFieldInRegisterForm(String registerEmail) {

        if (popUpErrorUnValidEmail.isDisplayed() && registerEmail == this.unValidRegisterEmail) {
            logger.info("UnValid email error is displayed.");
            return true;
        } else if (!popUpErrorUnValidEmail.isDisplayed() && registerEmail == this.validRegisterEmail) {
            logger.info("Valid login pop up is not displayed");
            return true;
        } else {
            logger.info("popUp error is not displayed.");
            return false;
        }


    }

    public boolean checkIsDisplayedErrorPopUpOverPasswordFieldInRegisterForm(String password) {

        if (webDriver.findElement(By.xpath(".//*[text() = 'Password must be at least 12 characters.']")).isDisplayed() && password == unValidRegisterPassword) {
            logger.info("UnValid password error is displayed.");
            return true;
        } else {
            logger.info("error is not displayed.");
            return false;
        }

    }


    public void enterLoginRegisterIn(String login) {
        enterTextInToElement(inputLoginRegisterIn, login);
    }

    private void enterEmailRegisterIn(String email) {
        enterTextInToElement(inputEmailRegisterIn, email);
    }

    private void enterPassWordRegisterIn(String password) {
        enterTextInToElement(inputPassWordRegisterIn, password);
    }


    public LoginPage fillRegisterFormAndSubmit(String login, String email, String password) {
        openLoinPage();
        enterLoginRegisterIn(login);
        enterEmailRegisterIn(email);
        enterPassWordRegisterIn(password);
        clickButtonSignUpForOurApp();
        Util.waitABit(3);

        return this;
    }


    public LoginPage checkPopUpErrorsAfterRegisterSubmit(String login, String email, String password) {

        fillRegisterFormAndSubmit(login, email, password);
        checkIsDisplayedErrorPopUpOverUserNameFieldInRegisterFormAfterSubmit(login);
        checkIsDisplayedErrorPopUpOverRegisterEmailFieldInRegisterForm(email);
        checkIsDisplayedErrorPopUpOverPasswordFieldInRegisterForm(password);

        return this;
    }

    private void countUnValidValueForRegister(String login, String email, String password) {


        if (login == unValidRegisterUserName)
            countUnValidValueForRegisterForm++;
        if (email == unValidRegisterEmail)
            countUnValidValueForRegisterForm++;
        if (password == unValidRegisterPassword)
            countUnValidValueForRegisterForm++;

        logger.info("count of unValid value for register is - " + countUnValidValueForRegisterForm);

    }
}
