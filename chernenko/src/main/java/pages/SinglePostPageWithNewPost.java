package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SinglePostPageWithNewPost extends ParentPage{
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement deletePostButton;

    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    public SinglePostPageWithNewPost(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnDeleteButton(){
        clickOnElement(deletePostButton);
        return new CreatePostPage(webDriver);
    }
}
