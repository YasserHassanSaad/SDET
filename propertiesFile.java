package utilities;

import java.io.*;
import java.util.Properties;

/**
 * Handles all the mapping in the project with the properties file.
 * <p>
 * This class handles the paths and the mapping with the properties file
 * either to read from the properties configuration file or write in it.
 */
public class propertiesFile
{
    private String value;
    public String configFilePath = "resources\\config.properties";
    Properties properties = new Properties();

    /**
     * Read data from the properties configuration file.
     * <p>
     * This method takes the string passed which is a key in the
     * config.properties file, and then concatenate it with the relative
     * path of the config file to return the value of the key passed.
     * @param string it contains the key of a property in the configuration file.
     * @return       the value of the key passed.
     */
    public String readPropertiesFile(String string)
    {
        try
        {
            properties.load(new FileInputStream(configFilePath));
            InputStream input = new FileInputStream(configFilePath);
            properties.load(input);
            return(properties.getProperty(string));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return("None");
    }

    /**
     * Write data to the properties configuration file.
     * <p>
     * This method takes the string passed which is a key that will be created in the
     * config.properties file, then concatenate it with the relative path of the
     * config file, accompained with the value assigned to this key, along
     * with a comment if prefered to.
     * @param stringKey     the key of the property that will be created.
     * @param stringValue   the value assigned to the property that will be created.
     * @param stringComment an optional argument which is a comment if wanted to,
     *                      else pass an empty string.
     */
    public void writePropertiesFile(String stringKey, String stringValue, String stringComment)
    {
        try
        {
            OutputStream output = new FileOutputStream(configFilePath);
            properties.setProperty(stringKey, stringValue);
            properties.store(output, stringComment);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}