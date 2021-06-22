package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Handles all the UI interactions occurs in the pages.
 * <p>
 * This class includes all the UI interactions as well as verifications,
 * so to check that the test runs correctly ew also check the availabilty
 * of the UI elements itself and that there is no external problems that
 * intercepts the test going.
 */
public class actionElements
{
    private static WebDriver driver;
    public static Wait<WebDriver> wait;
    public static JavascriptExecutor executor;
    public static WebElement element;

    /**
     * Instantiate an object of this class.
     * <p>
     * @param driver the webdriver used to execute the test that
     *               these UI elements will be executed in
     */
    public actionElements(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        executor = (JavascriptExecutor) driver;
    }

    /**
     * Checks the availability of the UI elements in the page after it is rendered.
     * <p>
     * This method checks the presence, visibility and clickability of the UI locators
     * we want to interact with and then returns in, and if not it throws an error.
     * And this method is mainly to solve issues related with bad network connection,
     * bad processing and other issues that harms the pages performance.
     * @param locator    the unique by locator value of the chosen UI element.
     * @return           element object of available UI element.
     * @throws Exception error that the element is not visible either interactable.
     */
    public static WebElement checkAvailabiltyOf(By locator) throws Exception
    {
        try
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch (Exception e)
        {
            System.out.println("Element: " + locator.toString() + " is not present on the current DOM. ");
        }
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (Exception e)
        {
            System.out.println("Element: " + locator.toString() + " is not visible on the current DOM. ");
        }
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (element.isEnabled())
        {
            return element;
        }
        else
        {
            throw new Exception("Element " + locator.getClass().getName() + " is not interactable & visible");
        }
    }

    /**
     * Do the typing action in the UI elements chosen.
     * <p>
     * This method receives string of text to be typed in UI locator
     * passed to this method, and if error happens, exception is printed.
     * @param locator the unique by locator value of the chosen UI element.
     * @param string  the string that is needed to be typed in the UI locator.
     */
    public static void typeInTextField(By locator, String string)
    {
        try
        {
            WebElement element = checkAvailabiltyOf(locator);
            element.clear();
            element.sendKeys(string);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Do the clicking action to the UI elements chosen.
     * <p>
     * This method receives a UI locator value to click on it, and if error
     * happens, exception is printed.
     * @param locator the unique by locator value of the chosen UI element.
     */
    public static void clickAction(By locator)
    {
        try
        {
            WebElement element = checkAvailabiltyOf(locator);
            element.click();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}