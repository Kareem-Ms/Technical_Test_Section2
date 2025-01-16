package org.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.time.Duration;

public class AppiumServerConfigs {

    AppiumDriverLocalService appiumServiceBuilder;

    public void startAppiumServer() {
        PropertyManager propertyManager = new PropertyManager();

        appiumServiceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File(propertyManager.readFromProperty("appiumJsFilePath")))
                .usingDriverExecutable(new File(propertyManager.readFromProperty("appiumDriverExecutablePath")))
                .withIPAddress(propertyManager.readFromProperty("appiumServerIPAddress"))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                .usingPort(Integer.parseInt(propertyManager.readFromProperty("appiumServerPort")))
                .withTimeout(Duration.ofSeconds(Integer.parseInt(propertyManager.readFromProperty("appiumServerTimeout"))))
                .build();

        appiumServiceBuilder.start();
    }

    public AppiumDriverLocalService getAppiumServiceBuilder(){
        return appiumServiceBuilder;
    }

    public void stopAppiumServer(){
        appiumServiceBuilder.stop();
        appiumServiceBuilder.close();
    }
}
