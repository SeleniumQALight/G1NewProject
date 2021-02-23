//HOMEWORK 02-13
package Pages;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage{

   @FindBy(xpath = ".//*[contains(text(),'Chepurna Title of Post')] ")
    private WebElement postTitle;

   @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successDeletedMessageElement;

    final String postTitleLocator = ".//*[text()='%s']";

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

//=========================================================

    public ProfilePage chechIsRedirectToProfilePage(){
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/profile")) ;
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
                , StringContains.containsString("https://qa-complex-app-for-testing.herokuapp.com/profile"));
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
                    By.xpath(String.format(postTitleLocator, post_title))));
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
//=========================================================
}
