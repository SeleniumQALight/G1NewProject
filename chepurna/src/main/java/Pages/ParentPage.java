package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver){ //constrictor
        this.webDriver = webDriver;
    }
}
