package project02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to read configuration values from a property file.
 * If the file cannot be found, default values are used.
 *
 * @author Oliver Reyes
 * @version 2.0
 */
public class PropertyReader {

    private final Properties properties = new Properties();

    /**
     * Constructs a PropertyReader and loads configuration values
     * from the specified file.
     *
     * @param propertyFile the name of the property file to read
     */
    public PropertyReader(String propertyFile) {
        ClassLoader classLoader = PropertyReader.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(propertyFile)) {
            if (inputStream == null) {
                System.out.println("Configuration file not found, using defaults.");
            } else {
                properties.load(inputStream);
                System.out.println("Loaded configuration from " + propertyFile);
            }
        } catch (IOException e) {
            System.out.println("Error reading configuration file, using defaults.");
        }
    }

    /**
     * Retrieves an integer value from the properties file.
     *
     * @param key the property key
     * @param def the default value to return if the key is not found or invalid
     * @return the integer value associated with the key or the default
     */
    public int getInt(String key, int def) {
        try {
            return Integer.valueOf(properties.getProperty(key, String.valueOf(def)));
        } catch (NumberFormatException e) {
            return def;
        }
    }

    /**
     * Retrieves a double value from the properties file.
     *
     * @param key the property key
     * @param def the default value to return if the key is not found or invalid
     * @return the double value associated with the key or the default
     */
    public double getDouble(String key, double def) {
        try {
            return Double.valueOf(properties.getProperty(key, String.valueOf(def)));
        } catch (NumberFormatException e) {
            return def;
        }
    }

    /**
     * Returns the loaded Properties object.
     *
     * @return the Properties object
     */
    public Properties getProperties() {
        return properties;
    }
}
