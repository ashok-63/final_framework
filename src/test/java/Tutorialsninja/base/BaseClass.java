package Tutorialsninja.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {

    public static WebDriver driver;

    @BeforeClass
    public void setup() {

        // Detect Jenkins environment
        boolean isJenkins = System.getenv("JENKINS_HOME") != null;

        ChromeOptions options = new ChromeOptions();

        if (isJenkins) {
            // ---------------------------
            // JENKINS HEADLESS CONFIG
            // ---------------------------
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");

            System.out.println("Running in Jenkins → Headless Chrome enabled");
        } else {
            // ---------------------------
            // NORMAL LOCAL EXECUTION
            // ---------------------------
            options.addArguments("--start-maximized");
            System.out.println("Running locally on Windows → Normal Chrome");
        }

        try {
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            System.out.println("ChromeDriver launch failed: " + e.getMessage());
            throw e;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Error during driver.quit(): " + e.getMessage());
        }
    }
}
