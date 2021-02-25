package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextInput successMessageElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger")
    private Button deleteButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button profileButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public SinglePostPage checkIsRedirectToSinglePostPage() {
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , CoreMatchers.containsString(baseUrl + getRelativeUrl()));
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessageElement);
        return this;
    }

    //HOMEWORK 02-13
    public ProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(); */

        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnDeletePostButton() {
        /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */
        waitChatToBeHide();
        clickOnElement(deleteButton);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}