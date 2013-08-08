import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.Assert.assertEquals;


public class WebDriverTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "17");
        capabilities.setCapability("platform", Platform.XP);
        this.driver = new RemoteWebDriver(
                new URL("http://blueblud:fbd44937-0e1d-4db2-bcaf-4a9264ee78a8@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
		driver.get("https://qa.healthentic.com/securityadmin/login.jsp");
    }
/*
    @Test
    public void webDriver() throws Exception {
        assertEquals("Healthentic - Login", driver.getTitle());
    }

	@Test
    public void testLoginFailsWithInvalidCredentials() throws Exception {
        driver.findElement(By.cssSelector("INPUT#j_username.textboxshadow")).sendKeys("MMGG");
		//driver.findElement(By.cssSelector("input.textboxshadow[name=j_username]")).sendKeys("MMGG");
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_password]")).sendKeys("MMGG");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
		assertEquals("Healthentic - Login", driver.getTitle());
        //assertNotNull("Text not found", driver.findElement(By.id("message")));
		//assertEquals("Site Administration | Healthentic", driver.getTitle());
    }
*/	
	@Test
    public void testLoginSuccessWithValidCredentials() throws Exception {
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_password]")).sendKeys("Health0!");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
		//assertEquals("Site Administration | Healthentic", driver.getTitle());
		driver.findElement(By.linkText("Dental Action Report Usage")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
