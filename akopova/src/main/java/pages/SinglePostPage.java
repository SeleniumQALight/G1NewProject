package pages;

import libs.MyUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.CoreMatchers.containsString;

public class SinglePostPage extends ParentPage{

    //public String postTitleText = "";

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextInput successMessageElement;

    @FindBy(xpath = ".//*[@data-original-title='Delete']")
    private Button deleteIcon;
    // FinbBy ".//button[@class='delete-post-button text-danger']"
   // @FindBy(xpath = ".//*[@class='svg-inline--fa fa-trash fa-w-14']")


    // Post successfully updated
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successUpdateMessage;

    @FindBy(xpath=".//*[@data-icon='edit']")
    private Button editIcon;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;

    //04-03-2021
    @FindBy(xpath=".//*[@id='post-title']")
    //@FindBy(xpath = ".//*[@class='d-flex justify-content-between']")
    public TextInput postTitleField;

    @FindBy(xpath=".//*[@class='btn btn-primary']")
    public Button buttonSaveUpdates;


    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public SinglePostPage checkIsRedirectedToSinglePostPage(){
        //MyUtil.waitABit(2);
        waitChatToBeHide();
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl())
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

    public SinglePostPage clickOnEditIcon() {
        clickOnElement(editIcon);
        return this;
    }


    public MyProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new MyProfilePage(webDriver);
    }

    public void checkIfTheTitleWasUpdated(String postTitleText) {
        //public void checkIfTheTitleWasUpdated(WebElement postTitle, String updateText) {
        Assert.assertThat("Title was not Updated", postTitleText, containsString("updated"));
    }

    public SinglePostPage checkIfTheUpdateNotificationPresent(){

        Assert.assertTrue("Successful Update message is not displayed", successUpdateMessage.isDisplayed());
        return this;
    }
    /**
    public String getPostTitle(){
        //public String getPostTitle(WebElement postTitleField){
        postTitleText = postTitleField.getText();
        logger.info("initially Title is " + postTitleText);
        return postTitleText;
    }
*/
   // @Override
    public void updatePostTitle(String postTitleText) {
        //clickOnElement();
        //enterTextIntoElement(postTitleField, postTitleText + " updated");
        enterTextIntoElement(postTitleField, postTitleText);
        //getPostTitle();
        logger.info("Updated post Title is " + postTitleText);
        //return postTitleText;
    }

    public SinglePostPage clickSaveButton() {
        clickOnElement(buttonSaveUpdates);
        return this;

    }

    public void checkIfTitleUpdated(){

    }


}
