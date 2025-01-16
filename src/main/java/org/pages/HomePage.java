package org.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.utils.ElementsAction;

public class HomePage {

    //********** Variables **********\\
    AndroidDriver driver;
    ElementsAction elementsAction;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        elementsAction = new ElementsAction(driver);
    }

    //********** Locators **********\\
    AppiumBy weatherMenuLocator = (AppiumBy) AppiumBy.id("com.wftab.weather.forecast:id/iv_nav_menu");
    AppiumBy unitSettingDoneBtnLocator = (AppiumBy) AppiumBy.id("com.wftab.weather.forecast:id/tv_done");
    AppiumBy mainScreenTempUnitLocator = (AppiumBy) AppiumBy.id("com.wftab.weather.forecast:id/tv_temperature_unit");
    AppiumBy hourly48btnLocator = (AppiumBy) AppiumBy.id("com.wftab.weather.forecast:id/bt_header_action");
    By humidityValueLocator = By.xpath("//android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_summary' and @text = 'Humidity']/preceding-sibling::android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_index' ]");
    By rainValueLocator = By.xpath("//android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_summary' and @text = 'Rain probability']/preceding-sibling::android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_index' ]");


    //*********** Actions **********\\
    @Step("Expand the left side menu")
    public void expandMenu() {
        elementsAction.click(weatherMenuLocator);
    }

    @Step("open {itemName} from left side menu")
    public void openMenuItem(String itemName) {
        String menuItemXpath = "//android.widget.TextView[@text = '" + itemName + "']";
        elementsAction.click(By.xpath(menuItemXpath));
    }

    @Step("Change the temperature to {temperatureUnit}")
    public void changeTemperature(String temperatureUnit) {
        String temperatureUnitXpath = "//android.widget.TextView[@text = '" + temperatureUnit + "']";
        elementsAction.click(By.xpath(temperatureUnitXpath));
        elementsAction.click(unitSettingDoneBtnLocator);
    }

    @Step("Change the time format to {timeFormat}")
    public void changeTimeFormat(String timeFormat) {
        String temperatureUnitXpath = "//android.widget.TextView[@text = '" + timeFormat + "']";
        elementsAction.click(By.xpath(temperatureUnitXpath));
        elementsAction.click(unitSettingDoneBtnLocator);
    }

    @Step("Get main screen Temperature unit")
    public String getMainScreenTemperatureUnit() {
        return elementsAction.getText(mainScreenTempUnitLocator);
    }

    @Step("Get first hour in Main screen to validate hour format")
    public String getFirstHour() {
        return elementsAction.getText((By.xpath("(//android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_item_time'])[1]")));
    }

    @Step("Open 48 hours screen")
    public void openHourlyForecastScreen() {
        elementsAction.click(hourly48btnLocator);
    }

}
