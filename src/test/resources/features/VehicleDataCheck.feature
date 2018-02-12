Feature: Compare vehicle data from excel with live data

  In order to verify vehicle data
  as a user
  I want to go to DVLA website and check each vehicle

Scenario Outline: :

Given the DVLA information service is available
And I enter "<VehicleRegistration>"
Then verify all vehicle details from an excel with DVLA information service

Examples:
|VehicleRegistration|
|VFZ1344            |
|GK12YRF            |
|LY15ULA            |
|DV06 HBZ           |
|HY66 WUT           |





