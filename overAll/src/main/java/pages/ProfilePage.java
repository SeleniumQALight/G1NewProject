package pages;

import static org.hamcrest.core.StringContains.containsString;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import libs.Util;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class ProfilePage extends ParentPage {

    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private HtmlElement successPostDeleteElement;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

    public ProfilePage checkIsRedirectToProfilePage(){
//        Util.waitABit(2);
        waitPageLoaded();
        Assert.assertThat(webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl()));
        return this;
    }


    public ProfilePage deletePostWhilePresent(String post_title) {
        List<WebElement> listOfPosts = getPostsList(post_title);

        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 100){
            WebElement post = webDriver.findElement(
                    By.xpath(String.format(postTitleLocator, post_title)));
            clickOnElement(post, "Post with text '" + post_title + "'");
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToProfilePage()
                    .checkSuccessDeletePost();
            listOfPosts = getPostsList(post_title);
            counter ++;
        }

        return this;
    }

    private ProfilePage checkSuccessDeletePost() {
        checkIsElementVisible(successPostDeleteElement);
        return this;
    }

    public ProfilePage checkPostWasAdded(String post_title) {
        List<WebElement> listOfPosts = getPostsList(post_title);
        Assert.assertEquals("Number of post in list with title '" + post_title + "'", 1, listOfPosts.size());
        return this;
    }

    private List<WebElement> getPostsList(String post_title) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
    }
}
