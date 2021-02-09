package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'password']")
    private  WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private  WebElement buttonSignIn;



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
       enterTextInToElement(inputLogin, login);
    }

    public void enterPassWordSignIn(String passWord) {
       enterTextInToElement(inputPassword, passWord);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public  void fillLoginFormAndSubmit(String login, String password){
        openLoinPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(password);
        clickButtonSignIn();
    }


}
