package TestCases;

import Base.Device;
import Base.DriverThreadLocal;
import model.MovieInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WikiGuiTestCase extends WikiTestCase {

    // @Factory(dataProvider = "movieInfo")
    public WikiGuiTestCase(String movieName, String wikiUrl) {
        this.moviesInfo = new MovieInfo(movieName, wikiUrl);
        moviesInfo.setDevice(Device.REMOTECHROME);
        moviesInfo.setMode("GUI");
    }

    @Override
    public void extractDataFromImdb(String movie, String url) {
        if (!url.equalsIgnoreCase("No url found")) {
            WebDriver driver = null;
            try {
                DriverThreadLocal.setDriver(moviesInfo.getDevice().setDriver());
                driver = DriverThreadLocal.getDriver();
                driver = moviesInfo.getDevice().setDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
            WebElement externalLink = driver.findElement(By.xpath("//*[@id='External_links']"));
            focusElement(driver, externalLink);
            externalLink.click();
            WebElement imdbPage = driver.findElement(By.xpath("//a[text() = 'IMDb']//preceding-sibling::a[1]"));
            focusElement(driver, imdbPage);
            imdbPage.click();
            List<WebElement> directorElements;
            directorElements = driver.findElements(By.xpath("//*[contains (text(),'Director')]//following-sibling::*"));
            List<String> directorNameResult = new ArrayList<>();
            for (WebElement directorElement : directorElements) {
                if (StringUtils.isNotEmpty(directorElement.getText())) {
                    directorNameResult.add(directorElement.getText());
                }
            }
            moviesInfo.setImdbUrl(driver.getCurrentUrl());
            moviesInfo.setImdbDirectorName(directorNameResult.toString());
        } else {
            moviesInfo.setImdbDirectorName("Not Available");
        }
    }

    @Override
    public void extractDataFromWiki(String movie, String wikiUrl) {
        if (!wikiUrl.equalsIgnoreCase("No url found")) {
            WebDriver driver = DriverThreadLocal.getDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(wikiUrl);
            List<WebElement> directorNames = driver.findElements(By.xpath("//*[contains(text(),'Directed by')]//following-sibling::td/a"));
            String[] directorNameResult = new String[directorNames.size()];
            for (int i = 0; i < directorNames.size(); i++) {
                directorNameResult[i] = directorNames.get(i).getText();
            }
            moviesInfo.setMovieName(movie);
            moviesInfo.setWikiDirectorName(Arrays.toString(directorNameResult));
        } else {
            moviesInfo.setWikiDirectorName("Not Available");
            moviesInfo.setWikiUrl("IMDb Movie URL Not Found");
        }
    }

    @Override
    @Test
    public void testCompareDirectorNames() {
        String movieName = moviesInfo.getMovieName();
        String wikiUrl = moviesInfo.getWikiUrl();
        extractDataFromImdb(movieName, wikiUrl);
        extractDataFromWiki(movieName, wikiUrl);
        System.out.println(moviesInfo);
        Assert.assertEquals(moviesInfo.getImdbDirectorName(), moviesInfo.getWikiDirectorName());
    }


    private void focusElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void click(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


}
