package StepDefinitions;

import Pages.MainPage;
import Pages.VehicleDetailsPage;
import Pages.VehicleEnquiryPage;
import Pages.VehicleVerificationPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import driver.WebDriverFactory;
import model.Vehicle;
import org.junit.Assert;
import utilities.LoadConfigClass;
import utilities.VehicleDataFromExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class VehicleDataCheckExcelSteps extends AbstractSteps {
    Vehicle expectedVehicle = null;
    Map<String,List<String>> exceldata;
    private VehicleEnquiryPage vehicleEnquiryPage;
    private VehicleVerificationPage vehicleVerifcationPage;
    private VehicleDetailsPage vehicledetailsPage;
    private Properties config = null;


    @Given("^I obtain the excel file from a Directory$")
    public void i_obtain_the_excel_file_from_a_Directory() throws Exception  {

        exceldata = VehicleDataFromExcel.getData();
    }

    @Then("^I validate each data from excel DVLA information service$")
    public void i_validate_each_data_from_excel_DVLA_information_service() throws IOException {

        config = new Properties();
        FileInputStream fs1  = LoadConfigClass.configfile((FileInputStream fs) -> fs);
        config.load(fs1);

        List<String> vehicleRegs = new ArrayList<>(exceldata.keySet());

        for (String r: vehicleRegs)
             {
                 webDriver = WebDriverFactory.createInstance();
                 webDriver.get(config.getProperty("DVLA_URL"));
                 MainPage mainPage = createPage(MainPage.class);
                 vehicleEnquiryPage = mainPage.clickOnStartNowButton();
                 expectedVehicle = VehicleDataFromExcel.getExpectedVehicleData1(exceldata,r);
                 vehicleVerifcationPage = vehicleEnquiryPage.provideRegistrationNumber(r);
                 vehicledetailsPage = vehicleVerifcationPage.confirmDetails();
                 List<String> details1 = exceldata.get(r);

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
                 }

        }

    }



