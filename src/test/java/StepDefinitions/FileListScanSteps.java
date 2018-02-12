package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import DirectoryScan.*;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class FileListScanSteps {

    public String Directory1;
    Map<String, List<String>> fileData;
    @Given("^I run the filelistScan on a \"(.*?)\"$")
    public void i_run_the_filelistScan_on_a(String Directory)  throws IOException {

        Directory1 =  System.getProperty("user.dir") + "//src//"+Directory;
        fileData = GetFileList.getFilenameAndDetails(Directory1);

    }

    @Then("^I should see a list of files$")
    public void i_should_see_a_list_of_files()  {
        System.out.println(fileData);
        // Can write Assertions to verify the files names returned.
    }

    @And("^I should be able to filter only excel files$")
    public void i_should_be_able_to_filter_only_excel_files() throws IOException  {

       Path file = GetFileList.getDataFileUri(Directory1);
       Assert.assertEquals("verify excel sheet is returned","xlsx",GetFileList.getFileExtension(file));


    }

}
