package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private WebElement createPostButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement myProfileButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
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

    //HOMEWORK 02-13
    public ProfilePage clickOnMyProfileButton(){
        clickOnElement(myProfileButton);
        return new ProfilePage(webDriver);
    }

}

