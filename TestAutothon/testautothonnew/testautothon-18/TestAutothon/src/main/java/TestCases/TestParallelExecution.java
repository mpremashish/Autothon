package TestCases;

import Base.Device;
import Base.TestCase;
import org.testng.annotations.*;


public class TestParallelExecution extends TestCase {

    @Factory(dataProvider = "instanceFactory")
    public TestParallelExecution(String thread) throws SecurityException, IllegalArgumentException {
        super(Device.fromString(thread));
    }

    @BeforeClass
    public void beforeClass1() {
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
