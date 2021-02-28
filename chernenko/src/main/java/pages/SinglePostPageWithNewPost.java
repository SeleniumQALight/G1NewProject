package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class SinglePostPageWithNewPost extends ParentPage{
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private Button deletePostButton;

    @FindBy(xpath = ".//h2")
    private TextInput postTitle;


    public SinglePostPageWithNewPost(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    public CreatePostPage clickOnDeleteButton(){
        clickOnElement(deletePostButton);
        return new CreatePostPage(webDriver);
    }


}
