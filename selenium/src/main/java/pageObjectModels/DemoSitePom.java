package pageObjectModels;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class DemoSitePom extends LoadableComponent<DemoSitePom> {
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.CSS, using = "#basicBootstrapForm > div:nth-child(1) > div:nth-child(2) > input")
    private WebElement firstName;

    @FindBy(how = How.CSS, using = "#basicBootstrapForm > div:nth-child(1) > div:nth-child(3) > input")
    private WebElement lastName;

    @FindBy(how = How.CSS, using = "#eid > input")
    private WebElement email;

    @FindBy(how = How.CSS, using = "#basicBootstrapForm > div:nth-child(4) > div > input")
    private WebElement phone;

    @FindBy(how = How.CSS, using = "#basicBootstrapForm > div:nth-child(5) > div > label:nth-child(1)")
    private WebElement male;

    @FindBy(how = How.CSS, using = "#basicBootstrapForm > div:nth-child(5) > div > label:nth-child(2)")
    private WebElement female;

    @FindBy(how = How.ID, using = "countries")
    private WebElement country;

    @FindBy(how = How.ID, using = "yearbox")
    private WebElement year;

    @FindBy(how = How.CSS, using = "#basicBootstrapForm > div:nth-child(11) > div:nth-child(3) > select")
    private WebElement month;

    @FindBy(how = How.ID, using = "daybox")
    private WebElement day;

    @FindBy(how = How.ID, using = "firstpassword")
    private WebElement firstPassword;

    @FindBy(how = How.ID, using = "secondpassword")
    private WebElement secondPassword;

    @FindBy(how = How.ID, using = "submitbtn")
    private WebElement submitButton;

    public DemoSitePom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void load() {
        driver.get("http://demo.automationtesting.in/Register.html");
    }

    public void isLoaded() throws Error {

    }

    public void fillOutUserData(Map<String, String> userData) {
        firstName.sendKeys(userData.get("FirstName"));
        lastName.sendKeys(userData.get("LastName"));
        email.sendKeys(userData.get("Email"));
        phone.sendKeys(userData.get("Phone"));

        if (userData.get("Gender").equalsIgnoreCase("male")) {
            male.click();
        } else if (userData.get("Gender").equalsIgnoreCase("female")) {
            female.click();
        } else
            Assert.fail("Please specify valid gender.");

        Select selectCountry = new Select(country);
        selectCountry.selectByValue(userData.get("Country"));

        Select selectYear = new Select(year);
        selectYear.selectByValue(userData.get("Year"));

        Select selectMonth = new Select(month);
        selectMonth.selectByValue(userData.get("Month"));

        Select selectDay = new Select(day);
        selectDay.selectByValue(userData.get("Day"));

        firstPassword.sendKeys(userData.get("Password"));
        secondPassword.sendKeys(userData.get("Password"));
    }

    public void submit() throws InterruptedException {
        submitButton.click();
    }

    public void isRegistrationSuccessful() throws InterruptedException {
        wait.until(ExpectedConditions.urlContains("https:://demo.automationtesting.in/WebTable.html"));
        Assert.assertEquals("Page is not loaded", "https:://demo.automationtesting.in/WebTable.html", driver.getCurrentUrl());
        driver.quit();
    }
}
