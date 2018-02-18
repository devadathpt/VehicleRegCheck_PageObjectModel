package StepDefinitions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import Pages.*;
import model.Vehicle;
import utilities.*;
import driver.WebDriverFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.*;

public class VehicleDataCheckSteps extends AbstractSteps {

    private VehicleEnquiryPage vehicleEnquiryPage;
    private VehicleVerificationPage vehicleVerifcationPage;
    private VehicleDetailsPage vehicledetailsPage;
    Vehicle expectedVehicle = null;
    private Properties config = null;
    private static final Logger logger = LogManager.getLogger(VehicleDataCheckSteps.class);

    @Given("^the DVLA information service is available$")
    public void the_DVLA_information_service_is_available() throws IOException {

        config = new Properties();
        FileInputStream fs1  = LoadConfigClass.configfile((FileInputStream fs) -> fs); ;
        config.load(fs1);

        webDriver = WebDriverFactory.createInstance();
        logger.info("Opening Browser");
        logger.info("               ");

        webDriver.get(config.getProperty("DVLA_URL"));
        MainPage mainPage = createPage(MainPage.class);
        vehicleEnquiryPage = mainPage.clickOnStartNowButton();
    }

    @And("^I enter \"(.*?)\"$")
    public void i_enter_as(String vehicleRegistration) throws IOException {

        logger.info("Checking : "+ vehicleRegistration);
        logger.info("               ");

        expectedVehicle = VehicleDataFromExcel.getExpectedVehicleData(vehicleRegistration);
        vehicleVerifcationPage = vehicleEnquiryPage.provideRegistrationNumber(expectedVehicle.getVehicle_Registration());
        vehicledetailsPage = vehicleVerifcationPage.confirmDetails();

        }

        @Then("^verify all vehicle details from an excel with DVLA information service$")
    public void verify_each_vehicle_in_the_document_with_DVLA_information_service() throws IOException {

            logger.info("Matching Data for : "+ expectedVehicle.getVehicle_Registration());
            logger.info("               ");

            Assert.assertEquals("Vehicle make is validated", expectedVehicle.getVehicle_make(), vehicledetailsPage.getVehicledetail("Vehicle make:"));
            Assert.assertEquals("date Of First Registration is validated", expectedVehicle.getDate_of_first_registration(), vehicledetailsPage.getVehicledetail("Date of first registration:"));
            Assert.assertEquals("year Of Manufacture is validated", expectedVehicle.getYear_of_manufacture(), vehicledetailsPage.getVehicledetail("Year of manufacture:"));
            Assert.assertEquals("cylinder Capacity is validated", expectedVehicle.getCylinder_capacity(), vehicledetailsPage.getVehicledetail("Cylinder capacity (cc):"));
            Assert.assertEquals("co2 Emissions is validated", expectedVehicle.getCO2Emissions(), vehicledetailsPage.getCO2Emissions());
            Assert.assertEquals("fuel Type is validated", expectedVehicle.getFuel_type(), vehicledetailsPage.getVehicledetail("Fuel type:"));
            Assert.assertEquals("export Marker is validated", expectedVehicle.getExport_marker(), vehicledetailsPage.getVehicledetail("Export marker:"));
            Assert.assertEquals("vehicle Status is validated", expectedVehicle.getVehicle_status(), vehicledetailsPage.getVehicledetail("Vehicle status:"));
            Assert.assertEquals("vehicle Colour is validated", expectedVehicle.getVehicle_colour(), vehicledetailsPage.getVehicledetail("Vehicle colour:"));
            Assert.assertEquals("vehicle Type Approval is validated", expectedVehicle.getVehicle_type_approval(), vehicledetailsPage.getVehicledetail("Vehicle type approval:"));
            Assert.assertEquals("wheelPlan is validated", expectedVehicle.getWheelplan(), vehicledetailsPage.getVehicledetail("Wheelplan:"));
            Assert.assertEquals("revenueWeight is validated", expectedVehicle.getRevenue_weight(), vehicledetailsPage.getVehicledetail("Revenue weight:"));

            webDriver.quit();
            logger.info("Closing Browser");
            logger.info("               ");
        }

    }


