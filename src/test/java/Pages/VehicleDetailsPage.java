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

    public String getVehicleMake(){
        String label = "Vehicle make:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }


    public String getDateOfFirstRegistration(){
        String label = "Date of first registration:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }



    public String getYearOfManufacture(){
        String label = "Year of manufacture:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }



    public String getCylinderCapacity(){
        String label = "Cylinder capacity (cc):";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }



    public String getCO2Emissions(){
        return emissionDetail.getText();
    }


    public String getFuelType(){
        String label = "Fuel type:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }


    public String getExportMarker(){
        String label = "Export marker:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }

    public String getVehicleStatus(){
        String label = "Vehicle status:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }


    public String getVehicleColour(){
        String label = "Vehicle colour:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }


    public String getVehicleTypeApproval(){
        String label = "Vehicle type approval:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }


    public String getWheelPlan(){
        String label = "Wheelplan:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }

    public String getRevenueWeight(){
        String label = "Revenue weight:";
        for (WebElement webElement : detailsList) {
            if( webElement.getText().equalsIgnoreCase(label))
                return webElement.findElements(By.xpath("../span")).get(1).getText();
        }
        throw new RuntimeException("'"+ label +"' not found");
    }
}
