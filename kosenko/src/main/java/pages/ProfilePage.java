//HOMEWORK 02-13

package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage {
    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[contains(text(),'Kosenko Title of Post')] ")
    private WebElement postTitle;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successDeletedMessageElement;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsRedirectedToProfilePage(){
        waitChatToBeHide();
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/profile")) ;
        return this;
    }

    public SinglePostPage clickOnPostTitle(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOnElement(postTitle);
        return new SinglePostPage(webDriver);
    }

    public ProfilePage checkIsSuccessDeletedMessageDisplayed(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        checkIsElementVisible(successDeletedMessageElement);
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postLists = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Number of posts with title " + post_title, 1, postLists.size());
        return this;
    }
}
