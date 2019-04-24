package BaseFactory;


import TestCases.WikiApiTestCase;
import TestCases.WikiGuiTestCase;
import TestCases.WikiTestCase;

import java.util.HashMap;
import java.util.Map;


public class WikiTestCaseFactory {

    private static Map<TestCaseMode, WikiTestCase> testCases= new HashMap<>();

    public WikiTestCaseFactory(String movieName, String wikiUrl) {
        testCases.put(TestCaseMode.GUI, new WikiGuiTestCase(movieName, wikiUrl));
        testCases.put(TestCaseMode.API, new WikiApiTestCase(movieName, wikiUrl));
    }

    public WikiTestCase getTestCase(TestCaseMode mode) {
        return testCases.get(mode);

    }
}
