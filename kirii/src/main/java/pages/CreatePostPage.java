package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement profileButton;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy (id = "select1")
    private WebElement postListType;
    @FindBy (xpath = ".// select [@id = 'select1']")
    private WebElement valueOfList;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        waitChatToBeHide();
        Assert.assertEquals("Invalid page", "https://qa-complex-app-for-testing.herokuapp.com/create-post" , webDriver.getCurrentUrl()); //"", expected, actual
        return this;
    }

    public CreatePostPage enterTitleToInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        //делаем задержку

        enterTextIntoElement (inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String text) {
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public SinglePostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSaveNewPost);
        return new SinglePostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownRole(String textInDD) {
        selectTextInDropdown(dropDownRole, textInDD);
        return this;
    }
    public CreatePostPage selectValueInDropDown (String valueInDD){
        clickOnElement(postListType);
        selectValueInDropDown(valueOfList, valueInDD);
        return this;
    }

/*    public ProfilePage clickOnProfileButton(){
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }*/


}
