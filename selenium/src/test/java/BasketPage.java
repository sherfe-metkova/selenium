import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasketPage {
    List<String> cartItems = new ArrayList<String>();
    List<String> cartItemsPrice = new ArrayList<String>();
    WebDriver driver = Common.getDriver();

    @Test
    public void checkBasketPage() throws InterruptedException {
        //LoginForm.registerUser(driver, LoginForm.generateEmail());
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //click shop
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("#menu-item-40 > a"))).click();


        //scroll down the page
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,400)");

        //add items to cart
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                ("#content > ul > li:nth-child(2) > .button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                ("#content > ul > li:nth-child(3) > .button"))).click();

        //click cart icon on top right
        driver.findElement(By.id("wpmenucartli")).click();

        //wait for products in cart to show
//        wait.until(ExpectedConditions.presenceOfElementLocated
//                (By.cssSelector("#page-34 > div > div > form > table > tbody > tr.cart_item")));
//        int size = driver.findElements(By.cssSelector
//                ("#page-34 > div > div > form > table > tbody > tr.cart_item")).size();
//        List<WebElement> tableRows = driver.findElements(By.cssSelector
//                ("#page-34 > div > div > form > table > tbody > tr.cart_item"));
//        System.out.println(size);
//
//        //calculate items' total price
//        int sum;
//        for(WebElement element : tableRows) {
//        System.out.println(driver.findElement(By.cssSelector(".product-price > span.amount")).
//                getText());

        WebElement table = driver.findElement(By.xpath("//*[@id=\"page-34\"]/div/div[1]/form/table/tbody"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<WebElement> columns;

        for (int i = 0; i < rows.size(); i++) {
            columns = rows.get(i).findElements(By.tagName("th"));
            System.out.println("Number of columns" + columns.size());
            for (int j = 0; j < columns.size(); j++) {
                System.out.println(columns.get(j).getText());
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}