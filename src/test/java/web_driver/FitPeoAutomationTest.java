package web_driver;


	import org.junit.jupiter.api.*;
	import org.openqa.selenium.*;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class FitPeoAutomationTest {

	    private WebDriver driver;

	    @BeforeAll
	    public static void setupClass() {
	        WebDriverManager.chromedriver().setup();
	    }

	    @BeforeEach
	    public void setupTest() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void testRevenueCalculator() {
	        // Navigate to FitPeo Homepage
	        driver.get("https://www.fitpeo.com"); // Replace with the actual URL
	        Assertions.assertTrue(driver.getTitle().contains("FitPeo")); // Update the title as needed

	        // Navigate to Revenue Calculator Page
	        WebElement revenueCalculatorLink = driver.findElement(By.linkText("Revenue Calculator")); // Update the locator as needed
	        revenueCalculatorLink.click();
	        Assertions.assertTrue(driver.getCurrentUrl().contains("revenue-calculator")); // Replace with the actual URL part

	        // Scroll down to the Slider section
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebElement slider = driver.findElement(By.id("slider")); // Update the locator as needed
	        js.executeScript("arguments[0].scrollIntoView(true);", slider);
	        Assertions.assertTrue(slider.isDisplayed());

	        // Adjust the Slider to 820
	        Actions move = new Actions(driver);
	        move.dragAndDropBy(slider, 820, 0).perform(); // Adjust to set the slider value
	        WebElement sliderValue = driver.findElement(By.id("sliderValue")); // Update the locator as needed
	        Assertions.assertEquals("820", sliderValue.getAttribute("value"));

	        // Update the Text Field to 560
	        WebElement textField = driver.findElement(By.id("textField")); // Update the locator as needed
	        textField.clear();
	        textField.sendKeys("560");
	        Assertions.assertEquals("560", slider.getAttribute("value"));

	        // Select CPT Codes
	        String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
	        for (String code : cptCodes) {
	            WebElement checkbox = driver.findElement(By.id(code)); // Update the locator as needed
	            if (!checkbox.isSelected()) {
	                checkbox.click();
	            }
	            Assertions.assertTrue(checkbox.isSelected());
	        }

	        // Validate Total Recurring Reimbursement
	        WebElement reimbursementHeader = driver.findElement(By.id("totalReimbursement")); // Update the locator as needed
	        String expectedReimbursement = "$110700";
	        Assertions.assertTrue(reimbursementHeader.getText().contains(expectedReimbursement));
	    }

	    @AfterEach
	    public void teardown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}



