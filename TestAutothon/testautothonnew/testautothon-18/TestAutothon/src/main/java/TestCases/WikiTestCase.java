package TestCases;

import Base.DriverThreadLocal;
import Reporter.HtmlReporter;
import Reporter.TestReporter;
import model.MovieInfo;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class WikiTestCase {
    public static HtmlReporter report = null;
    MovieInfo moviesInfo;

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        report = new HtmlReporter(context.getCurrentXmlTest().getName());
        report.setTestSuiteHeader("Test Suite: " + context.getCurrentXmlTest().getName());
        report.addStepRow("MovieInfo##Movie Name##wikiUrl##Wiki Director Name##imdb Director Name##imdb Url##Mode##Device", "warn", false);
    }

    /*@DataProvider(name = "movieInfo", parallel = true)
    public static Iterator<Object[]> moviesNames(ITestContext testContext) {
        if (testContext != null)
            System.getProperties().putAll(testContext.getCurrentXmlTest().getAllParameters());
        ImportData.initData();
        List<Object[]> data = new ArrayList<>();
        ImportData.moviesUrl.forEach((k, v) -> data.add(new Object[]{k, v}));
        return data.iterator();
    }*/

    public abstract void extractDataFromImdb(String movie, String url);

    public abstract void extractDataFromWiki(String movie, String wikiUrl) throws IOException;

    @Test
    public abstract void testCompareDirectorNames() throws IOException;

    @AfterMethod
    public void tearDownMethod() {
        if (moviesInfo.getMode().equalsIgnoreCase("gui")) {
            report.addStepRow_movies(moviesInfo.toString(), true);
            DriverThreadLocal.getDriver().quit();
        } else
            report.addStepRow_movies(moviesInfo.toString(), false);
    }

    @AfterSuite
    public void tearDownSuite(ITestContext context) {
        this.report.finalizeSuiteResult(context);
    }
}
