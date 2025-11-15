package Tutorialsninja.Register;

import static org.testng.Assert.assertTrue;

import java.awt.dnd.DragGestureEvent;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_001 {
	WebDriver driver ;
	@Test
	public void registerWithFields() {

		String url = "https://tutorialsninja.com/demo";
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);

		driver.findElement(By.xpath("//span[text()='My Account']")).click();

		driver.findElement(By.linkText("Register")).click();

		// input-firstname
		driver.findElement(By.id("input-firstname")).sendKeys("Hello");
		// input-lastname
		driver.findElement(By.id("input-lastname")).sendKeys("World");

		// input-email
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());

		// input-telephone
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");

		// input-password
		driver.findElement(By.id("input-password")).sendKeys("Hello@123");
		// input-confirm
		driver.findElement(By.id("input-confirm")).sendKeys("Hello@123");

		driver.findElement(By.name("agree")).click();

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

		assertTrue(driver.findElement(By.id("content")).isDisplayed());

		String actstr = "Your Account Has Been Created!";
		String actualText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actstr, actualText, "SuccessFuly Registered");

		String proDetails1 = "Congratulations! Your new account has been successfully created!";

		String proDetails2 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String proDetails3 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String proDetails4 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";

		String expPropDetails = driver.findElement(By.id("content")).getText();

		Assert.assertTrue(expPropDetails.contains(proDetails1));
		Assert.assertTrue(expPropDetails.contains(proDetails2));
		Assert.assertTrue(expPropDetails.contains(proDetails3));
		Assert.assertTrue(expPropDetails.contains(proDetails4));

		driver.findElement(By.xpath("//a[text()='Continue']")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	}

	public String generateNewEmail() {

		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";

	}
	
	@AfterMethod()
	public void tearDown()
	{
		driver.quit();
		
	}

}
