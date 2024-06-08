package abstracted;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        System.out.println(Boolean.getBoolean("use.selenium.grid"));
        if (Boolean.getBoolean("use.selenium.grid"))
            this.driver = this.getRemoteDriver();
        else
            this.driver = this.getLocalDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        String url = "http://localhost:4444/wd/hub";

        if (System.getProperty("browser").equalsIgnoreCase("firefox"))
            capabilities = new FirefoxOptions();

        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }

}