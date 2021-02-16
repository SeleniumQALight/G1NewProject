package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    @FindBy (xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//*[text()='Create Post']" )
    private WebElement createPostButton;

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

   public boolean isButtonSignOutVisible() {
      return isElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public CreatePostPage clickOnCreatePostButton (){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }
}
