import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Shop {
    private static String extension = "@gmail.com";
    private static String password = "CactussF&1334555555";

    @Test
    public void shop() {
        WebDriver driver = common.getDriver();
        LoginForm.registerUser(driver, LoginForm.generateEmail());
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menu-item-40"))).click();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,400)");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > ul > li:nth-child(2) > .button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > ul > li:nth-child(3) > .button"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#wpmenucartli"))).click();

        driver.findElements(By.cssSelector("#page-34 > tbody > tr.cart_item")).size();

        Assert.assertEquals("Functional Programming in JS product is not added to Basket", "Functional Programming in JS", driver.findElement(By.cssSelector("#page-34 > div > div.woocommerce > form > table > tbody > tr:nth-child(2) > td.product-name > a")).getText());
        Assert.assertEquals("HTML5 Forms product is not added to Basket", "HTML5 Forms", driver.findElement(By.cssSelector("#page-34 > div > div.woocommerce > form > table > tbody > tr:nth-child(1) > td.product-name > a")).getText());

    }
}
