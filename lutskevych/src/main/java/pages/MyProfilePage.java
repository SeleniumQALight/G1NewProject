package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.StringContains.containsString;

public class MyProfilePage extends ParentPage {
    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//img[contains(@data-original-title,'My Profile')]")
    private WebElement buttonMyProfile;
    @FindBy(xpath = ".//a[contains(text(),'Posts')]")
    private WebElement bookmarkPosts;

    @FindBy(xpath = "//*[contains(text(),'Post successfully deleted')]")
    private WebElement successessDeleteMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        logger.info("Button my profile is clicked");
        return new MyProfilePage(webDriver);
    }

    public MyProfilePage checkIsSuccessRedirectToMyProfilePage (){
        waitChatToBeHide();
        checkIsElementVisible(bookmarkPosts);
        Assert.assertThat(webDriver.getCurrentUrl(),containsString("https://qa-complex-app-for-testing.herokuapp.com/profile"));

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
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,post_title))));
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
}
