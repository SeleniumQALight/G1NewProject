package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{

    @FindBy (xpath =".//*[@placeholder='Username']")
    private TextInput inputLogin;

    @Name(value = "beauty password name")
    @FindBy( xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;

    @FindBy (xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy (xpath = ".//*[@id = 'username-register']")
    private TextInput pickAUsername;

    @FindBy (xpath = ".//*[@id = 'email-register']")
    private TextInput emailForRegistration;

    @FindBy (xpath = ".//*[@id ='password-register']")
    private WebElement passwordForRegistration;

    @FindBy (xpath = ".//button[contains(text(),'Sign up for OurApp')]")
    private Button buttonSignUp;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage(){
       try {
           webDriver.get(baseUrl+getRelativeUrl());
           logger.info("Login Page was opened");
       } catch (Exception e){
           logger.error("Can not open Login page");
           Assert.fail("Can not open Login page");
       }
    }

    public void enterLoginSignIn(String login) {
        enterTextIntoElement(inputLogin, login);
    }

    @Step
    public void enterUsernameIntoLogin(String newUsername){
        enterTextIntoElement(pickAUsername, newUsername);
    }

    @Step
    public void enterPasswordSignIn(String passWord) {
        enterTextIntoElement(inputPassword, passWord);
    }

    @Step
    public void enterEmailIntoLogin (String userEmail){
        enterTextIntoElement(emailForRegistration, userEmail);
    }

    @Step
    public void enterPasswordIntoLogin (String newPassword){
        enterTextIntoElement(passwordForRegistration, newPassword);
    }

    @Step
    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);

    }

    @Step
    public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
    }

    @Step
    public HomePage loginWithValidCred (){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    @Step
    public void fillLoginFormNewUserAndSubmit (String newUsername, String userEmail, String newPassword){
        openLoginPage();
        enterUsernameIntoLogin(newUsername);
        enterEmailIntoLogin(userEmail);
        enterPasswordIntoLogin(newPassword);
        clickButtonSignUp();
    }

    @Step
    public void clickButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    @Step
    public ArrayList listOfErrorMessageWereDisplayed (){
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".// div[contains(@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible')]"));
        return (ArrayList) list;

    }

    @Step
    public int numberOfErrorMessageWereDisplayed (){
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".//*[@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        return  list.size();

    }

    @Step
    public void checkErrors(String errors)  {
        webDriverWait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")));
        String [] errorsArray = errors.split(";");

        List<WebElement> actualErrorsList = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));

        Assert.assertEquals("Number os Messages", errorsArray.length, actualErrorsList.size());
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList <String > textFromErrors = new ArrayList<>();
        for (WebElement element : actualErrorsList){
            textFromErrors.add(element.getText());
        }

        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);
        }
        softAssertions.assertAll();


    }
}
