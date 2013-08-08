
import com.saucelabs.common.SauceOnDemandAuthentication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.junit.Parallelized;

import java.net.URL;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce OnDemand in parallel.
 *
 * @author Ross Rowe
 */
@RunWith(Parallelized.class)
public class WebDriverParallelTest {

    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("blueblud", "fbd44937-0e1d-4db2-bcaf-4a9264ee78a8");

    private String browser;
    private String os;
    private String version;

    public WebDriverParallelTest(String os, String version, String browser) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static LinkedList browsersStrings() throws Exception {
        LinkedList browsers = new LinkedList();
        //browsers.add(new String[]{Platform.WIN8.toString(), "17", "firefox"});
		browsers.add(new String[]{"Windows 7", "9", "iexplore"});
		//browsers.add(new String[]{Platform.MAC.toString(), "5", "safari"});
		//browsers.add(new String[]{Platform.XP.toString(), "27", "googlechrome"});
		//browsers.add(new String[]{Platform.XP.toString(), "6", "safari"});
        return browsers;
    }

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, "Windows 7");//Platform.valueOf(os)
		//Jai modified
		this.driver = new RemoteWebDriver(
                new URL("http://blueblud:fbd44937-0e1d-4db2-bcaf-4a9264ee78a8@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
		driver.get("https://qa.healthentic.com/securityadmin/login.jsp");
		
		
        /*this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
				*/
    }
//Jai commented and added
/*  
  @Test
    public void webDriver() throws Exception {
        driver.get("http://www.amazon.com/");
        assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }
*/

	@Test
    public void testLoginFailsWithInvalidCredentials() throws Exception {
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_username]")).sendKeys("MMGG");
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_password]")).sendKeys("MMGG");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
		assertEquals("Healthentic - Login", driver.getTitle());
        //assertNotNull("Text not found", driver.findElement(By.id("message")));
		//assertEquals("Site Administration | Healthentic", driver.getTitle());
    }
	
	@Test
    public void testLoginSuccessWithValidCredentials() throws Exception {
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input.textboxshadow[name=j_password]")).sendKeys("Health0!");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
		assertEquals("Site Administration | Healthentic", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
