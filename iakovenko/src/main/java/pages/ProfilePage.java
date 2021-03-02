package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage {
    final String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private TextInput succesPostDeleteElement;

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
        List<WebElement> listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator,post_title)));
        int counter =0;

        while (!listOfPost.isEmpty() && counter <100 ){
            clickOnElement(webDriver.findElement(
                    By.xpath(String.format(postTitleLocator, post_title))),
                    "Post with title " + post_title);
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToProfilePage()
                    .checkSuccessDeletePost()
            ;
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator,post_title)));
            counter ++;
        }

        return this;
    }



    private ProfilePage checkSuccessDeletePost() {
        checkIsElementVisible(succesPostDeleteElement);
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {

        List<WebElement> postList = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Number of posts with title" + post_title,1, postList.size());
        return this;
    }
}
