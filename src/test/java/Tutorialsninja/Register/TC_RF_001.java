package Tutorialsninja.Register;

import Tutorialsninja.base.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC_RF_001 extends BaseClass {

    @Test
    public void registerWithFields() {

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Ashok");
        driver.findElement(By.id("input-lastname")).sendKeys("Tester");
        driver.findElement(By.id("input-email")).sendKeys("ashok" + System.currentTimeMillis() + "@mail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
        driver.findElement(By.id("input-password")).sendKeys("test123");
        driver.findElement(By.id("input-confirm")).sendKeys("test123");

        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }
}
