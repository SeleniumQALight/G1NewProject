package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage{
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private TextInput checkSuccessDeletePost;

    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//strong[contains(text(),'Yaroslav5 Title of Post')]")
    private TextInput yaroslavPost;

    @FindBy(xpath = ".//*[@class='list-group']/a")
    private List<WebElement> postsList;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

/*    public ProfilePage checkIsFindPostButton(){
        checkIsElementVisible(yaroslavPost);
        return this;
    }

    public SinglePostPage clickOnPostButton(){
        clickOnElement(yaroslavPost);
        return new SinglePostPage(webDriver);
    }*/


    public ProfilePage checkIsRedirectedToProfilePage(){
        waitChatToBeHide();
        Assert.assertThat(webDriver.getCurrentUrl()
        , containsString(baseUrl + getRelativeUrl()));
        return this;
    }
    public void clickOnCreatedPost (String postTitle){
        String xpath = format(".//*[contains(text(),'%s')]", postTitle);
        WebElement post = webDriver.findElement(By.xpath(xpath));
        clickOnElement(post);
    }

    public ProfilePage deletePostWhilePresent (String post_title){
        List<WebElement> listOfPost = webDriver.findElements(By.xpath(format(postTitleLocator, post_title)));

        int counter = 0;
        while (!listOfPost.isEmpty() && counter < 100){
            clickOnElement(webDriver.findElement(By.xpath(format(postTitleLocator, post_title))), "Post with title " + post_title);
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectedToProfilePage()
            .checkSuccessDeletePost();
            listOfPost = webDriver.findElements(By.xpath(format(postTitleLocator, post_title)));
            counter++;
        }
        return this;
    }
    public ProfilePage checkSuccessDeletePost(){
        checkIsElementVisible(checkSuccessDeletePost);
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(format(postTitleLocator, post_title)));  //формат вместо процент С добавляет значение
        Assert.assertEquals("Number of with title " + post_title, 1, postsList.size());
        return this;
    }
    public ProfilePage checkIsPostWasEdited(String edited_post_title) {
        List<WebElement>postList = webDriver.findElements(By.xpath((format(postTitleLocator, edited_post_title))));
        Assert.assertEquals("Number of posts with title " + edited_post_title,1, postList.size());
        return this;
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts", expectedNumberOfPosts, postsList.size());
    }
}