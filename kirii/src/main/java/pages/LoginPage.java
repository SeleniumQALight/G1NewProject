package pages;

import io.qameta.allure.Step;
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

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private TextInput inputLogin;

    //@Name(value = "")
    @FindBy(xpath = ".//*[@placeholder='Password']")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//input [@name = 'email']")
    private TextInput inputEmail;

    @FindBy(xpath = ".//button [@type = 'submit']")
    private Button clickSignUpButton;

    @FindBy(xpath = ".//input [@id='username-register']")
    private TextInput registerLogin;

    @FindBy(xpath = ".//input [@id='password-register']")
    private TextInput registerPassword;



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
        }catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
        }
    }

    @Step
    public void enterLoginSignIn(String login) {
//        try {
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted into Login Input");
//        }catch (Exception e){
//            logger.error("Can not work with Login Input");
//            Assert.fail("Can not work with Login Input");
//        }
        enterTextIntoElement(inputLogin, login);
    }
    @Step
    public void enterPasswordSignIn(String password) {
//        try {
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info(password + " was inputted into Password Input");
//        }catch (Exception e){
//            logger.error("Can not work with Password Input");
//            Assert.fail("Can not work with Password Input");
//        }
        enterTextIntoElement(inputPassword, password);
    }

    @Step
    public void clickButtonSignIn() {
//        try {
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        }catch (Exception e){
//            logger.error("Can not work with Button SignIn");
//            Assert.fail("Can not work with Button SignIn");
//        }
        clickOnElement(buttonSignIn);
    }


    public boolean isButtonSignInVisible() {
        try {
            logger.info("Button Sign In visible");
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isMessageErrorVisible() {
        try {
            logger.info("Message error visible");
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Error')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isButtonSignOutNotVisible() {
        try {
            logger.info("Button Sign Out not visible");
            return !webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return true;
        }
    }

    public void clickSignUpButton(){
        clickOnElement(clickSignUpButton);
    }
    public boolean isAlertErrorVisible(){
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
    public boolean signUpButtonIsVisible(){
        try{
            return webDriver.findElement(By.xpath(".//button [@type = 'submit']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    @Step
    public void fillLoginFormAndSubmit(String login,String pass){
        openLoginPage();
        enterLoginSignIn(login);
        enterPasswordSignIn(pass);
        clickButtonSignIn();
    }
    @Step
    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
    public void fullFillLoginForm(String login, String email, String pass){
        enterTextIntoElement(registerLogin, login);
        enterTextIntoElement(inputEmail, email);
        enterTextIntoElement(registerPassword, pass);
    }
    public int countValidateMessages(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> messagesCount = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        return messagesCount.size();
    }

    @Step
    public void checkErrors(String errors) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] errorsArray = errors.split(";");

        List<WebElement> actualErrorList = webDriver.findElements(By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        Assert.assertEquals("Number of Messages", errorsArray.length, actualErrorList.size());

        SoftAssertions softAssertions = new SoftAssertions();

        ArrayList<String> textFromErrors = new ArrayList<>();
        for (WebElement element : actualErrorList){
            textFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++){
            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);
        }
        softAssertions.assertAll();
    }
}
