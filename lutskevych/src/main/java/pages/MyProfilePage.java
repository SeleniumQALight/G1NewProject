package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.String.format;

public class MyProfilePage extends ParentPage {
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
        // TODO will be fixed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkIsElementVisible(bookmarkPosts);

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
}
