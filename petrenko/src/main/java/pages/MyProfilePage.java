package pages;

import libs.TestData;
import libs.Util;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.List;

import static org.hamcrest.Matchers.containsString;

public class MyProfilePage extends ParentPage {

    final String postTitleLocator = ".//*[text()= '%s']";

    @FindBy(xpath = postTitleLocator)
    private TextBlock validTitleOfPost;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button myProfileButton;
    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private TextBlock successPostDeleteElement;

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectOnMyProfilePage() {

        waitChatToBeHide();
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));
        return this;
    }

    public MyProfilePage checkIsPostWithTheValidTitleIsVisible() {
        try {
            validTitleOfPost.isDisplayed();
            logger.info("Post with title \" " + TestData.VALID_TITLE + "\" is visible in the list of posts.");
            return this;
        } catch (Exception e) {
            logger.info("Post with title " + TestData.VALID_TITLE + " is not visible in the  list of posts.");
            Assert.fail("Post with title \"" + TestData.VALID_TITLE + "\" is not visible in the list of posts.");
        }

        return this;
    }


    public SinglePostPage clickOnPostWithTheValidTitle() {

        clickOnElement(validTitleOfPost);
        return new SinglePostPage(webDriver);
    }


    public MyProfilePage checkIsPostWithTheValidTitleIsNotVisible(String postTitle) {
        try {
            Assert.assertTrue("Element is visible", !isElementDisplayed(validTitleOfPost));
            logger.info("Post with title \"" + TestData.VALID_TITLE + "\" is not visible in the  list of posts.");
            return this;
        } catch (Exception e) {
            logger.info("Post with title \" " + TestData.VALID_TITLE + "\" is visible in the list of posts.");
            Assert.fail("Post with title \" " + TestData.VALID_TITLE + "\" is visible in the list of posts.");
        }

        return this;
    }

    public MyProfilePage clickOnMyProfileButton() {

        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    public MyProfilePage deletePostWhilePresent(String post_title) {

        List<WebElement> listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        int counter =0;
        while (!listOfPost.isEmpty() && counter < 100) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))));
            new SinglePostPage(webDriver)
                    .checkIsRedirectOnSinglePostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectOnMyProfilePage()
                    .checkSuccessDeletePost();
            Util.waitABit(2);
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));

        }

        return this;
    }

    private MyProfilePage checkSuccessDeletePost() {
        checkIsElementVisible(successPostDeleteElement);
        return this;
    }

    public MyProfilePage checkIsPostWasAdded(String post_title) {


        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Numbers of posts with title " + post_title, 1, postsList.size());
        logger.info("End before");
        return this;
    }

    public SinglePostPage clickOnPostWithTitle(String post_title) {
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,post_title))));
        return new SinglePostPage(webDriver);
    }
}
