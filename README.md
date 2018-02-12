# VehicleRegCheck_PageObjectModel


#The Main project consists of a service to retreive
 files and their mime type, extension, size 

#/src/main/java/DirectoryScan/GetFileList.java

#The Directory for testing is kept in /src named #DirectoryForScanning and contains some files to test #the service.

# The name of the directory can be provided in the 
#  config.properties as below and the code is configured to pickup as long as the directory is under the src folder
#   Directory = DirectoryForScanning


# The  second part of the project contains cucumber feature files that are used to test the file scanning process and retreive an excel file named "VechicleRegList" 

# The excel file contains Vehicle registration details,, the cucumber feature files are used to 
# get the details of each vehicle registration from the excel file and verify the details are #matched with the vehicle details held in the DVLA information service.





 