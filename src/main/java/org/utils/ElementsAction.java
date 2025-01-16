package org.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.fail;

public class ElementsAction {

    AndroidDriver driver;
    public ElementsAction(AndroidDriver driver){
        this.driver = driver;
    }

    public WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By elementLocator) {
        // Check if the element is clickable
        try {
            // wait for the element to be clickable
            getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            // If the element doesn't become clickable the test case will fail
            fail(toe.getMessage());
        }

        // Try to click on the element
        try {
            // Now we click on the element with selenium click method
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {
                // Force fail the test case if we couldn't perform the click
                fail("Couldn't click on the element:" + elementLocator, exception);
        }
    }


    public String getText(By elementLocator) {
        locatingElement(elementLocator);
        try {
            return driver.findElement(elementLocator).getText();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

    public void scrollIntoElement(String elementText){
        try {
            System.out.println("Scroll to "+elementText);
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+elementText+"\").instance(0))"));

        }catch (TimeoutException toe){
            fail(toe.getMessage());

        }
    }

    //This method is used to make sure that the element is visible and displayed so that we could take any action on it
    private void locatingElement(By elementLocator) {
        try {
            // Wait for the element to be visible
            getExplicitWait(driver).until(d -> driver.findElement(elementLocator).isDisplayed());
            // Check if the element is displayed
            if (!driver.findElement(elementLocator).isDisplayed()) {
                fail("The element [" + elementLocator.toString() + "] is not Displayed");
            }
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        }
    }

    public boolean isElementExist(By elementLocator){
        return driver.findElement(elementLocator).isDisplayed();
    }
}
