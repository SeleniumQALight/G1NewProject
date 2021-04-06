package pages;

import io.qameta.allure.Step;
import io.qameta.allure.Stories;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;


   // @Name(value = "")
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;
    @FindBy(xpath = ".//*[@id = 'username-register']")
    private WebElement useNameInput;

    @FindBy (xpath = ".//*[@id = 'email-register']")
    private TextInput emailForRegistration;

    @FindBy (xpath = ".//*[@id ='password-register']")
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
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com" + getRelativeUrl());
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }

    }
    @Step
    public void enterLoginSignIn(String login) {
        enterTextIntoElement(inputLogin, login);
    }
    @Step
    public void enterPassWordSignIn(String passWord) {
        enterTextIntoElement(inputPassword, passWord);
    }
    @Step
    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    @Step
    public void filLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(pass);
        clickButtonSignIn();
    }
    @Step
    public HomePage loginWithValidCred(){
        filLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    @Step
    public void enterUserName(String userName) {
        enterTextIntoElement(useNameInput, userName);
    }

    @Step
    public void enterEmaiL(String email) {
        enterTextIntoElement(emailForRegistration, email);
    }

    @Step
    public void enterPassword(String passWord) {
        enterTextIntoElement(passwordForRegistration, passWord);
    }

    @Step
    public void checkErrors(String errors) throws InterruptedException {
        Thread.sleep(1000);
        String[] errorsArray = errors.split(";");

        List<WebElement> actualErrorsList = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        Assert.assertEquals("Number of Messages", errorsArray.length, actualErrorsList.size());

        SoftAssertions softAssertions = new SoftAssertions();

        ArrayList<String> textFromErrors = new ArrayList<>();
        for (WebElement element : actualErrorsList){
            textFromErrors.add(element.getText());
        }

        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);
        }
        softAssertions.assertAll();

    }
}
