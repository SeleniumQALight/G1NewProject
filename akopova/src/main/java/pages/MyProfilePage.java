package pages;

//import com.sun.deploy.util.JVMParameters;
//import libs.MyUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;


import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class MyProfilePage extends ParentPage{

    public final String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = postTitleLocator)
    public TextInput postTitle;

    @FindBy(xpath = ".//*[contains(text(),'Inga')][1]")
    private WebElement linkToPost;

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private TextInput deletionSuccessText;

    // List of my mesages on my profile page
    @FindBy(xpath=".//*[@class='list-group']/a")
    private List<WebElement> postList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/inga1";
    }
 //   String getRelativeUrl() {
       // return "/profile/auto";
         //   return "/profile/auto";
 //   }


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
                , containsString(baseUrl + getRelativeUrl())
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
                        By.xpath(String.format(postTitleLocator, post_title))),
                        " Post with Title " + post_title);
                new SinglePostPage(webDriver).checkIsRedirectedToSinglePostPage()
                        .clickOnDeleteIcon()
                        .checkIsRedirectedOnMyProfilePage()
                        .checkForDeletionSuccess();
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
            //counter++;
        }


        return this;
    }

    public MyProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Number of posts with title " + post_title
                ,1, postList.size());

        return this;
    }

    // 27-02-2021
    /**
    public MyProfilePage editPost(String post_title) {
        SinglePostPage singlePostPage = new SinglePostPage(webDriver);
        clickOnElement(webDriver.findElement(
                By.xpath(String.format(postTitleLocator, post_title))),
                " Post with Title " + post_title);
                singlePostPage.waitChatToBeHide();

        //Click on Edit icon
    //    SinglePostPage singlePostPage = new SinglePostPage(webDriver);

                singlePostPage.checkIsRedirectedToSinglePostPage();
                singlePostPage.clickOnEditIcon();
                singlePostPage.waitChatToBeHide();
                singlePostPage.checkIsRedirectedToSinglePostPage();
                String titleText = postTitleField.getText();
        logger.info("Current post title is " + titleText);
                singlePostPage.enterTextIntoElement(postTitleField, titleText + " updated");
                singlePostPage.clickOnElement(buttonSaveUpdates);
                //singlePostPage.checkIsRedirectedToSinglePostPage()
        singlePostPage.checkIfTheUpdateNotificationPresent()
                .checkIfTheTitleWasUpdated(postTitleField, post_title);
                singlePostPage.clickOnProfileButton()
                .waitChatToBeHide();
               ;

        return this;
    }
*/
     // 04-03-2021

     public MyProfilePage clickOnPost (String post_title) {
         SinglePostPage singlePostPage = new SinglePostPage(webDriver);
         clickOnElement(webDriver.findElement(
                 By.xpath(String.format(postTitleLocator, post_title))),
                 " Post with Title " + post_title);
         singlePostPage.waitChatToBeHide();

         return this;
     }


    public void checkNumberOfPosts(int expectedNumberOfPosts) {
         Assert.assertEquals("", expectedNumberOfPosts, postList.size());

    }
}
