package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class LoginPagePom extends LoadableComponent<LoginPagePom> {
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.ID, using = "reg_email")
    private WebElement userName;

    @FindBy(how = How.ID, using = "reg_password")
    private WebElement password;

    @FindBy(how = How.NAME, using = "register")
    private WebElement registerButton;

    @FindBy(how = How.ID, using = "username")
    private WebElement loginName;

    @FindBy(how = How.ID, using = "password")
    private WebElement loginPassword;

    @FindBy(how = How.NAME, using = "login")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "#page-36 > div > div.woocommerce > ul")
    private WebElement errorMessage;

    public LoginPagePom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    @Override
    public void load() {
        driver.get("http://practice.automationtesting.in/my-account/");
        System.out.println("PRINT");
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertEquals("Page is not loaded", "http://practice.automationtesting.in/my-account/",
                driver.getCurrentUrl());
    }

    public void register(String userName, String password) {
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        registerButton.click();
    }

    public void login(String username, String password) throws InterruptedException {
        this.loginName.sendKeys(username);
        this.loginPassword.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful(String username) {
        return isRedirectSuccessful(username);
    }

    public boolean isRedirectSuccessful(String username) {
        boolean result = true;

        String cssSelectorHello = "#page-36 > div > div.woocommerce > " +
                "div > p:nth-child(1) > strong";
        if (driver.findElements(By.cssSelector(cssSelectorHello)).size() == 0) {
            result = false;
        } else {
            if (!driver.findElement(By.cssSelector(cssSelectorHello)).getText().equals(username)) {
                result = false;
            }
        }

        return result;
    }

//    public boolean isWrongPasswordMessageDisplayed() {
//        boolean result = true;
//        String cssSelectorErrormessage = "#page-36 > div > div.woocommerce > ul";
//        if (driver.findElements(By.cssSelector(cssSelectorErrormessage)).size() == 0){
//            result = false;
//        }
//        else{
//            if (driver.findElement(By.cssSelector(cssSelectorErrormessage)).getText().equals("ERROR: The password you entered for the username fdsfd@ff.ff is incorrect. Lost your password?"));
//            result = true;
//        }
//        return result;
//    }

    public boolean isWrongPasswordMessageDisplayed(String errorMessage){
        return this.errorMessage.getText().equalsIgnoreCase(errorMessage);
    }

    public String generateEmail() {
        String result = "";

        Random random = new Random();
        result = "newuser" + random.nextInt();

        return result;
    }
    public boolean isWrongEmailMessageDisplayed() {
        boolean result = true;
        String cssSelectorErrormessage = "#page-36 > div > div.woocommerce > ul";
        if (driver.findElements(By.cssSelector(cssSelectorErrormessage)).size() == 0){
            result = false;
        }
        else{
            if (driver.findElement(By.cssSelector(cssSelectorErrormessage)).getText().equals("Error: A user could not be found with this email address."));
            result = true;
        }
        return result;
    }
}
