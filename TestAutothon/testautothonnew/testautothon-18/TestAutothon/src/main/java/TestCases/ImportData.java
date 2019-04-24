package TestCases;

import Base.Device;
import Base.TestBaseManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class ImportData {

    private static final String PATH_OF_FILE = "testData/movieList";

    private static List<String> lines = Collections.emptyList();

    public static ConcurrentMap<String, String> moviesUrl = new ConcurrentHashMap<>();

    public static void initData() {
        readFile();
        lines.parallelStream().forEach(ImportData::getWikiUrl);
    }

    private static void readFile() {
        try {
            lines = Files.readAllLines(Paths.get(ImportData.PATH_OF_FILE), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getWikiUrl(String movieName) {
        WebDriver driver = null;
        try {
            driver = Device.REMOTECHROME.setDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.google.co.in/");
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input")).sendKeys("movie:" + movieName + " wikipedia page");
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[3]/center/input[1]")).sendKeys(Keys.ENTER);
        WebElement webElement = driver.findElements(By.xpath("//a[starts-with(@href,'https://en.wikipedia.org/wiki')]")).get(0);
        if (webElement != null) {
            webElement.click();
            if (driver.getTitle().contains(movieName)) {
                moviesUrl.put(movieName.replace("\\s", ""), driver.getCurrentUrl());
            }
        } else {
            moviesUrl.put(movieName.replace("\\s", ""), "No url found");
        }
        driver.quit();
    }

}
