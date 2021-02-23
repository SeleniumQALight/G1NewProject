package pages;

import libs.MyUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.containsString;

public class SinglePostPage extends ParentPage{

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//*[@data-original-title='Delete']")

    // FinbBy ".//button[@class='delete-post-button text-danger']"
   // @FindBy(xpath = ".//*[@class='svg-inline--fa fa-trash fa-w-14']")

    private WebElement deleteIcon;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement profileButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage checkIsRedirectedToSinglePostPage(){
        //MyUtil.waitABit(2);
        waitChatToBeHide();
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/post/")
        );
                return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessageElement);
        return this;
    }

    public MyProfilePage clickOnDeleteIcon(){

            clickOnElement(deleteIcon);

         return new MyProfilePage(webDriver);
    }


    public MyProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new MyProfilePage(webDriver);
    }
}
