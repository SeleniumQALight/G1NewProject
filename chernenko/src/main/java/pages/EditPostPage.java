package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.containsString;

public class EditPostPage extends ParentPage{

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/edit";
    }


    public EditPostPage checkIsRedirectToEditPostPage(){
        waitChatBeHied();
        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString(baseUrl+getRelativeUrl()));
        return this;
    }


}
