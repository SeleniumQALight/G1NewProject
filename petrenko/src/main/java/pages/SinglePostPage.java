package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.ls.LSOutput;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//*[text()= \'" + TestData.VALID_TITLE + " \']")
    private WebElement validTitleOfPost;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement deletePostButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage checkIsRedirectOnSinglePostPage() {

        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString("https://qa-complex-app-for-testing.herokuapp.com/post"));

        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {

        checkIsElementVisible(successMessageElement);
        return this;
    }

    public SinglePostPage checkIsValidTitleIsDisplayed() {
        checkIsElementVisible(validTitleOfPost);
        return this;
    }

    public SinglePostPage checkIsButtonDeletePostIsDisplayed() {
       //TODO
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkIsElementVisible(deletePostButton);
        return this;
    }

    public MyProfilePage clickOnDeletePostButton() {
        //TODO
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            clickOnElement(deletePostButton);
            logger.info("Post was deleted.");
            return new MyProfilePage(webDriver);
        }catch (Exception e){
            logger.info("Post was not deleted.");
            Assert.fail("Post was not deleted.");
        }


        return new MyProfilePage(webDriver);
    }


}
