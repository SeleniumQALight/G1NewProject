package pages;

import io.qameta.allure.Step;
import libs.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private Button createPostButton;

    @FindBy(xpath = ".//*[text() = 'Complex app for testing - QA']")
    private TextBlock textComplexAppForTestingQa;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button myProfileButton;


    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage(WebDriver webDriver) {

        super(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }
    @Step
    public boolean isButtonSignOutNotVisible() {
        return !isElementDisplayed(buttonSignOut);
    }
    @Step
    public CreatePostPage clickOnCreatePostButton() {

        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }
    @Step
    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }
    @Step
    public HomePage clickTextBackToStartHomePage() {

        clickOnElement(textComplexAppForTestingQa);
        return new HomePage(webDriver);
    }
    @Step
    public MyProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }
    @Step
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
