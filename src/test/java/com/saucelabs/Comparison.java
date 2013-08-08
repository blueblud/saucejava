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


public class Comparison {

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

  @Test
  public void testLogin() throws Exception {
	driver.findElement(By.cssSelector("input.textboxshadow[name=j_username]")).sendKeys("admin");
    driver.findElement(By.cssSelector("input.textboxshadow[name=j_password]")).sendKeys("Health0!");
    driver.findElement(By.cssSelector("input[@value='Log In']")).click();
	assertEquals("Site Administration | Healthentic", driver.getTitle());
	/*
	driver.findElement(By.cssSelector("input:link[href=Dental Action Report Usage]")).click();
    driver.findElement(By.cssSelector("input.dropwdth[name=cust_id]")).sendKeys("Delta Dental of Illinois");
    driver.findElement(By.cssSelector("input.dropwdth[name=history]")).sendKeys("Last Year");
    driver.findElement(By.id("submit_280008558")).click();
    driver.findElement(By.id("anchor_270819221")).click();
    driver.findElement(By.linkText("Site Administration")).click();
    driver.findElement(By.linkText("Sign Out")).click();
	*/
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    }
  }