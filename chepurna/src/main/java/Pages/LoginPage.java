package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
// ELEMENTS
public class LoginPage extends ParentPage{
    @FindBy(xpath =".//form[@action='/login']//input[@name='username']")
    private WebElement inputLogin;

    @FindBy(xpath =".//form[@action='/login']//input[@name='password']")
    private WebElement intutPassword;

    @FindBy(xpath =".//button[text()='Sign In']")
    private WebElement buttonSignIn;

//__________________________________________________________________________
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    //open Login page method
    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        }catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }
// OLD TEST enterLoginSignIn
    /*
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
     */

 // NEW TEST
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

    // 4 IN 1
    public void fillLoginFormAndSubmit(String login, String pass) {
        openLoginPage();
        enterLoginSignIn(login);
        enterPassSignIn(pass);
        clickButtonSignIn();
    }
}
