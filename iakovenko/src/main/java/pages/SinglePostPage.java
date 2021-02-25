package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {
    @FindBy (xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;
    @FindBy (xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement deleteButton;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement profileButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatToBeHide();
        Assert.assertThat("Invalid page",webDriver.getCurrentUrl(),
                containsString("https://qa-complex-app-for-testing.herokuapp.com/post/") );
        return this;
    }
    public SinglePostPage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return this;
    }

    public ProfilePage clickOnDeleteButton() {
        clickOnElement(deleteButton);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}
