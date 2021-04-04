package pages;

import static org.hamcrest.core.StringContains.containsString;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import libs.Util;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class ProfilePage extends ParentPage {

    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private TextInput successPostDeleteElement;

    @FindBy(xpath = ".//*[@class='list-group']/a")
    private List<WebElement> postsList;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

    public ProfilePage checkIsRedirectToProfilePage(){
        waitChatToBeHide();
        Assert.assertThat(webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl()));
        return this;
    }


    public ProfilePage deletePostWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));

        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 100){
            clickOnElement(webDriver.findElement(
                    By.xpath(String.format(postTitleLocator, post_title))), " Post with title " + post_title  );
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToProfilePage()
                    .checkSuccessDeletePost();
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
            counter ++;
        }

        return this;
    }

    private ProfilePage checkSuccessDeletePost() {
        checkIsElementVisible(successPostDeleteElement);
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Number of posts with title " + post_title , 1, postsList.size());
        return this;
    }


    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
