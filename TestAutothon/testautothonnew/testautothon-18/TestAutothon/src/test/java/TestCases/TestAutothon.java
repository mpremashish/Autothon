package TestCases;

import java.util.function.Predicate;

import model.JsonPlaceHolder;
import Listener.Retry;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Device;
import Base.ObjectUtils;
import Base.TestCase;

public class TestAutothon extends TestCase {

    private static Logger log = Logger.getLogger(Class.class.getName());
    ObjectUtils loginPage;
    ObjectUtils yourStoriesPage;
    ObjectUtils publishStoryPage;
    ObjectUtils navBarPage;
    Predicate<WebDriver> isAlertPresent = (d) -> {
        d.switchTo().alert();
        return true;
    };

    public TestAutothon(Device thread) {
        super(thread);
    }

    @DataProvider(name = "testDataFactory")
    public Object[][] testDataFactory() {
        return new Object[][]{{jsonStack.pop()}, {jsonStack.pop()}};
    }

    @BeforeClass
    public void testSetUp() {
        try {
            setDriver();
            setCredentials();
            loginPage = new ObjectUtils(driver, "loginpage");
            ObjectUtils yourStoriesPg = new ObjectUtils(driver, "label");
            setTimeout(5);
            driver.get("https://13.251.157.128/admin");
            WebElement loginPageElement = loginPage.getElement("username");
            setTimeout(4);
            loginPageElement.sendKeys(popUserMap());
            loginPage.getElement("password").sendKeys("Testing123");
            loginPage.getElement("loginbtn").click();
            //Awaitility.await("waiting for a pop up to appear").atMost(5, TimeUnit.SECONDS).until(() -> loginPage.getElement("loginbtn").getText().equals("loginNow"));
            // Awaitility.await().until(()->isAlertPresent.test(driver));
        } catch (Exception e) {
            log.error(e.getMessage().toString(), e);
        }
    }

    @Test(dataProvider = "testDataFactory", retryAnalyzer = Retry.class)
    public void testThread(JsonPlaceHolder jsonObject) throws InterruptedException {
        try {

            log.info("running test for thread : " + thread + " with data : " + jsonObject.title);
            setTimeout(4);
            yourStoriesPage = new ObjectUtils(driver, "yourstories");
            yourStoriesPage.getElement("newstorybtn").click();
            setTimeout(4);
            publishStoryPage = new ObjectUtils(driver, "publishstory");
            publishStoryPage.getElement("title").sendKeys(jsonObject.UserId + thread.toString() + jsonObject.title);
            setTimeout(4);
            WebElement titleTxt = publishStoryPage.getElement("title");
            titleTxt.sendKeys(jsonObject.UserId + " " + thread.toString() + " " + jsonObject.id + jsonObject.title);
            setTimeout(4);
            titleTxt.sendKeys(Keys.TAB);
            publishStoryPage.getElement("description").sendKeys(jsonObject.body);
            setTimeout(4);
            publishStoryPage.getElement("publishmenu").click();
            publishStoryPage.getElement("publishbutton").click();

        } catch (Exception e) {
            log.error(e.getMessage().toString(), e);
        }
    }

    @AfterClass
    public void tearDown() {
        log.info("quiting browser..for thread" + thread);
        navBarPage = new ObjectUtils(driver, "navbar");
        navBarPage.getElement("profilemenu").click();
        navBarPage.getElement("signout").click();
        driver.quit();
    }

}
