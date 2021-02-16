package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.containsString;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = ".//*[contains(text(),'Inga')][1]")
    private WebElement linkToPost;

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private WebElement deletionSuccessText;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


    public MyProfilePage checkIsRedirectedOnMyProfilePage(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat("Invalid page"
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


    public void checkForDeletionSuccess() {
        checkIsElementVisible(deletionSuccessText);
    }




}
