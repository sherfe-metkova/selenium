package stepDefinitions;

import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjectModels.DemoSitePom;

import java.util.List;
import java.util.Map;

public class DemoSiteStepDefinitions {

    DemoSitePom demoSite;

    public DemoSiteStepDefinitions(){
        WebDriver driver = Common.getDriver();
        demoSite = new DemoSitePom(driver);
    }

    @Given("there is a user on the registration page")
    public void thereIsAUserOnTheRegistrationPage() {
        demoSite.load();
    }

    @And("the user selects the following data")
    public void theUserSelectsTheFollowingData(DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps();
        demoSite.fillOutUserData(userData.get(0));
    }

    @When("the user presses button Submit")
    public void theUserPressesButtonSubmit() throws InterruptedException {
        demoSite.submit();
    }

    @Then("the user is registered for the demo site")
    public void theUserIsRegisteredForTheDemoSite() throws InterruptedException {
        demoSite.isRegistrationSuccessful();
    }
}
