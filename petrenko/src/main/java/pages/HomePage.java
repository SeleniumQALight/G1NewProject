package pages;

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
        //TODO
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOnElement(textComplexAppForTestingQa);
        return new HomePage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        //TODO
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }
}
