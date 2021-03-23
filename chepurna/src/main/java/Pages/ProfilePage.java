//HOMEWORK 02-13
package Pages;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage{

   @FindBy(xpath = ".//*[contains(text(),'Chepurna Title of Post')] ")
    private WebElement postTitle;

   @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextInput successDeletedMessageElement;

    final String postTitleLocator = ".//*[text()='%s']";

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

//URL of this page
    @Override
    String getRelativeUrl() {
        return "/profile";
    }

//=========================================================

    public ProfilePage chechIsRedirectToProfilePage(){
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl())); //= URL
        return this;
    }

    public SinglePostPage clickOnPostTitle(){
        waitChatToBeHide();
        clickOnElement(postTitle);
        return new SinglePostPage(webDriver);
    }

    public ProfilePage checkIsSuccessDeletedMessageDisplayed(){
        checkIsElementVisible(successDeletedMessageElement);
        return this;
    }

// DELETE POST METHOD (I WAS ILL)

    public ProfilePage checkIsRedirectToProfilePage(){
        waitChatToBeHide();
        Assert.assertThat(webDriver.getCurrentUrl()
                , StringContains.containsString(baseUrl +"/profile"));
        return this;
    }

    private ProfilePage checkSuccessDeletePost() {
        checkIsElementVisible(successDeletedMessageElement);
        return this;
    }

    public ProfilePage deletePostWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));

        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 100){
            clickOnElement(webDriver.findElement(
                    By.xpath(String.format(postTitleLocator, post_title))), " Post with title " +post_title);
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectToProfilePage()
                    .checkSuccessDeletePost();
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
            counter ++;
        }
        return this;
    }

//CHECK THAT WE CREATED JUST 1 (QTY) POST

    public ProfilePage checkISPostWasAdded(String post_title, int qty){
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Number of posts with title " + post_title , qty, postsList.size());
        return this;
    }

}
