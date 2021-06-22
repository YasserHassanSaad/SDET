package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

/**
 * Handles all the browsers and webdrivers settings and running.
 * <p>
 * This class is used to operate on the chosen browser type using webdriver manager,
 * wanted browser type maybe changed and controlled from configuration properties file.
 */
public class baseClass
{
    public static WebDriver driver;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    /**
     * Initiate and run the driver that is passed as string to this method.
     * <p>
     * This method initiates the chosen web driver through the string passed to
     * this method, web driver manager is used to work with any version of chrome
     * and firefox, while in IE, a specific version is used for compatability issues
     * as well as using the capabilites to monetize the usage of IE.
     * @param string the browser type that is chosen to run by the user.
     * @return       the driver that is chosen and compatible with the device it is
     *               running on.
     */
    public static WebDriver initiateDriver(String string)
    {
        switch (string)
        {
            case "CH":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "FF":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "IE":
                //WebDriverManager.iedriver().driverVersion("3.141.59").arch32().setup();
                System.setProperty("webdriver.ie.driver", "./IEDriver/IEDriverServer.exe");
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability("nativeEvents", true);
                capabilities.setCapability("browserFocus", true);
                capabilities.setCapability("ignoreZoomSetting", true);
                capabilities.setCapability("requireWindowFocus", false);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                driver = new InternetExplorerDriver(capabilities);
                driver.manage().deleteAllCookies();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        tdriver.set(driver);
        return getDriver();
    }

    /**
     * Gets the driver version used.
     * @return the driver that is chosen and compatible with the device
     *         it is running on.
     */
    public static synchronized WebDriver getDriver()
    {
        return tdriver.get();
    }
}
