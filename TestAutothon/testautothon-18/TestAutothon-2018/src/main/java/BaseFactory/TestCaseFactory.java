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
		testCases.put("TestCasesexample", new TestCasesexample(thread));
	}

	public TestCase getTestCase(String testCaseName) {
		return testCases.get(testCaseName);
	}

/*
	private List<Object> findClasses(File directory, String packageName)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		directory = new File("src/main/java/TestCases");
		packageName = "TestCases";
		List<Object> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				Class<?> cl = Class
						.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				Constructor<?> cons = cl.getConstructor(Device.class);
				Object o = cons.newInstance(thread);
				classes.add(o);
			}
		}
		return classes;
	}
*/

}
