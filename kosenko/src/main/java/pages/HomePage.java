package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private Button createPostButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button profileButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    //=========================================================
    @Override
    String getRelativeUrl() {
        return "/";
    }
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

    //HOMEWORK 02-13
    public ProfilePage clickOnMyProfileButton(){
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }

}

