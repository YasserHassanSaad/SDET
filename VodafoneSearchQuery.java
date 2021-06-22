package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GooglePage;
import pages.SearchPage;
import utilities.baseClass;
import utilities.propertiesFile;

import static utilities.Helper.captureScreenshot;

/**
 * Initiating the test is done by this class.
 * <p>
 * This class extends the base class to chose the browser type from,
 * the test goes as follows, browser opens, renders google page to search
 * for "Vodafone" then redirects to results page, and assert if the number of
 * search results links in the 2nd and 3rd page are the same or not.
 */
public class VodafoneSearchQuery extends baseClass
{
    propertiesFile data;
    GooglePage googlePage;
    SearchPage searchPage;

    /**
     * Initiate all the before test actions.
     * <p>
     * This method initiates the web driver type, as well as instance of the
     * properties file and the pages that will be rendered in this test.
     */
    @BeforeClass
    public void beforeClass()
    {
        data = new propertiesFile();
        baseClass.initiateDriver(data.readPropertiesFile("browserType"));
        googlePage = new GooglePage(driver);
        searchPage = new SearchPage(driver);
    }

    /**
     * Starting the test in this method.
     * <p>
     * in this method, the test is operating as stated using action elements and
     * getting properties from the configuration files, and then looping in the first
     * 3 page results and store the results in an array, after the looping it asserts the
     * results of the second and third page, if they are equal the test passes, if not then
     * the test fails and a screenshot is taken.
     */
    @Test(priority = 1, description = "Searching for Vodafone in Google Search")
    public void InitiateVodafoneSearch()
    {
        driver.get(data.readPropertiesFile("initiatingURl"));

        googlePage.typeSearchBar();
        googlePage.clickOnGoogleSearchButton();

        int i, a[] = new int[3];

        for(i = 1; i <= 3; i++)
        {
            a[i - 1] = searchPage.resultQueryNumber();
            searchPage.clickOnNextButton();
            System.out.println(a[i - 1]);
        }
        try
        {
            Assert.assertEquals(a[1], a[2]);
        }
        catch (Exception e)
        {
            captureScreenshot(driver, data.readPropertiesFile("errorText"));
        }
    }

    /**
     * Ending the testing session.
     */
    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}