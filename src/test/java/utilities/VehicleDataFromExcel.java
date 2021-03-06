package utilities;

import com.monitorjbl.xlsx.StreamingReader;
import model.Vehicle;
import org.apache.poi.ss.usermodel.*;
import DirectoryScan.GetFileList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

public class VehicleDataFromExcel extends LoadConfigClass{
    static Properties  CON = null;
    static Path Directory;
    static InputStream is = null;
    static Map<String, List<String>> vehicleData;


    public static Map<String, List<String>> getData() throws IOException
    {

        CON = new Properties();
        FileInputStream fs1  = LoadConfigClass.configfile((FileInputStream fs) -> fs); ;
        CON.load(fs1);
        Directory= Paths.get(System.getProperty("user.dir")+"//src//"+CON.getProperty("Directory"));

        Path dataFile = GetFileList.getDataFileUri(Directory);

        vehicleData = new HashMap<String, List<String>>();
        is = (is == null) ? new FileInputStream(new File(dataFile.toUri())) : is;

        Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);

        Sheet sheet = workbook.sheetIterator().next();
        Iterator rows = sheet.rowIterator();
        rows.next();

        while (rows.hasNext())

        {
            Row row = (Row) rows.next();
            List<String> vehicleAttributes = new ArrayList<String>();
            Iterator cells = row.cellIterator();

            String regNumber = ((Cell) cells.next()).getStringCellValue();
            if (regNumber.isEmpty())
            {
                break;
            }
            while (cells.hasNext()) {
                Cell cell = (Cell) cells.next();
                vehicleAttributes.add(cell.getStringCellValue());
            }
            
            vehicleData.put(regNumber, vehicleAttributes);
        }

         return vehicleData;

    }


    public static Vehicle getExpectedVehicleData(String vehicleRegistration) throws IOException {

        Vehicle v = new Vehicle();
        List<String> details = VehicleDataFromExcel.getData().get(vehicleRegistration);

        v.setVehicle_Registration(vehicleRegistration);
        v.setVehicle_make(details.get(0));
        v.setDate_of_first_registration(details.get(1));
        v.setYear_of_manufacture(details.get(2));
        v.setCylinder_capacity(details.get(3));
        v.setCO2Emissions(details.get(4));
        v.setFuel_type(details.get(5));
        v.setExport_marker(details.get(6));
        v.setVehicle_status(details.get(7));
        v.setVehicle_colour(details.get(8));
        v.setVehicle_type_approval(details.get(9));
        v.setWheelplan(details.get(10));
        v.setRevenue_weight(details.get(11));

        return v;
    }

    public static Vehicle getExpectedVehicleData1(Map<String,List<String>> excelData,String vehicleRegistration) throws IOException {

        Vehicle v = new Vehicle();
        List<String> details = excelData.get(vehicleRegistration);

        v.setVehicle_Registration(vehicleRegistration);
        v.setVehicle_make(details.get(0));
        v.setDate_of_first_registration(details.get(1));
        v.setYear_of_manufacture(details.get(2));
        v.setCylinder_capacity(details.get(3));
        v.setCO2Emissions(details.get(4));
        v.setFuel_type(details.get(5));
        v.setExport_marker(details.get(6));
        v.setVehicle_status(details.get(7));
        v.setVehicle_colour(details.get(8));
        v.setVehicle_type_approval(details.get(9));
        v.setWheelplan(details.get(10));
        v.setRevenue_weight(details.get(11));

        return v;
    }


}
