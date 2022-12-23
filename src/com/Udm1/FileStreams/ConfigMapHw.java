package com.Udm1.FileStreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigMapHw {

    //TODO should ignore null keyNames!!!
    public String getValueFromConfigMap(Path configMapFilePath, String keyName) throws IOException {
        String property = null;

        if (configMapFilePath == null
            || keyName == null) {
            return null;
        }
        try (var bufferedReader = new BufferedReader(new FileReader(configMapFilePath.toString()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(keyName)) {
                    property = extractPropertyValue(line);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return property;
    }

    public String extractPropertyValue(String propertyLine) {
        String value = null;

        for (int i = 0; i < propertyLine.length(); i++) {
            if (propertyLine.charAt(i) == '='
                && i+1 < propertyLine.length()) {
                value = propertyLine.substring(i+1);
            }
        }
        return value;
    }

}
