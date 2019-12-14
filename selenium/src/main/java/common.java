import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class common {
    public static WebDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", false);
        FirefoxOptions options=new FirefoxOptions(capabilities);
        WebDriver driver=new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
