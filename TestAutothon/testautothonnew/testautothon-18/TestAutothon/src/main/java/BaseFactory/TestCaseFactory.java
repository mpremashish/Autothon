package BaseFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Base.Device;
import Base.TestCase;
import TestCases.TestAutothon;
import TestCases.TestCasesexample;

public class TestCaseFactory {
	private Map<String, TestCase> testCases;
	Device thread;

	public TestCaseFactory(Device thread) {
		this.thread = thread;
		testCases = new HashMap<>();
		testCases.put("testautothon", new TestAutothon(thread));
		//testCases.put("TestCasesexample", new TestCasesexample(thread));
	}

	public TestCase getTestCase(String testCaseName) {
		return testCases.get(testCaseName);
	}

}
