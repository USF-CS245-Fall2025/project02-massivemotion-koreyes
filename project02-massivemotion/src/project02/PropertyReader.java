package project02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to read the configuration values of a property file. If
 * said file cannot be found, default configurations are used instead.
 * 
 * @Author: Oliver Reyes
 * @version: 1.0
 */
public class PropertyReader {

    private Properties properties;
    
    /**
     * Constructs a PropertyReader and loads configuration values
     * from the specified file.
     * 
     * @param filename the name of the property file to read.
     */
    public PropertyReader (String propertyFile) {
        properties = new Properties();
        
        try (FileInputStream fis = new FileInputStream(propertyFile)) {
            properties.load(fis);
            System.out.println("Loaded configuration from " + propertyFile);
        } catch (IOException e) {
            System.out.println("File not found, using default configurations.");
        }
    }
    
    /**
     * Retrieves an integer value from the properties file.
     *
     * @param key the property key.
     * @param def the default value to return if the key is not found.
     * @return the integer value associated with the given key,
     * or the default value if the key does not exist.
     */
    public int getInt (String key, int def) {
        return Integer.valueOf(properties.getProperty(key, String.valueOf(def)));
    }
    
    /**
     * Retrieves a double value from the properties file.
     *
     * @param key the property key.
     * @param def the default value to return if the key is not found.
     * @return the integer value associated with the given key,
     * or the default value if the key does not exist.
     */
    public double getDouble (String key, double def) {
        return Double.valueOf(properties.getProperty(key, String.valueOf(def)));
    }
    
    /**
     * Returns the Properties object containing the loaded key-value pairs.
     * 
     * @return the Properties object.
     */
    public Properties getProperties() {
        return properties;
    }
}
