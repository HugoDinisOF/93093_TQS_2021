import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SeleniumJupiter.class)
class ChromeJupiterTest {

    @Test
    void testWithOneChrome(ChromeDriver chromeDriver) {
        // Use Chrome in this test
        chromeDriver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(chromeDriver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }
    @Test
    void testPhantomJS(PhantomJSDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getPageSource(), notNullValue());
    }
}
