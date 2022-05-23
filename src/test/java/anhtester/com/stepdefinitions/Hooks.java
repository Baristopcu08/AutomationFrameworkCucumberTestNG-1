package anhtester.com.stepdefinitions;

import anhtester.com.constants.FrameworkConstants;
import anhtester.com.driver.DriverManager;
import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.helpers.PropertiesHelpers;
import anhtester.com.report.AllureManager;
import anhtester.com.utils.WebUI;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void beforeScenario() {
        //System.out.println("Starting Driver in Hooks: " + DriverManager.getDriver());

    }


    @After
    public void afterScenario(Scenario scenario) {

        //System.out.println("Stop Driver in Hooks: " + DriverManager.getDriver());

        DriverManager.quit();
        WebUI.stopSoftAssertAll();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        //validate if scenario has failed then Screenshot
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot Failed");

            //AllureManager.takeScreenshotStep();
        }
    }

}
