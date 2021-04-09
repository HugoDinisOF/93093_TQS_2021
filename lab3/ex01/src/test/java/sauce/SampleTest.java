package sauce;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {
    WebDriver browser;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hugo Dinis\\Desktop\\2021 uni\\chromedriver\\chromedriver.exe");
        browser = new ChromeDriver();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {browser.close();
    }
    @Test
    public void site_header_is_on_home_page() {
        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));

    }
}