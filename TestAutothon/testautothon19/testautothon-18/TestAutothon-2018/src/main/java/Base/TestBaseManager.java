package Base;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestBaseManager {

    private static org.apache.log4j.Logger log = Logger.getLogger(TestBaseManager.class.getName());
    public WebDriver driver;
    public Device thread;
    public JavascriptExecutor jsx = ((JavascriptExecutor) getDriver());

    TestBaseManager(Device threadName) {
        this.thread = threadName;
    }

    public WebDriver getDriver() {
        this.driver = DriverThreadLocal.getDriver();
        return driver;
    }

    public void setDriver(Device threadName) {
        try {
            thread = threadName;
            DriverThreadLocal.setDriver(thread.setDriver());
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void setDriver() {
        try {
            DriverThreadLocal.setDriver(thread.setDriver());
            this.driver = DriverThreadLocal.getDriver();
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void resetDriver() {
        this.driver = null;
    }

}
