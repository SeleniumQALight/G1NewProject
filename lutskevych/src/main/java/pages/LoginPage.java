package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static java.lang.String.format;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//*[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input [@name = 'password' and @placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button [contains(text(),'Sign In')]")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input [@name = 'email']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//button [@type = 'submit']")
    private WebElement buttonSignUpForOurApp;
    @FindBy(xpath = ".//input [@id='username-register']")
    private WebElement registerLogin;
    @FindBy(xpath = ".//input [@id='password-register']")
    private WebElement registerPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        }catch(Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }

    public void enterLoginSignIn(String login) {
        enterTextIntoElement(inputLogin,login);
    }
    public void enterPasswordSignIn(String password) {
//        try{
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info(password + " was inputted into Password Input");
//        }catch (Exception e){
//            logger.error("Can not work with Password Input");
//            Assert.fail("Can not work with Password Input");
//        }
        enterTextIntoElement(inputPassword,password);
    }

    public void clickButtonSignIn() {
//        try{
//            buttonSignIn.click();
//            logger.info("Button Sign In was Clicked");
//
//        }catch(Exception e){
//            logger.error("Can not work with Button SignIn");
//            Assert.fail("Can not work with Button SignIn");
//        }
        clickOnElement(buttonSignIn);
    }
    public void clickButtonSignUpForOurApp(){
        clickOnElement(buttonSignUpForOurApp);
    }
    public boolean isAllertErrorrVisible(){
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Errorr')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isAlertForInvalidIdVisible(){
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Username must be at least 3 characters.')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isAlertForInvalidEmailVisible(){
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'You must provide a valid email address.')]")).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
    public boolean isAlertForInvalidPassVisible(){
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Password must be at least 12 characters.')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isAlertForInvalidPassInvisible(){
        try{
            List<WebElement> alert = webDriver.findElements(By.xpath(".//*[contains(text(),'Password must be at least 12 characters.')]"));
            if (alert.size()>0){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }
    public boolean buttonSignUpForOurAppIsVisible(){
        try{
            return webDriver.findElement(By.xpath(".//button [@type = 'submit']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public void fillLoginFormAndSubmit(String login,String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
    public void fullFillLoginForm(String login, String email, String pass){
            enterTextIntoElement(registerLogin, login);
            enterTextIntoElement(inputEmail, email);
            enterTextIntoElement(registerPassword, pass);
    }

}
