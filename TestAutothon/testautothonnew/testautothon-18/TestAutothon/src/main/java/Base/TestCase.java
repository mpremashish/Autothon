package Base;

import java.io.FileInputStream;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import JsonCollector.*;
import model.JsonPlaceHolder;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

public abstract class TestCase extends TestBaseManager {

    private static Logger log = Logger.getLogger(TestCase.class.getName());
    protected static Stack<JsonPlaceHolder> jsonStack = new Stack<>();
    private static ConcurrentMap<String, String> userMap = new ConcurrentHashMap<>();
/*
    @Factory(dataProvider = "instanceFactory")
    public TestCase(String thread) throws SecurityException, IllegalArgumentException {
        super(Device.fromString(thread));
        System.out.println("testing 12");
    }

    @DataProvider(name = "instanceFactory", parallel = true)
    public static Iterator<Object[]> testFactory(ITestContext testContext) {
        System.out.println("testing");
        if (testContext != null)
            System.getProperties().putAll(testContext.getCurrentXmlTest().getAllParameters());
        List<Object[]> data = new ArrayList<>();
        Arrays.stream(System.getProperty("threadsName").split(",")).forEach(i -> {
            data.add(new Object[]{i});
        });
        return data.iterator();
    }*/

    public TestCase(Device thread) {
        super(thread);
    }

    protected void setCredentials() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(System.getProperty("user.dir") + "//logindata//credentials.properties"));
            userMap.putAll(prop.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static synchronized String popUserMap() {
        Iterator<Map.Entry<String, String>> itr = userMap.entrySet().iterator();
        Map.Entry<String, String> nxtEntry;
        String pair;
        if (itr.hasNext()) {
            nxtEntry = itr.next();
            pair = nxtEntry.getKey() + ":" + nxtEntry.getValue();
            userMap.remove(nxtEntry.getKey());
        } else {
            throw new InvalidParameterException("user details is empty..");
        }
        return pair;
    }

    public void setTimeout(long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void TestExecutionSetUp() {
        log.info("Running TestExecutionSetUp (before suite)");
        jsonStack = JsonCollector.findJsonObjects();
    }


    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }


}
