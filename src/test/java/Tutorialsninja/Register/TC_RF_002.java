package Tutorialsninja.Register;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_002 extends BaseClass {

    @Test
    public void registerWithoutAgreeShowsWarning() {
        System.out.println("TC_RF_002 - Negative registration test (no agree) running...");

        driver.get("https://tutorialsninja.com/demo/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"a[title='My Account']\").click();");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();

        // Fill required fields but DO NOT click agree
        String uniqueEmail = "ashok" + System.currentTimeMillis() + "@example.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys("Ashok");
        driver.findElement(By.id("input-lastname")).sendKeys("Negative");
        driver.findElement(By.id("input-email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("9999999999");
        driver.findElement(By.id("input-password")).sendKeys("Password@123");
        driver.findElement(By.id("input-confirm")).sendKeys("Password@123");

        driver.findElement(By.cssSelector("input[type='submit'][value='Continue'], input.btn.btn-primary")).click();

        // Expect an error or stay on the same page with warning - check for alert-danger or warning text
        boolean warningPresent = wait.withTimeout(Duration.ofSeconds(5))
                .until(d -> d.findElements(By.cssSelector(".alert-danger, .text-danger")).size() > 0);

        Assert.assertTrue(warningPresent, "Expected a validation/warning message when agreement is not checked.");
        System.out.println("TC_RF_002 - Warning displayed as expected when agreement not checked.");
    }
}
