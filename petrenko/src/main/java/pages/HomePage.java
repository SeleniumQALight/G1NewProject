package pages;

import io.qameta.allure.Step;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Form;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.logging.Logger;

import static org.hamcrest.Matchers.containsString;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private Button createPostButton;

    @FindBy(xpath = ".//*[text() = 'Complex app for testing - QA']")
    private TextBlock textComplexAppForTestingQa;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button myProfileButton;
    @FindBy(className = "mr-2")
    private Button avatarButton;
    @FindBy(xpath = ".//span[@data-original-title = 'Chat' ]")
    private Button chatButton;
    @FindBy(className = "text-white mr-2")
    private Button userNameButton;
    @FindBy(xpath = ".//a[@class = 'text-white mr-2 header-search-icon' ]")
    private Button searchButton;
    @FindBy(xpath = ".//form[@action ='/register']")
    private Form registrationFormLoginPage;
    String messageHelloNewUser = "Hello %s, your feed is empty.";


    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage(WebDriver webDriver) {

        super(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        Util.waitABit(2);
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));

        return this;
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
    public MyProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    @Step
    public HomePage openHomePage() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoinPage();
        if (!isButtonSignOutVisible()) {
            loginPage.loginWithValidCred();
        }
        logger.info("Home page was opened.");
        return this;
    }

    @Step
    public HomePage registrationFormIsNotVisible() {
        checkIsElementUnVisible(registrationFormLoginPage);
        return this;
    }

    @Step
    public HomePage messageHelloNewUserIsVisible(String validLogin) {

        String message = webDriver.findElement(By.xpath(".//h2")).getText();
        logger.info("Text after registration on the top is visible - " + message);
        Assert.assertEquals("Text is not correct.", String.format(messageHelloNewUser, validLogin), message);
        return new HomePage(webDriver);

    }

    @Step
    public HomePage checkIsSearchButtonVisible() {

        checkIsElementVisible(searchButton);

        return new HomePage(webDriver);
    }

    @Step

    public HomePage checkIsChatButtonVisible() {

        checkIsElementVisible(chatButton);
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsAvatarButtonVisible() {

        checkIsElementVisible(avatarButton);

        return new HomePage(webDriver);
    }

    @Step
    public void checkIsUserNameButtonVisible() {
        checkIsElementVisible(userNameButton);

    }

}
