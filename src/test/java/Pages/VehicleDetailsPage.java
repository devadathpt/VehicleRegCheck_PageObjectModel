package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.*;
import java.util.List;

public class VehicleDetailsPage extends AbstractWebPage {

    private static final Logger logger = LoggerFactory.getLogger(VehicleDetailsPage.class);

    @FindBy(css = ".list-summary-item span")
    List<WebElement> detailsList;

    @FindBy(id = "CO2EmissionShown")
    WebElement emissionDetail;

    public VehicleDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getVehicledetail(String label)
    {
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }

    public String getCO2Emissions(){
        return emissionDetail.getText();
    }


}
