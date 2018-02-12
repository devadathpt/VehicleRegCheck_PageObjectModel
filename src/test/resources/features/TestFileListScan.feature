Feature: Compare vehicle data from excel with live data

  In order to verify vehicle data
  as a user
  I want to go to DVLA website and check each vehicle

  Scenario Outline: :

    Given I run the filelistScan on a "<Directory>"
    Then I should see a list of files
    And I should be able to filter only excel files


    Examples:
      |Directory|
      |DirectoryForScanning|

