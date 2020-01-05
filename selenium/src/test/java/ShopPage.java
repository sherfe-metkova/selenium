import stepDefinitions.Common;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectModels.LoginPagePom;

import java.util.ArrayList;
import java.util.List;

public class ShopPage {
    List<String> cartItems = new ArrayList<String>();
    List<String> cartItemsPrice = new ArrayList<String>();

    @Test
    public void addItems() throws InterruptedException {
        WebDriver driver = Common.getDriver();
        LoginPagePom loginForm = new LoginPagePom(driver);

        //loginForm.registerUser(driver, LoginForm.generateEmail());
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("#menu-item-40 > a"))).click();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,400)");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                ("#content > ul > li:nth-child(2) > .button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                ("#content > ul > li:nth-child(3) > .button"))).click();

        boolean isQuantityUpdated = false;
        for (int i = 0; i < 20; i++) {
            if (driver.findElements(By.className("cartcontents")).size() > 0) {
                if (driver.findElement(By.className("cartcontents")).getText().
                        equalsIgnoreCase("2 items")) {
                    isQuantityUpdated = true;
                    break;
                }
            }
            Thread.sleep(500);
        }
        Assert.assertTrue("Cart text is not updated for the given 10 seconds", isQuantityUpdated);

        driver.findElement(By.id("wpmenucartli")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.cssSelector("#page-34 > div > div > form > table > tbody > tr.cart_item")));
        int size = driver.findElements(By.cssSelector
                ("#page-34 > div > div > form > table > tbody > tr.cart_item")).size();
        Assert.assertEquals("Items count in the cart is not the expected one", 2, size);

        List<WebElement> itemsList = driver.findElements(By.cssSelector
                ("#page-34 > div > div > form > table > tbody > tr.cart_item"));

        cartItems.add("Functional Programming in JS");
        cartItems.add("HTML5 Forms");
        checkItems(cartItems, itemsList);

        cartItemsPrice.add("250.00");
        cartItemsPrice.add("280.00");
        checkItemsPrice(cartItemsPrice, itemsList);

        driver.close();
    }

    private void checkItems(List<String> cartItems, List<WebElement> itemsList) {
        for (String itemName : cartItems) {
            Assert.assertTrue(String.format("Item with name %s is not added to the cart", itemName),
                    doesCollectionContainItem(itemName, itemsList));
        }
    }

    private boolean doesCollectionContainItem(String itemText, List<WebElement> itemsList) {
        for (WebElement element : itemsList) {
            if (element.findElement(By.cssSelector(".product-name > a")).
                    getText().equalsIgnoreCase(itemText)) {
                return true;
            }
        }

        return false;
    }

    private void checkItemsPrice(List<String> cartItemsPrice, List<WebElement> itemsList) {
        for (String itemName : cartItemsPrice) {
            Assert.assertTrue(String.format("Item with price %s is not added to the cart", itemName),
                    doesCollectionContainItem(itemName, itemsList));
        }
    }

    private boolean doesCollectionContainItemPrice(String itemPrice, List<WebElement> itemsList) {
        for (WebElement element : itemsList) {
            System.out.println("--" + element.findElement(By.cssSelector(".product-price > span.amount")).
                    getText());
            if (element.findElement(By.cssSelector(".product-price > span.amount")).
                    getText().equalsIgnoreCase(itemPrice)) {
                return true;
            }
        }

        return false;
    }

}