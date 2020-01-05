import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPagePom;

public class LoginPage {
    WebDriver driver;
    @Test
    public void logInPositive() throws InterruptedException {
        driver = Common.getDriver();
        LoginPagePom loginPage = new LoginPagePom(driver);

        loginPage.load();
        loginPage.isLoaded();

        String username = loginPage.generateEmail();
        String password = "Qwertmnbvc123697845";
        loginPage.register(username + "@gmail.com", password);
        driver.quit();

        driver = Common.getDriver();
        //LoginPagePom loginPage = new LoginPagePom(driver);

        loginPage.load();
        loginPage.isLoaded();

        loginPage.login(username, password);
        Assert.assertTrue("Log in is not successful",
                loginPage.isLoginSuccessful(username));
        driver.quit();
    }

    @Test
    public void logInWrongPasswordFirefox() throws InterruptedException {
        driver = Common.getDriver();
        LoginPagePom loginPage = new LoginPagePom(driver);

        loginPage.load();
        loginPage.isLoaded();

        String username = loginPage.generateEmail();
        String password = "Qwertmnbvc123697845";
        loginPage.register(username + "@gmail.com", password);
        driver.quit();

        driver = Common.getDriver();
        loginPage = new LoginPagePom(driver);

        loginPage.load();
        loginPage.isLoaded();

        String wrongPassword = "Qwertmnbvc12369784500";
        loginPage.login(username + "@gmail.com", wrongPassword);
        Assert.assertTrue("Error message for wrong password is not displayed",
                loginPage.isWrongPasswordMessageDisplayed(String.format("The password you entered for the username %s is incorrect. Lost your password?", username)));
        driver.quit();

    }

//    @Test
//    public void logInWrongPasswordChrome() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver",
//                "C:\\Users\\sherfe.metkova\\Desktop\\automationTraining\\selenium\\chromedriver.exe");
//        driver = new ChromeDriver();
//        WebElement element =
//                driver.findElement(By.id("selectLoad"));
//        element.getAttribute("value");
//        LoginPagePom loginPage = new LoginPagePom(driver);
//
//        loginPage.load();
//        loginPage.isLoaded();
//
//        String username = loginPage.generateEmail();
//        String password = "Qwertmnbvc123697845";
//        loginPage.register(username + "@gmail.com", password);
//        driver.quit();
//
//        driver = Common.getDriver();
//        loginPage = new LoginPagePom(driver);
//
//        loginPage.load();
//        loginPage.isLoaded();
//
//        String wrongPassword = "Qwertmnbvc12369784500";
//        loginPage.login(username + "@gmail.com", wrongPassword);
//        Assert.assertTrue("Error message for wrong password is not displayed",
//                loginPage.isWrongPasswordMessageDisplayed("ERROR: The password you entered for the username fdsfd@ff.ff is incorrect. Lost your password?"));
//        driver.quit();
//
//    }

    @Test
    public void logInWrongEmail() throws InterruptedException {
        driver = Common.getDriver();
        LoginPagePom loginPage = new LoginPagePom(driver);

        loginPage.load();
        loginPage.isLoaded();

        String username = loginPage.generateEmail();
        String password = "Qwertmnbvc123697845";
        loginPage.register(username + "@gmail.com", password);
        driver.quit();

        driver = Common.getDriver();
        loginPage = new LoginPagePom(driver);

        loginPage.load();
        loginPage.isLoaded();

        loginPage.login(username + "as@gmail.com", password);
        Assert.assertTrue("Error message for wrong password is not displayed",
                loginPage.isWrongEmailMessageDisplayed());
        driver.quit();

}
    @After
    public void tearDown(){
        driver.close();
    }
}