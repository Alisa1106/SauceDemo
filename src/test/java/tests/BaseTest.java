package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.CartSteps;
import steps.LoginSteps;
import steps.ProductSteps;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    ProductSteps productSteps;
    CartSteps cartSteps;
    LoginSteps loginSteps;

    /**
     * Init test.
     * * This method performed before the test method
     *
     * @param context the context
     */
    @BeforeMethod
    public void InitTest(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        initSteps();
        String variable = "driver";
        log.debug("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }

    /**
     * End test.
     * This method performed after test method regardless of the test result
     */
    @AfterMethod(alwaysRun = true)
    public void endTest() {
        log.debug("Close browser");
        driver.quit();
    }

    /**
     * Init steps.
     */
    public void initSteps() {
        productSteps = new ProductSteps(driver);
        cartSteps = new CartSteps(driver);
        loginSteps = new LoginSteps(driver);
    }
}