package POM.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTestToURL {
    public WebDriver driver;
    private String browser, targetUrl;
    private int implicitWait;
    //precondition
    @BeforeMethod
    public void setupDriverAndOpenTargetUrl() {
        readConfig("src/test/resources/config.properties");
        setupDriver();
        driver.manage().timeouts().implicitlyWait(Duration.from(Duration.ofSeconds(implicitWait)));
        driver.get(targetUrl);
    }
    //post condition
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    private void readConfig(String pathToFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            targetUrl = properties.getProperty("url");
            browser = properties.getProperty("browser");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //public interface WebDriver extends SearchContext
    private WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    private WebDriver setupFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    private WebDriver setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    //to access to variable browser
    private void setupDriver() {
        switch (browser) {
            case "edge":
                driver = setupEdgeDriver();
                break;
            case "firefox":
                driver = setupFireFoxDriver();
                break;
            default:
                driver = setupChromeDriver();
        }
    }
}

