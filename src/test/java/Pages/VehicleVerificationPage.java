package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.*;

public class VehicleVerificationPage extends AbstractWebPage {

    private static final Logger LOG = LogManager.getLogger(VehicleVerificationPage.class);

    //    @FindBy(css =".selection-button-radio[for='Correct_True'] input")
    @FindBy(css ="#Correct_True")
    private WebElement radioButtonYes;

    //    @FindBy(css =".selection-button-radio[for='Correct_False'] input")
    @FindBy(css ="#Correct_False")
    private WebElement radioButtonNo;

    @FindBy(css =".button[name='Continue']")
    private WebElement continueButton;

    public VehicleVerificationPage(WebDriver webDriver) {
        super(webDriver);
    }


    public VehicleDetailsPage confirmDetails(){
        radioButtonYes.click();
        continueButton.click();
        return createPage(VehicleDetailsPage.class);
    }

}
