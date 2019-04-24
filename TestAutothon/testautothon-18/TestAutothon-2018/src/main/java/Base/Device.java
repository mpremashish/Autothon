package Base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public enum Device {
	MOBILESAFARI,MOBILECHROME,WEBCHROME,WEBFIREFOX,WEBSAFARI;

	public WebDriver setDriver() throws MalformedURLException {
		switch (this) {
		case MOBILECHROME:
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("deviceName", "device");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
			capabilities.setCapability("noReset", true);
			return new RemoteWebDriver(new URL(System.getProperty("appiumUrl","http://127.0.0.1:4723/wd/hub")), capabilities);
		case MOBILESAFARI:
			DesiredCapabilities capabilities1 = new DesiredCapabilities();
			capabilities1.setCapability("browserName", "Safari");
			capabilities1.setCapability("deviceName", "iPhone Simulator");
			capabilities1.setCapability("platformName", "iOS");
			capabilities1.setCapability("bundleId", "com.apple.mobilesafari");
			capabilities1.setCapability("automationName", "XCUITest");
			capabilities1.setCapability("noReset", true);
			return new RemoteWebDriver(new URL(System.getProperty("appiumUrl","http://127.0.0.1:4723/wd/hub")), capabilities1);
		case WEBCHROME:
			File chromeDriver = new File("./src/main/resources/drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-extensions");
			options.addArguments("disable-infobars");
			// options.addArguments("auth-server-whitelist=" +
			// StringUtils.join(whiteListUrls, "*,"));
			return new ChromeDriver(options);
		case WEBFIREFOX:
			File firefoxDriver = new File("./src/main/resources/drivers/geckodriver");
			System.setProperty("webdriver.gecko.driver", firefoxDriver.getAbsolutePath());
			FirefoxOptions options1 = new FirefoxOptions();
			return new FirefoxDriver(options1);
		case WEBSAFARI:
			File safariDriver = new File("/src/main/resources/drivers/chromedriver");
			System.setProperty("webdriver.safari.driver", safariDriver.getAbsolutePath());
			SafariOptions options2 = new SafariOptions();
			return new SafariDriver(options2);
		default:
			return null;
		}

	}

	public static Device fromString(String browserArg) {
		switch (browserArg.toLowerCase()) {
		case "webchrome":
			return WEBCHROME;
		case "webfirefox":
			return WEBFIREFOX;	
		case "websafari":
			return WEBSAFARI;		
		case "mobilesafari":
			return MOBILESAFARI;
		case "mobilechrome":
			return MOBILECHROME;	
		default:
			return null;
		}
	}
}
