package Base_Class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./software/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://groupy.live/travelplanbooker.com/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
