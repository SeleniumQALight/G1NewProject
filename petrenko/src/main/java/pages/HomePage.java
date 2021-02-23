package pages;

import libs.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private WebElement createPostButton;

    @FindBy(xpath = ".//*[text() = 'Complex app for testing - QA']")
    private WebElement textComplexAppForTestingQa;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement myProfileButton;


    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage(WebDriver webDriver) {

        super(webDriver);
    }


    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonSignOutNotVisible() {
        return !isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnCreatePostButton() {

        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HomePage clickTextBackToStartHomePage() {

        clickOnElement(textComplexAppForTestingQa);
        return new HomePage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {

        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    public HomePage openHomePage() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoinPage();
        if(!isButtonSignOutVisible()){
          loginPage.loginWithValidCred();
                }
        logger.info("Home page was opened.");
        return this;
    }
}
