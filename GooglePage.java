package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.actionElements;
import utilities.propertiesFile;

/**
 * Following is a class of the main google page applying the POM design pattern.
 * <p>
 * This class defines the main UI elements and actions in the google main page.
 */
public class GooglePage
{
    propertiesFile data = new propertiesFile();
    private WebDriver driver;
    actionElements elements;

    By searchbar = By.xpath(data.readPropertiesFile("searchbarLocator"));
    By searchbutton = By.className(data.readPropertiesFile("searchbuttonLocator"));

    /**
     * Instantiate an object of this page's class along with its elements.
     * <p>
     * @param driver the webdriver used to execute the test on this class.
     */
    public GooglePage(WebDriver driver)
    {
        this.driver = driver;
        elements = new actionElements(driver);
    }

    /**
     * Apply the typing action on the UI search bar element found in the main page.
     */
    public void typeSearchBar()
    {
        elements.typeInTextField(searchbar, data.readPropertiesFile("mainSearchTopic"));
    }

    /**
     * Apply the clicking action on the UI search button element found in the main page.
     */
    public void clickOnGoogleSearchButton()
    {
        elements.clickAction(searchbutton);
    }
}