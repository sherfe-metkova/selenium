package cucumberTestFiles;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPagePom;

public class LoginPageStepDefinitions {
    private Context context;
    private LoginPagePom loginPage;
    WebDriver driver = Common.getDriver();

    public LoginPageStepDefinitions(Context context) {
        this.context = context;
        loginPage = new LoginPagePom(driver);
    }

    @Given("there is a user with valid email")
    public void thereIsAUserWithValidEmail() {
        loginPage.load();
        loginPage.isLoaded();
        context.username = loginPage.generateEmail();
    }

    @And("the user is making registration with password WETYJ!@#{int}")
    public void theUserIsMakingRegistrationWithPasswordWETYJ(String password) {
        context.password = password;
    }

    @When("the user presses Register button")
    public void theUserPressesRegisterButton() {
        loginPage.register(context.username + "@gmail.com", context.password);
    }

    @Then("the user is successfully registered")
    public void theUserIsSuccessfullyRegistered() {
        loginPage.isRedirectSuccessful(context.username);
    }
}
