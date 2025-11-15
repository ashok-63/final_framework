package Tutorialsninja.Register;

import Tutorialsninja.base.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC_RF_003 extends BaseClass {

    @Test
    public void invalidEmail() {

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-email")).sendKeys("wrong_email_format");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }
}
