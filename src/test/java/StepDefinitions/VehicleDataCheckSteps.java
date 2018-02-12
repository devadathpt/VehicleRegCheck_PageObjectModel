package StepDefinitions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import Pages.*;
import model.Vehicle;
import utilities.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import driver.WebDriverFactory;
import org.openqa.selenium.support.PageFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.*;


public class VehicleDataCheckSteps extends AbstractSteps {

    private MainPage startPage;
    private VehicleEnquiryPage vehicleEnquiryPage;
    private VehicleVerificationPage vehicleVerifcationPage;
    private VehicleDetailsPage vehicledetailsPage;
    Vehicle expectedVehicle = null;
    private Properties config = null;
    private static final Logger logger = LogManager.getLogger(VehicleDataCheckSteps.class);

    @Given("^the DVLA information service is available$")
    public void the_DVLA_information_service_is_available() throws IOException {
        config = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//Config//config.properties");
        config.load(fs);
        webDriver = WebDriverFactory.createInstance();
        logger.info("Opening Browser");
        logger.info("               ");
        webDriver.get(config.getProperty("DVLA_URL"));
        MainPage mainPage = createPage(MainPage.class);
        vehicleEnquiryPage = mainPage.clickOnStartNowButton();
    }

    @And("^I enter \"(.*?)\"$")
    public void i_enter_as(String vehicleRegistration) throws IOException {
        expectedVehicle = VehicleDataFromExcel.getExpectedVehicleData(vehicleRegistration);
        vehicleVerifcationPage = vehicleEnquiryPage.provideRegistrationNumber(expectedVehicle.getVehicle_Registration());
        vehicledetailsPage = vehicleVerifcationPage.confirmDetails();

        }

        @Then("^verify all vehicle details from an excel with DVLA information service$")
    public void verify_each_vehicle_in_the_document_with_DVLA_information_service() throws IOException {
            System.out.println(expectedVehicle.getVehicle_Registration()+"==========================");
            Assert.assertEquals("Vehicle make is validated", expectedVehicle.getVehicle_make(), vehicledetailsPage.getVehicleMake());
            Assert.assertEquals("date Of First Registration is validated", expectedVehicle.getDate_of_first_registration(), vehicledetailsPage.getDateOfFirstRegistration());
            Assert.assertEquals("year Of Manufacture is validated", expectedVehicle.getYear_of_manufacture(), vehicledetailsPage.getYearOfManufacture());
            Assert.assertEquals("cylinder Capacity is validated", expectedVehicle.getCylinder_capacity(), vehicledetailsPage.getCylinderCapacity());
            Assert.assertEquals("co2 Emissions is validated", expectedVehicle.getCO2Emissions(), vehicledetailsPage.getCO2Emissions());
            Assert.assertEquals("fuel Type is validated", expectedVehicle.getFuel_type(), vehicledetailsPage.getFuelType());
            Assert.assertEquals("export Marker is validated", expectedVehicle.getExport_marker(), vehicledetailsPage.getExportMarker());
            Assert.assertEquals("vehicle Status is validated", expectedVehicle.getVehicle_status(), vehicledetailsPage.getVehicleStatus());
            Assert.assertEquals("vehicle Colour is validated", expectedVehicle.getVehicle_colour(), vehicledetailsPage.getVehicleColour());
            Assert.assertEquals("vehicle Type Approval is validated", expectedVehicle.getVehicle_type_approval(), vehicledetailsPage.getVehicleTypeApproval());
            Assert.assertEquals("wheelPlan is validated", expectedVehicle.getWheelplan(), vehicledetailsPage.getWheelPlan());
            Assert.assertEquals("revenueWeight is validated", expectedVehicle.getRevenue_weight(), vehicledetailsPage.getRevenueWeight());
         webDriver.quit();
        }

    }


