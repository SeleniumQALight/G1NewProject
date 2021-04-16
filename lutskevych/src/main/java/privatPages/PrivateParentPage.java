package privatPages;

import api.EndPoints;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class PrivateParentPage {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10,webDriverWait15;
    protected final String baseUrlPrivate = EndPoints.BASE_URL_FOR_PRIVATE;
    Logger logger = Logger.getLogger(getClass());
    public PrivateParentPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait10 = new WebDriverWait(webDriver,10);
        webDriverWait15 = new WebDriverWait(webDriver,15);
    }
}
