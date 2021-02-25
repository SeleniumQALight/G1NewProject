package pages;


import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'username']")
    private TextInput inputLogin;
 //   @Name(value = " ")
    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'password']")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private Button buttonSignIn;


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
        enterTextInToElement(inputLogin, login);
    }

    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPassword, passWord);
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
