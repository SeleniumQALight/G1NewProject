package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import libs.TestData;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        }catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }

    public void enterLoginSignIn(String login) {
        enterTextInToElement(inputLogin, login);
    }

    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPassWord, passWord);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(pass);
        clickButtonSignIn();
    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
}
