package tqs;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorStepdefs {
    private Calculator calc;
    @Given("a calculator I just turned on")
    public void aCalculatorIJustTurnedOn() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("+");
    }

    @Then("the result is {int}")
    public void theResultIs(double arg0) {
        assertEquals(arg0,calc.value());
    }

    @When("I subtract {int} to {int}")
    public void iSubtractTo(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("-");
    }
}
