package blazedemo;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BlazeDemoSteps {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Given("a browser I just opened")
    public void aBrowserIJustOpened() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Documents\\chrome_driver\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        driver.manage().window().setSize(new Dimension(1382, 744));
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver.get(url);
    }

    @And("I choose {string} as the departure city")
    public void iChooseAsTheDepartureCity(String city) {
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = '"+city+"']")).click();
        }
        driver.findElement(By.name("fromPort")).click();
    }

    @And("I choose {string} as the destination city")
    public void iChooseAsTheDestinationCity(String city) {
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = '"+city+"']")).click();
        }
        driver.findElement(By.name("toPort")).click();
    }

    @And("I click in Find flights")
    public void iClickInFindFlights() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the {int} th flight")
    public void iChooseTheThFlight(int n) {
        driver.findElement(By.cssSelector("tr:nth-child("+n+") .btn")).click();
    }

    @And("I submit the form to purchase the flight")
    public void iSubmitTheFormToPurchaseTheFlight() {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("João Silva");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("123 Main St.");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Aveiro");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("2138789989890");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("Then the page title should start with {string}")
    public void thenThePageTitleShouldStartWith(String title) {
        assertThat(driver.getTitle(), is(title));
    }
    @After
    public void closebrowser(){
        driver.close();
    }
}
