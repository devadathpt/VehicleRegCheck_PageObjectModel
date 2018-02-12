package StepDefinitions;
import Pages.AbstractWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class AbstractSteps {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSteps.class);

    protected  WebDriver webDriver;

    public <T extends AbstractWebPage> T createPage(Class<T> clazz){

        return PageFactory.initElements(webDriver, clazz);
    }

}
