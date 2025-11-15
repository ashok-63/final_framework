package Tutorialsninja.Register;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_003 extends BaseClass {

    @Test
    public void registerThenLogin() {
        System.out.println("TC_RF_003 - Register -> Logout -> Login test running...");

        driver.get("https://tutorialsninja.com/demo/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"a[title='My Account']\").click();");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();

        String email = "ashok" + System.currentTimeMillis() + "@example.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys("Ashok");
        driver.findElement(By.id("input-lastname")).sendKeys("LoginTest");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys("9999999999");
        driver.findElement(By.id("input-password")).sendKeys("Password@123");
        driver.findElement(By.id("input-confirm")).sendKeys("Password@123");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("input[type='submit'][value='Continue'], input.btn.btn-primary")).click();

        // Confirm registration success then logout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#content h1")).getText().trim(),
                "Your Account Has Been Created!");

        // Logout
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"a[title='My Account']\").click();");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout"))).click();

        // Now Login
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"a[title='My Account']\").click();");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email"))).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys("Password@123");
        driver.findElement(By.cssSelector("input[type='submit'][value='Login'], input.btn.btn-primary")).click();

        // Validate login by checking presence of My Account -> Logout or heading "My Account"
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"a[title='My Account']\").click();");
        boolean logoutVisible = wait.until(d -> d.findElements(By.linkText("Logout")).size() > 0);
        Assert.assertTrue(logoutVisible, "Login failed - Logout not visible after login.");
        System.out.println("TC_RF_003 - Login verified for: " + email);
    }
}
