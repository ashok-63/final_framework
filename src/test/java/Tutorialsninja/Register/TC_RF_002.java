package Tutorialsninja.Register;

import Tutorialsninja.base.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC_RF_002 extends BaseClass {

    @Test
    public void missingFields() {

        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }
}
