package eu.koboo.paladins.api.config;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class PropertyConfig {

    public static PropertyConfig fromFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File \"" + filePath + "\" doesn't exists.");
            }
            InputStream stream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(stream);
            stream.close();
            return new PropertyConfig(properties);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create PropertiesConfig from file \"" + filePath + "\":", e);
        }
    }

    public static PropertyConfig fromResource(String resourcePath) {
        try {
            Class<PropertyConfig> clazz = PropertyConfig.class;
            URL resourceFile = clazz.getResource(resourcePath);
            if (resourceFile == null) {
                throw new FileNotFoundException("Resource \"" + resourcePath + "\" doesn't exists.");
            }
            InputStream stream = clazz.getResourceAsStream(resourcePath);
            if (stream == null) {
                throw new FileNotFoundException("Couldn't create InputStream for resource \"" + resourcePath + "\".");
            }
            Properties properties = new Properties();
            properties.load(stream);
            stream.close();
            return new PropertyConfig(properties);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create PropertiesConfig from resource  \"" + resourcePath + "\":", e);
        }
    }

    public static PropertyConfig empty() {
        return new PropertyConfig(new Properties());
    }

    Properties properties;

    public PropertyConfig(Properties properties) {
        this.properties = properties;
    }

    public void save(String filePath) {
        save(filePath, null);
    }

    public void save(String filePath, String comments) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            properties.store(out, comments);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't save PropertiesConfig to file \"" + filePath + "\":", e);
        }
    }

    public void save(File file) {
        save(file, null);
    }

    public void save(File file, String comments) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            properties.store(out, comments);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't save PropertiesConfig to file \"" + file.getAbsolutePath() + "\":", e);
        }
    }

    public Set<String> getAllKeys() {
        return properties.stringPropertyNames();
    }

    public WrappedResult get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            return WrappedResult.empty();
        }
        return WrappedResult.of(value);
    }

    public <T> void set(String key, T value) {
        String stringValue = convertToString(value);
        properties.setProperty(key, stringValue);
    }

    private <T> String convertToString(T value) {
        return String.valueOf(value);
    }
}
