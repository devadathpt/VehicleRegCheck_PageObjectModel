package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.*;


public class VehicleEnquiryPage extends  AbstractWebPage  {

    protected WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(VehicleEnquiryPage.class);

    @FindBy(id = "Vrm")
    private WebElement regInputField;

    @FindBy(css = ".button[name='Continue']")
    private WebElement continueButton;


    public VehicleEnquiryPage(WebDriver webDriver) {
        super(webDriver);

    }

    public VehicleVerificationPage provideRegistrationNumber(final String regNumber) {
        regInputField.clear();
        regInputField.sendKeys(regNumber.trim());
        continueButton.click();
        return createPage(VehicleVerificationPage.class);
    }
   }

