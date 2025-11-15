package Tutorialsninja.Register;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_001 extends BaseClass {

    @Test
    public void registerWithFields() {
        System.out.println("TC_RF_001 - Registration test running...");

        driver.get("https://tutorialsninja.com/demo/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // click My Account (use anchor with title attribute)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='My Account']")));

        // use JS click to be robust in headless
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"a[title='My Account']\").click();");

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();

        // Fill form
        String uniqueEmail = "ashok" + System.currentTimeMillis() + "@example.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys("Ashok");
        driver.findElement(By.id("input-lastname")).sendKeys("Tester");
        driver.findElement(By.id("input-email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("9999999999");
        driver.findElement(By.id("input-password")).sendKeys("Password@123");
        driver.findElement(By.id("input-confirm")).sendKeys("Password@123");

        // Agree and continue
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("input[type='submit'][value='Continue'], input.btn.btn-primary")).click();

        // Verify success
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));
        String heading = driver.findElement(By.cssSelector("#content h1")).getText().trim();
        Assert.assertEquals(heading, "Your Account Has Been Created!", "Registration failed or heading mismatch");
        System.out.println("TC_RF_001 - Registration successful for: " + uniqueEmail);
    }
}
