package org.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.utils.ElementsAction;

public class HourlyForecastPage {

    //********** Variables **********\\
    AndroidDriver driver;
    ElementsAction elementsAction;

    public HourlyForecastPage(AndroidDriver driver) {
        this.driver = driver;
        elementsAction = new ElementsAction(driver);
    }

    //********** Locators **********\\
    By humidityValueLocator = By.xpath("//android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_summary' and @text = 'Humidity']/preceding-sibling::android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_index' ]");
    By rainValueLocator = By.xpath("//android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_summary' and @text = 'Rain probability']/preceding-sibling::android.widget.TextView[@resource-id = 'com.wftab.weather.forecast:id/tv_index' ]");

    //*********** Actions **********\\

    @Step("Check if the Humidity and Rain values exist in the next {numberOfHoursToCheckWithin} hours")
    public boolean isHumidityAndRainValuesExistForNextHours(int numberOfHoursToCheckWithin) {
        String currentHour = elementsAction.getText(By.id("com.wftab.weather.forecast:id/tv_time"));
        String currentDateWithoutHour = getCurrentDateWithoutHour(currentHour);

        int currentHourAsNumber = getCurrentHourWithoutDate(currentHour);
        boolean isHumidityAndRainExist = true;

        for (int i = 0; i < numberOfHoursToCheckWithin; i++) {
            int nextHour = currentHourAsNumber + 1;

            isHumidityAndRainExist = CheckIfHumidityAndRainValuesExist();

            printHourHumidity(nextHour, currentDateWithoutHour);
            printHourRain(nextHour, currentDateWithoutHour);

            elementsAction.scrollIntoElement(nextHour + currentDateWithoutHour);
            currentHourAsNumber = nextHour;
        }
        return isHumidityAndRainExist;

    }

    public boolean CheckIfHumidityAndRainValuesExist() {
        return elementsAction.isElementExist(humidityValueLocator) && elementsAction.isElementExist(rainValueLocator);
    }

    //*********** Helper Methods **********\\
    public int getCurrentHourWithoutDate(String currentHour) {
        String currentHourWithoutDate = currentHour.substring(0, 2);
        return Integer.parseInt(currentHourWithoutDate);
    }

    public String getCurrentDateWithoutHour(String currentHour) {
        System.out.println("Current hour " + currentHour);
        return currentHour.substring(2, 18);
    }

    public void printHourHumidity(int nextHour, String currentDateWithoutHour) {
        System.out.println("Humidity value for " + (nextHour - 1) + currentDateWithoutHour + " is :" + driver.findElement(humidityValueLocator).getText());

    }

    public void printHourRain(int nextHour, String currentDateWithoutHour) {
        System.out.println("Rain probability value for " + (nextHour - 1) + currentDateWithoutHour + " is :" + driver.findElement(rainValueLocator).getText());
    }



}
