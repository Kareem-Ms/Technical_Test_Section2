package org.tests;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.pages.HomePage;
import org.testng.Assert;
import org.utils.AppiumDriverBuilder;
import org.utils.AppiumServerConfigs;
import org.utils.JsonFileManager;
import org.testng.annotations.*;

@Epic("Regression Tests")
@Feature("change setting tests")
public class ChangeSettingTests {

    AndroidDriver driver;
    JsonFileManager jsonFileManager;
    AppiumServerConfigs appiumServerConfigs;
    AppiumDriverBuilder appiumDriverBuilder;
    HomePage homePage;

    @Test(description = "verify changing temperature successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyChangingTemperature(){
        homePage.expandMenu();
        homePage.openMenuItem("Unit setting");
        homePage.changeTemperature(jsonFileManager.getTestData("temperatureUnit"));
        homePage.openMenuItem("Home");
        Assert.assertEquals(homePage.getMainScreenTemperatureUnit(),jsonFileManager.getTestData("temperatureUnit"));
    }

    @Test(description = "verify changing time format successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyChangingTimeFormat(){
        homePage.expandMenu();
        homePage.openMenuItem("Unit setting");
        homePage.changeTimeFormat(jsonFileManager.getTestData("timeFormat"));
        homePage.openMenuItem("Home");
        Assert.assertFalse(homePage.getFirstHour().contains("AM") || homePage.getFirstHour().contains("PM"));
    }


    @BeforeClass
    public void setup()  {
        jsonFileManager = new JsonFileManager("src/test/java/org/testData/ChangeSettingTestData.json");
        appiumServerConfigs = new AppiumServerConfigs();
        appiumServerConfigs.startAppiumServer();
        appiumDriverBuilder = new AppiumDriverBuilder();
        driver = appiumDriverBuilder.getAppiumDriver(appiumServerConfigs.getAppiumServiceBuilder());
        homePage = new HomePage(driver);

    }

    @AfterClass
    public void tearDown(){
        appiumDriverBuilder.closeAppiumDriver();
        appiumServerConfigs.stopAppiumServer();
    }
}
