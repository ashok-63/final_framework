package Tutorialsninja.Register;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_004 extends BaseClass {

    @Test
    public void addFirstProductToWishlist() {
        System.out.println("TC_RF_004 - Add to wishlist test running...");

        driver.get("https://tutorialsninja.com/demo/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Go to Phones & PDAs category (example)
        WebElement phonesLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones & PDAs")));
        phonesLink.click();

        // Wait for product list then click first "Add to Wish List" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-thumb")));

        // Many demo pages use wishlist.add in onclick — click the first such button
        WebElement wishBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@onclick,'wishlist.add') or contains(@title,'Wish')])[1]")));

        // Use JS to click to avoid headless issues
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishBtn);

        // Wait for success alert
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        String text = alert.getText();
        Assert.assertTrue(text.toLowerCase().contains("added to your wishlist") || text.toLowerCase().contains("success"),
                "Expected wishlist success message, got: " + text);

        System.out.println("TC_RF_004 - Product added to wishlist successfully.");
    }
}
