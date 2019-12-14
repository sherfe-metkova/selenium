import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class LoginForm {

    private static String extension = "@gmail.com";
    private static String password = "CactussF&1334555555";

    public static String generateEmail() {
        return "user" + (new Random().nextInt(10000));
    }

    private static void registerUser(WebDriver driver, String email, boolean exitBrowser) {
        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("reg_email")).sendKeys(email + extension);
        driver.findElement(By.id("reg_password")).sendKeys(password);
        driver.findElement(By.name("register")).click();
        if (exitBrowser) {
            driver.close();
        }
    }

   public static void registerUser(WebDriver driver, String email) {
        registerUser(driver, email, false);
    }

   public static void loginUser(WebDriver driver, String email) {
        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("username")).sendKeys(email + extension);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > input.woocommerce-Button.button")).click();
    }

    @Test
    public void registrationPositive() throws InterruptedException {
        String email = generateEmail();
        WebDriver driver = common.getDriver();

        registerUser(driver, email);

        Assert.assertEquals("Hello message is not correct", email, driver.findElement(By.cssSelector(".woocommerce-MyAccount-content>p>strong")).getText());
        driver.close();
    }

    @Test
    public void registrationEmailWithoutAt() {
        WebDriver driver = common.getDriver();
        String email = generateEmail();
        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("reg_email")).sendKeys(email);
        driver.findElement(By.id("reg_password")).sendKeys(password);
        driver.findElement(By.name("register")).click();

        Assert.assertTrue("User is redirected away from registration page", driver.findElements(By.id("reg_email")).size() > 0);
    }

    @Test
    public void registrationEmailInvalidExtension() {
        String email = generateEmail();
        WebDriver driver = common.getDriver();

        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("reg_email")).sendKeys(email + "@" + email);
        driver.findElement(By.id("reg_password")).sendKeys(password);
        driver.findElement(By.name("register")).click();

        Assert.assertTrue("We are redirected away from registration page", driver.findElements(By.id("reg_email")).size() > 0);
        Assert.assertEquals("Error message for valid email is not displayed",
                "Error: Please provide a valid email address.",
                driver.findElement((By.cssSelector("#page-36 > div > div.woocommerce > ul"))).getText());
    }

    @Test
    public void registrationAlreadyRegisteredEmail() throws InterruptedException {
        WebDriver driver = common.getDriver();
        String email = generateEmail();

        registerUser(driver, email);
        driver.findElement(By.cssSelector("#page-36 > div > div.woocommerce > div > p:nth-child(1) > a")).click();
        Thread.sleep(5000);
        registerUser(driver, email);
        Thread.sleep(10000);

        Assert.assertEquals("Error message for already registered email is not shown.",
                "Error: An account is already registered with your email address. Please login.",
                driver.findElement((By.cssSelector("#page-36 > div > div.woocommerce > ul > li"))).getText());
        driver.close();
    }

    @Test
    public void loginPositive() {
        WebDriver driver = common.getDriver();
        String email = generateEmail();

        registerUser(driver, email, true);
        driver = common.getDriver();
        loginUser(driver, email);

        Assert.assertEquals("Hello message is not correct", email, driver.findElement(By.cssSelector(".woocommerce-MyAccount-content>p>strong")).getText());
        driver.quit();
    }

    @Test
    public void loginNonRegistered() {
        WebDriver driver = common.getDriver();
        String email = "abcdefghijk@gmail.com";
        String password = "password1234&*&*";

        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > input.woocommerce-Button.button")).click();

        Assert.assertEquals("Error message is not displayed", "Error: A user could not be found with this email address.", driver.findElement((By.cssSelector("#page-36 > div > div.woocommerce > ul > li"))).getText());
    }

    @Test
    public void loginEmailWithoutAt() {
        WebDriver driver = common.getDriver();
        String email = generateEmail();

        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > input.woocommerce-Button.button")).click();

        Assert.assertEquals("Error message is not displayed", "ERROR: Invalid username. Lost your password?", driver.findElement(By.cssSelector("#page-36 > div > div.woocommerce > ul")).getText());
        driver.close();
    }

    @Test
    public void loginEmailInvalidExtension() {
        WebDriver driver = common.getDriver();
        String email = generateEmail();

        driver.get("http://practice.automationtesting.in/my-account/");
        driver.findElement(By.id("username")).sendKeys(email + "@" + email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > input.woocommerce-Button.button")).click();

        Assert.assertEquals("Error message is not displayed", "ERROR: Invalid username. Lost your password?", driver.findElement(By.cssSelector("#page-36 > div > div.woocommerce > ul")).getText());
        driver.close();
    }
}
