package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage{
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement checkSuccessDeletePost;
    @FindBy(xpath = ".//strong[contains(text(),'Yaroslav5 Title of Post')]")
    private WebElement yaroslavPost;



    final String postTitleLocator = ".//*[text()='%s']";
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
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
        Util.waitABit(2);
        Assert.assertThat(webDriver.getCurrentUrl()
        , containsString("https://qa-complex-app-for-testing.herokuapp.com/profile"));
        return this;
    }

    public ProfilePage deletePostWhilePresent (String post_title){
        List<WebElement> listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));

        int counter = 0;
        while (!listOfPost.isEmpty() && counter < 100){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))));
            new SinglePostPage(webDriver)
                    .checkIsRedirectToSinglePostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectedToProfilePage()
            .checkSuccessDeletePost();
            listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
            counter++;
        }
        return this;
    }
    public ProfilePage checkSuccessDeletePost(){
        checkIsElementVisible(checkSuccessDeletePost);
        return this;
    }
}
