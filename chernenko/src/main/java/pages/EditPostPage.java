package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.Matchers.containsString;

public class EditPostPage extends ParentPage{
    @FindBy(id = "post-body")
    private TextInput inputBody;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/edit";
    }


    public EditPostPage checkIsRedirectToEditPostPage(){
        waitChatBeHied();
        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString(getRelativeUrl()));
        return this;
    }

    public EditPostPage editPostBodyAddText(){
        webDriver.findElement(By.name("body")).click();
        String bodyText = webDriver.findElement(By.name("body")).getText();
        enterTextIntoElement(inputBody,(bodyText+"  new text was added to the Post"));
        return this;
    }


}
