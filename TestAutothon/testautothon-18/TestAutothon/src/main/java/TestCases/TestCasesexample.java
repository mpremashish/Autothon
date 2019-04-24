package TestCases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.*;
import Base.Device;
import Base.TestCase;

public class TestCasesexample extends TestCase {

    public TestCasesexample(Device thread) throws SecurityException, IllegalArgumentException {
        super(thread);
    }

    /*@Factory(dataProvider = "instanceFactory")
    public TestCasesexample(String thread) throws SecurityException, IllegalArgumentException {
        super(Device.fromString(thread));
    }*/

    @BeforeClass
    public void beforeClass1() throws MalformedURLException, InterruptedException {
        setDriver();
    }

    @Test(dataProvider = "urlProvider")
    public void test(String url) {
        driver.get(url);
    }

    @DataProvider(name = "urlProvider", parallel = true)
    public Object[][] testDataProvide() {
        return new Object[][]{{"https://dzone.com/articles/testng-run-tests-sequentially"}, {"http://hc.apache.org/httpcomponents-core-ga/httpcore/apidocs/org/apache/http/util/EntityUtils.html"}};
    }

    @AfterClass
    public void beforeClass() {
        getDriver().quit();
    }
}
