package Runner;

import Base.Device;
import BaseFactory.TestCaseMode;
import BaseFactory.WikiTestCaseFactory;
import TestCases.ImportData;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InitFactoryWiki {
    private static Logger log = Logger.getLogger(InitFactory.class.getName());


    @Factory(dataProvider = "movieInfo")
    public Object[] initFactorySetup(String movieName, String wikiUrl, String testCaseMode) throws SecurityException, IllegalArgumentException {
        log.info("running thread for ->  : " + movieName);
        TestCaseMode mode = TestCaseMode.fromString(testCaseMode);
        return new Object[]{new WikiTestCaseFactory(movieName, wikiUrl).getTestCase(mode)};
    }


    @DataProvider(name = "movieInfo", parallel = true)
    public static Iterator<Object[]> moviesNames(ITestContext testContext) {
        if (testContext != null)
            System.getProperties().putAll(testContext.getCurrentXmlTest().getAllParameters());
        ImportData.initData();
        String testCaseMode = System.getProperty("TestCaseMode");
        List<Object[]> data = new ArrayList<>();
        ImportData.moviesUrl.forEach((k, v) -> data.add(new Object[]{k, v, testCaseMode}));
        return data.iterator();
    }
}
