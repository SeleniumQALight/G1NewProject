package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;

public class ProfilePage extends ParentPage{

    final String postTitleLocator = ".//*[text()='%s']";

    @FindBy (xpath = ".//*[contains(text(), 'successfully deleted')]")
    private WebElement successPostDeleteElement;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsRedirectToProfilePage(){
        //Util.waitABit(2);
        waitChatBeHied();
        Assert.assertThat(webDriver.getCurrentUrl(), containsString("https://qa-complex-app-for-testing.herokuapp.com/profile"));
        return this;
    }


    public ProfilePage deletePostWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        int counter = 0;
        while (!listOfPosts.isEmpty() & counter < 100){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))));
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
}
