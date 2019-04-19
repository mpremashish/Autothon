package Runner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import BaseFactory.TestCaseFactory;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import Base.*;


public class InitFactory {


    private static Logger log = Logger.getLogger(InitFactory.class.getName());

    @Factory(dataProvider = "testFactory")
    public Object[] InitFactorysetUP(String thread, String testCaseName) throws SecurityException, IllegalArgumentException {
        Device device = Device.fromString(thread);
        log.info("running thread for -> " + device + " : " + testCaseName);
        return new Object[]{new TestCaseFactory(device).getTestCase(testCaseName)};
    }

    @DataProvider(name = "testFactory", parallel = true)
    public static Iterator<Object[]> testFactory(ITestContext testContext) {
        if (testContext != null)
            System.getProperties().putAll(testContext.getCurrentXmlTest().getAllParameters());
        List<Object[]> data = new ArrayList<>();
        String[] testCaseName = System.getProperty("testCaseName").split(",");
        Arrays.stream(System.getProperty("threadsName").split(",")).forEach(i -> {
            Arrays.stream(testCaseName).forEach(j -> {
                data.add(new Object[]{i, j});
            });
        });
        return data.iterator();
    }
}
