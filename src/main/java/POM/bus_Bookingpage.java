package POM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class bus_Bookingpage {

	private WebDriver driver;
	private WebDriverWait wait;

	private final By INITIAL_FROM_FIELD_DIV = By.xpath("//div[contains (text(), 'From')]");
	private final By SOURCE_INPUT_FIELD = By.xpath("//input[@id='srcDest']");
	private final By DESTINATION_INPUT_FIELD = By.xpath("//input[@id='srcDest']");
	private final By datefield = By.xpath("//span[contains (text(), 'Date of Journey')]");
	private final By SEARCH_BUS_BUTTON = By.xpath("//button[text()='Search buses']");
	private final By BUS_LISTINGS = By.xpath("//span[contains(@class, 'subtitle') and contains(text(), 'buses')]");
	private final By AC = By.xpath("//div[@class='container___26accd   ']//div[text()='AC']");
	private final By NonAC = By.xpath("//div[text()='Non-AC']");
	private final By highrated = By.xpath("//div[text()='High rated']");




	public bus_Bookingpage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(05));
	}

	public void searchBuses(String From, String To, String Date) throws InterruptedException {
		// Click on 'From' div to activate input
		wait.until(ExpectedConditions.elementToBeClickable(INITIAL_FROM_FIELD_DIV)).click();

		// Wait for visible From input
		WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SOURCE_INPUT_FIELD));
		fromInput.sendKeys(From);

		// Select dynamic suggestion for From
		By dynamicFromSuggestion = By.xpath("//div[contains(text(),'" + From + "')]");
		wait.until(ExpectedConditions.elementToBeClickable(dynamicFromSuggestion)).click();

		// Handle 'To' field conditionally
		List<WebElement> toFieldList = driver.findElements(SOURCE_INPUT_FIELD);

		if (!toFieldList.isEmpty()) 
		{
			// 'To' input is directly present
			WebElement toInput = wait.until(ExpectedConditions.presenceOfElementLocated(SOURCE_INPUT_FIELD));
			toInput.sendKeys(To);
			Thread.sleep(2000);

			By dynamicToSuggestion = By.xpath("//div[contains(text(),'" + To + "')]");
			wait.until(ExpectedConditions.elementToBeClickable(dynamicToSuggestion)).click();
		} else 
		{
			// Click on 'To' label to activate the input
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains (text(), 'To')]"))).click();

			WebElement toInput = wait.until(ExpectedConditions.presenceOfElementLocated(SOURCE_INPUT_FIELD));
			toInput.sendKeys(To);
			Thread.sleep(2000);

			By dynamicToSuggestion = By.xpath("//div[contains(text(),'" + To + "')]");
			wait.until(ExpectedConditions.elementToBeClickable(dynamicToSuggestion)).click();
		}

		Thread.sleep(1000);

		// Extract day from Date string (format: dd/MM/yyyy)
		String[] dateParts = Date.split("/"); // [0]=dd, [1]=MM, [2]=yyyy
		String day = dateParts[0];

		// Dynamic XPath for the date selection
		By dynamicDate = By.xpath("//span[text()='" + day + "']");

		// Click on date picker and select the date
		wait.until(ExpectedConditions.elementToBeClickable(datefield)).click();
		wait.until(ExpectedConditions.elementToBeClickable(dynamicDate)).click();

		// Click search
		wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUS_BUTTON)).click();
		Thread.sleep(2000);

		// Wait for results
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(AC)).click();
		} catch (Exception e) {
			System.out.println("AC filter not present. Skipping...");
		}

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(NonAC)).click();
		} catch (Exception e) {
			System.out.println("Non-AC filter not present. Skipping...");
		}

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(highrated)).click();
		} catch (Exception e) {
			System.out.println("High-rated filter not present. Skipping...");
		}

		//wait for bus listings at the end
		wait.until(ExpectedConditions.presenceOfElementLocated(BUS_LISTINGS));

	}


	public String getBusCount()
	{    	
		String text = driver.findElement(BUS_LISTINGS).getText();
		String count = text.split(" ")[0];   
		System.out.println(count);
		return count;
	}
}
