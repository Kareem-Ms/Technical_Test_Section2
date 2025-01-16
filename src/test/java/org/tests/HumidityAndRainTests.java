package org.tests;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.pages.HomePage;
import org.pages.HourlyForecastPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utils.AppiumDriverBuilder;
import org.utils.AppiumServerConfigs;

@Epic("Regression Tests")
@Feature("Humidity and Rain tests")
public class HumidityAndRainTests {

    AndroidDriver driver;
    AppiumServerConfigs appiumServerConfigs;
    AppiumDriverBuilder appiumDriverBuilder;
    HomePage homePage;
    HourlyForecastPage hourlyForecastPage;

    @Test(description = "Verify Humidity and Rain for the Next N hours")
    public void verifyHumidityAndRainForNextHours(){
        homePage.openHourlyForecastScreen();
        Assert.assertTrue(hourlyForecastPage.isHumidityAndRainValuesExistForNextHours(5));
    }

    @BeforeClass
    public void setup()  {
        appiumServerConfigs = new AppiumServerConfigs();
        appiumServerConfigs.startAppiumServer();
        appiumDriverBuilder = new AppiumDriverBuilder();
        driver = appiumDriverBuilder.getAppiumDriver(appiumServerConfigs.getAppiumServiceBuilder());
        homePage = new HomePage(driver);
        hourlyForecastPage = new HourlyForecastPage(driver);

    }
}
