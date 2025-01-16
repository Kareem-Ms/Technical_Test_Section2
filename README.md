# Technical-Test-Section2

## The main Frameworks included in the project:
- Appium
- TestNG
- Allure Report
- Json Reader for Data management

## Project Design:
- Page Object Model (POM) design pattern
- Data Driven framework
- Have a supporting Utilities package in src/main/java file path, named "utils" that includes many wrapper methods which services the project like ElementsAction class

## Steps to Execute Code
- Need to install a virtual device on an emulator
- Clone the code from the Repository
- under src/main/resources open "Weather.properties" file and modify appium server configuration and Device name to match your device
- Open POM.xml file then reload that file to install dependecies
- Go to testng.xml file then run that file.
- You can access allure report by executing the following command "allure serve" in terminal after running the code or you can check "Allure Report as Images" which contains screenshot of the reports

## Important Note
- Please note that if you run the code for the first time the application asks for many permissions so in order for the code to run smoothly firstly you need to install the application on virtual device then pass all permissions then open the main screen then start to run the script
- For the validation of humidity and rain values, the code scrolls to the hour box, then scrolls back to the start of the screen before scrolling down to the next hour box. This approach ensures that no hours are missed, as scrolling in one motion can sometimes cause some hours to be skipped. By scrolling each time from the start of the screen, we ensure that all hour boxes are accounted for.

## Code Explanation
- in the src/test/java/org you will find a package called "tests" this package contain two test classes "ChangeSettingTests.java" and "HumidityAndRainTests.java"
  - "ChangeSettingTests.java" include 2 test cases which is related to change the setting of the application
  - "HumidityAndRainTests.java" includes a test case that validates humidity and rain values for each hour over the next "N" hours. This method is designed to be dynamic by locating the current hour, then adding 1 hour to get the next hour. It then scrolls to the next box to retrieve humidity and rain values, checking their existence before proceeding to the subsequent hours. This process is repeated for the remaining hours.
- in the src/main/java/org you will find a package called "pages" this package used to include all the pages that will be used in testing so for example the "homePage" class contain methods and locators that exist in home screen in order to apply POM design pattern
- in the src/main/java/org folder there is a package called "utils" this package contain helper classes like:
    - "ElementsAction" which is designed to handel find element after applying waits then make interactions with that element.
    - "PropertiesManager" this class contains methods to read from a property file which exist under src/main/resources to access something like BaseUrl
    - "JsonFileManager" this class is used to read data from json file to inject these data in the test classes 
- in the src/test/java/org you will find a package called "testData" this package contains one json file per each test case to achieve isolation
