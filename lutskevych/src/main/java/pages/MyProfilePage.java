package pages;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.StringContains.containsString;

public class MyProfilePage extends ParentPage {
    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//img[contains(@data-original-title,'My Profile')]")
    private Button buttonMyProfile;
    @FindBy(xpath = ".//a[contains(text(),'Posts')]")
    private TextInput bookmarkPosts;

    @FindBy(xpath = "//*[contains(text(),'Post successfully deleted')]")
    private TextInput successessDeleteMessage;
    @FindBy(xpath = ".//*[@class='list-group']/a")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

    public MyProfilePage clickOnMyProfileButton(){
        waitChatToBeHide();
        clickOnElement(buttonMyProfile);
        logger.info("Button my profile is clicked");
        return new MyProfilePage(webDriver);
    }

    public MyProfilePage checkIsSuccessRedirectToMyProfilePage (){
        waitChatToBeHide();
        checkIsElementVisible(bookmarkPosts);
        Assert.assertThat(webDriver.getCurrentUrl(),containsString(baseUrl + getRelativeUrl()));

        return this;
    }

    public void clickOnCreatedPost (String postTitle){
        String xpath = format(".//*[contains(text(),'%s')]", postTitle);
        WebElement post = webDriver.findElement(By.xpath(xpath));
        clickOnElement(post);
    }


    public boolean isPostPresent(String postTitle){
        String xpath = format(".//*[contains(text(),'%s')]", postTitle);
        List<WebElement> post = webDriver.findElements(By.xpath(xpath));
        if (post.size()>0){
            return true;
        }else{
            return false;
        }
    }
    public void checkPostWasDeleted (String postTitle){
        Assert.assertFalse("Post was not deleted", isPostPresent(postTitle));
    }

    public MyProfilePage checkIsMessageAboutSuccessDeletionIsPresent(){
        checkIsElementVisible(successessDeleteMessage);
        return this;
    }

    public MyProfilePage deletePostWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator,post_title)));
        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 100){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,post_title)))," Post with title " + post_title);
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessRedirectToMyProfilePage()
                    .checkIsMessageAboutSuccessDeletionIsPresent();
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator,post_title)));
            counter ++;
            Util.waitABit(1);
        }
        return this;
    }


    public MyProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement>postList = webDriver.findElements(By.xpath((String.format(postTitleLocator, post_title))));
        Assert.assertEquals("Number of posts with title " + post_title,1, postList.size());
        return this;
    }
    public MyProfilePage checkIsPostWasEdited(String edited_post_title) {
        List<WebElement>postList = webDriver.findElements(By.xpath((String.format(postTitleLocator, edited_post_title))));
        Assert.assertEquals("Number of posts with title " + edited_post_title,1, postList.size());
        return this;
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
