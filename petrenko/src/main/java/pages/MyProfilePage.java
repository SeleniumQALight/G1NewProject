package pages;

import libs.TestData;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = ".//*[text()= '" + TestData.VALID_TITLE + "']")
    private WebElement validTitleOfPost;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectOnMyProfilePage() {
        //TODO
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString("https://qa-complex-app-for-testing.herokuapp.com/profile"));
        return this;
    }

    public MyProfilePage checkIsPostWithTheValidTitleIsVisible() {
        try {
           validTitleOfPost.isDisplayed();
            logger.info("Post with title \" " + TestData.VALID_TITLE + "\" is visible in the list of posts.");
            return this;
        } catch (Exception e) {
            logger.info("Post with title " + TestData.VALID_TITLE + " is not visible in the  list of posts.");
            Assert.fail("Post with title " + TestData.VALID_TITLE + " is not visible in the list of posts.");
        }

        return this;
    }


    public SinglePostPage clickOnPostWithTheValidTitle() {
        //TODO
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOnElement(validTitleOfPost);
        return new SinglePostPage(webDriver);
    }
}
