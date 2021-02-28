package pages;

import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextBlock successMessageElement;

    @FindBy(xpath = ".//*[text()= \'" + TestData.VALID_TITLE + " \']")
    private TextBlock validTitleOfPost;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button deletePostButton;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;
    @FindBy(name = "select1")
    private Button dropDownInCreatePost;
    String valueOfDropDownInCreatePost = ".//*[text() = '%s']";
    @FindBy(xpath = ".//a[@class = 'text-primary mr-2']")
    private Button editPostButton;


    @Override
    String getRelativeUrl() {
        return "/post";
    }

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage checkIsRedirectOnSinglePostPage() {
        waitChatToBeHide();
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));

        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {
        Util.waitABit(2);
        checkIsElementVisible(successMessageElement);
        return this;
    }

    public SinglePostPage checkIsValidTitleIsDisplayed() {
        checkIsElementVisible(validTitleOfPost);
        return this;
    }

    public SinglePostPage checkIsButtonDeletePostIsDisplayed() {
        Util.waitABit(2);
        checkIsElementVisible(deletePostButton);
        return this;
    }

    public MyProfilePage clickOnDeletePostButton() {
        Util.waitABit(2);

        try {
            clickOnElement(deletePostButton);
            logger.info("Post was deleted.");
            return new MyProfilePage(webDriver);
        } catch (Exception e) {
            logger.info("Post was not deleted.");
            Assert.fail("Post was not deleted.");
        }


        return new MyProfilePage(webDriver);
    }


    public MyProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new MyProfilePage(webDriver);

    }


    public EditPostPage clickOnEditPostButton() {
        Util.waitABit(2);
        clickOnElement(editPostButton);

        return new EditPostPage(webDriver);
    }
}
