package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private Button createPostButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button myProfileButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

//URL of this page
    @Override
    String getRelativeUrl() {
        return "/";
    }

//=========================================================

    public HomePage checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public boolean isButtonSignOutVisible() {
        return  isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutVisible()){
            loginPage.loginWithValidCred();
        }
        logger.info("Home Page was opened");
        return this;
    }

//HOMEWORK 02-13
    public ProfilePage clickOnMyProfileButton(){
        clickOnElement(myProfileButton);
        return new ProfilePage(webDriver);
    }

}
