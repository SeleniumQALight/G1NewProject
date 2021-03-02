package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name = 'title']")
    private TextInput inputTitle;
    @FindBy(xpath = ".//textarea[@name = 'body']")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[contains(text(),'Save New Post')]")
    private Button buttonSveNewPost;
    @FindBy(tagName = "select")
    private Button dropDownRole;
    @FindBy(xpath = ".//select[@name = 'select1']")
    private Select dropDownList;
    @FindBy(xpath = ".//select//option[@value = 'One Person']")
    private Select onePerson;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage(){
        waitChatToBeHide();
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl(),webDriver.getCurrentUrl());
        return this;
    }
    public CreatePostPage enterTitleToInputTitle(String title){
        waitChatToBeHide();
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String text){
        enterTextIntoElement(inputBody, text);
        return this;
    }
    public SinglePostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSveNewPost);
        return new SinglePostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownRole(String textUnDropDown) {
        selectTextInDropDown(dropDownRole,textUnDropDown);
        return this;
    }
    public CreatePostPage selectItemFromDropDownList (){
        clickOnElement(dropDownList);
        clickOnElement(onePerson);
        return this;
    }
    public void clickButtonSaveUpdates(){
        clickOnElement(buttonSaveUpdates);
    }
    public boolean messagePostSuccessfullyUpdatedVisible(){
        try{
            return webDriver.findElement(By.xpath(".//*[contains(text(),'Post successfully updated')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
