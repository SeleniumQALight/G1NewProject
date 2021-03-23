package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{

    @FindBy(xpath = ".//*[@class = 'col-md-auto']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private Button createPostButton;

    @FindBy(xpath=".//*[@data-original-title='My Profile']")
    private Button profileButton;



    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public boolean isButtonSignOutVisible() {

            return isElementDisplayed(buttonSignOut);
        }
    @Step
    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }


    public boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isErrorTextVisible() {
        try {
            return webDriver.findElement(By.xpath(".//*[@class='alert alert-danger text-center']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }


    public boolean checkForTheEnteredPost() {
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'auto')]")).isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }

    public void clickOnProfileButton() {
        clickOnElement(profileButton);
    }


    public HomePage openHomePage() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutVisible()) {
            loginPage.loginWithValidCreds();
        }
        logger.info("Home Page was opened");
        return this;
    }

    public HomePage checkIsRedirectedOnHomePage() {
        waitChatToBeHide();
        Assert.assertFalse("Homepage does not match"
                , isElementDisplayed(buttonSignIn));

        return new HomePage(webDriver);
    }



}
