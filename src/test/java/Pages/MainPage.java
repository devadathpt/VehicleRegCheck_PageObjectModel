package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import StepDefinitions.*;


public class MainPage extends AbstractWebPage {

    private static final Logger LOG = LoggerFactory.getLogger(MainPage.class);
    //@FindBy(css = ".button:contains('Start now')")
    @FindBy(linkText = "Start now")
    private WebElement startNowButton;

   public MainPage(WebDriver webDriver) {
       super(webDriver);

    }


    public VehicleEnquiryPage clickOnStartNowButton(){
        startNowButton.click();
        return createPage(VehicleEnquiryPage.class); }

}
