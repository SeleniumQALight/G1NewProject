package pages;

import libs.MyUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class MyProfilePage extends ParentPage{

    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[contains(text(),'Inga')][1]")
    private WebElement linkToPost;

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private WebElement deletionSuccessText;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


    public MyProfilePage checkIsRedirectedOnMyProfilePage(){
        /**try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
        waitChatToBeHide();
        //MyUtil.waitABit(2);
        Assert.assertThat("HomePage does not match"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/profile/auto")
        );
        return this;
    }

    public void clickOnPost(){
        try {
        clickOnElement(linkToPost);
            Thread.sleep(1000);
            logger.info("Post created by me was clicked");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("post clicking error");
        }

    }


    public MyProfilePage checkForDeletionSuccess() {
        checkIsElementVisible(deletionSuccessText);
        return this;
    }


    public MyProfilePage deletePostTillWhilePresent(String post_title) {

        List<WebElement> listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        //int counter = 0;
        //while (!listOfPosts.isEmpty() && (counter<100)){
        while(!listOfPosts.isEmpty()){
                clickOnElement(webDriver.findElement(
                        By.xpath(String.format(postTitleLocator, post_title))));
                new SinglePostPage(webDriver).checkIsRedirectedToSinglePostPage()
                        .clickOnDeleteIcon()
                        .checkIsRedirectedOnMyProfilePage()
                        .checkForDeletionSuccess();
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
            //counter++;
        }


        return this;
    }
}
