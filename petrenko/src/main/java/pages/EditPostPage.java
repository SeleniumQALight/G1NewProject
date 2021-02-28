package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.Matchers.containsString;

public class EditPostPage extends ParentPage {


    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//*[text() = 'Post successfully updated.']")
    private TextBlock messagePostSuccessfullyUpdated;


    @Override
    String getRelativeUrl() {
        return "/post/edit";
    }

    @FindBy(xpath = ".//input[@name = 'title']")
    private TextInput inputTitleText;

    @FindBy(xpath = ".//button[@class = 'btn btn-primary']")
    private Button updatePostButton;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button myProfileButton;

    public EditPostPage checkIsRedirectOnEditPostPage() {

        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));

        return this;
    }


    public EditPostPage editTitleOfPost(String newPostTitle) {

     //   waitBackToPostPermalink();
      //  waitChatToBeHide();
        inputTitleText.clear();
        Util.waitABit(2);
       enterTextInToElement(inputTitleText,newPostTitle);
        Util.waitABit(2);
        clickOnElement(updatePostButton);

        return new EditPostPage(webDriver);
    }

    public EditPostPage checkIsPostSuccessfullyUpdated() {
Assert.assertTrue(messagePostSuccessfullyUpdated + " is not displayed.", isElementDisplayed(messagePostSuccessfullyUpdated));


        return new EditPostPage(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
        clickOnElement(myProfileButton);


        return new MyProfilePage(webDriver);
    }
}
