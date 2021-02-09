package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//*[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input [@name = 'password' and @placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button [contains(text(),'Sign In')]")
    private WebElement buttonSignIn;

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
    public boolean isAllertErrorrVisible(){
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Errorr')]")).isDisplayed();
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
}
