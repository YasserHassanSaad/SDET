package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Takes screenshot of the failed tests.
 */
public class Helper
{
    /**
     * Capturing screenshot.
     * <p>
     * This method is used to take screenshots whenever it is used -in our case
     * in the failed tests- this method captures screenshot and save it in the screenshot
     * folder, and if error happens, it alerts and print that.
     * @param driver         the web driver that is used to run the test.
     * @param screenshotName the name that the screenshot will be saved with.
     */
    public  static void captureScreenshot(WebDriver driver, String screenshotName)
    {
        Path destination = Paths.get("./screenshots", screenshotName+".png");
        try
        {
            Files.createDirectories(destination.getParent());
            FileOutputStream out = new FileOutputStream(destination.toString());
            out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            out.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception while taking screenshot"+ e.getMessage());
        }
    }
}
