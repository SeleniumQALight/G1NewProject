package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
    @FindBy(id = "post-title")
    private TextInput inputTitle;
    @FindBy(id = "post-body")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSaveNewPost;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;
    @FindBy(tagName = "select")
    private Select dropDownRole;
    @FindBy (id = "select1")
    private TextInput postListType;
    @FindBy (xpath = ".// select [@id = 'select1']")
    private TextInput valueOfList;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        waitChatToBeHide();
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl() , webDriver.getCurrentUrl()); //"", expected, actual
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
