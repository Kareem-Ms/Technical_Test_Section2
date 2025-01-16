package org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    String filePath = "src/main/resources/Weather.properties";

    public String readFromProperty(String propertyName){
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            // Load properties file
            prop.load(input);

            // Get properties
            String propertyVlaue = prop.getProperty(propertyName);
            System.out.println("Read property: "+propertyName);
            // return properties
            return propertyVlaue;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
