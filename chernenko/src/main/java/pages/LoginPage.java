package pages;

import libs.TestData;
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

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

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

    @FindBy (xpath = ".// div[contains(@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible')]")
    private TextInput errorMessage;



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

    public void enterUsernameIntoLogin(String newUsername){
        enterTextIntoElement(pickAUsername, newUsername);
    }

    public void enterPasswordSignIn(String passWord) {
        enterTextIntoElement(inputPassword, passWord);
    }

    public void enterEmailIntoLogin (String userEmail){
        enterTextIntoElement(emailForRegistration, userEmail);
    }

    public void enterPasswordIntoLogin (String newPassword){
        enterTextIntoElement(passwordForRegistration, newPassword);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);

    }

    public void fillLoginFormAndSubmit(String login, String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
    }

    public HomePage loginWithValidCred (){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public void fillLoginFormNewUserAndSubmit (String newUsername, String userEmail, String newPassword){
        openLoginPage();
        enterUsernameIntoLogin(newUsername);
        enterEmailIntoLogin(userEmail);
        enterPasswordIntoLogin(newPassword);
        clickButtonSignUp();
    }

    public void clickButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public Integer numberOfErrorMessageWereDisplayed (){
        // Use for the test date from TestData file
        visibilityOfAllElements();
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".// div[contains(@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible')]"));
        int numberOfErrorMessages = list.size();
        return numberOfErrorMessages;

    }

    public void textOfErrorMessageWasDisplayed (String message){
        // Use for the test date from TestData file
        visibilityOfAllElements();
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".// div[contains(@class,'alert alert-danger small liveValidateMessage liveValidateMessage--visible')]"));
        for (WebElement e: list
             ) {
            Assert.assertEquals("Wrong error message", e.getText(), message);
        }
    }

//    public LoginPage textOfErrorWasDisplayed(String post_title) {
//        List<WebElement> listOfErrors = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
//        int counter = 0;
//        while (!listOfPosts.isEmpty() & counter < 100){
//            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title)))," Post with title " + post_title);
//            new SinglePostPage(webDriver)
//                    .checkIsRedirectToSinglePostPage()
//                    .clickOnDeleteButton()
//                    .checkIsRedirectToProfilePage()
//                    .checkSuccessDeletePost();
//            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
//            counter ++;
//        }


}
