package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.actionElements;
import utilities.propertiesFile;
import java.util.List;

/**
 * Following is a class of the google search result page
 * applying the POM design pattern.
 * <p>
 * This class defines the main UI elements and actions in the google
 * search result page.
 */
public class SearchPage
{
    propertiesFile data = new propertiesFile();
    private WebDriver driver;
    actionElements elements;

    By resultLink = By.className(data.readPropertiesFile("resultLinkLocator"));
    By adLink = By.className(data.readPropertiesFile("adLinkLocator"));
    By nextpagebutton = By.xpath(data.readPropertiesFile("nextButtonLocator"));

    /**
     * Instantiate an object of this page's class along with its elements.
     * <p>
     * @param driver the webdriver used to execute the test on this class.
     */
    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
        elements = new actionElements(driver);
    }

    /**
     * Returns an integer of the number of search results in the page.
     * <p>
     * This method returns an integer of the number of search results
     * found in the page, by extracting the number of the pure search
     * links and the ad links and summing them together.
     * @return integer that equals to the number of search results and ad links.
     */
    public int resultQueryNumber()
    {
        List<WebElement> resultLinks = driver.findElements(resultLink);
        List<WebElement> adLinks = driver.findElements(adLink);

        int resultLinksCount = resultLinks.size();
        int adLinksCount = adLinks.size();
        int linkCount = resultLinksCount + adLinksCount;

        return linkCount;
    }

    /**
     * Apply the clicking action on the UI search button element found in
     * the search result page.
     */
    public void clickOnNextButton()
    {
        elements.clickAction(nextpagebutton);
    }
}