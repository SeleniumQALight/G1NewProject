package pages;

import libs.Util;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends ParentPage {
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public ProfilePage checkIsRedirectToProfilePage() {
        Util.waitBit(1);

    }
}
