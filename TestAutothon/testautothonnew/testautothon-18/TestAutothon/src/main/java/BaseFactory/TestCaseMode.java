package BaseFactory;

import Base.Device;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

public enum TestCaseMode {
    API, GUI;

    public static TestCaseMode fromString(String arg) {
        return Stream.of(TestCaseMode.values()).filter(v -> v.name().replace("_", "").equalsIgnoreCase(arg)
                || v.name().replace("_", " ").equalsIgnoreCase(arg))
                .findFirst().orElseThrow(() -> new InvalidParameterException("\'" + arg + "\" Type is not supported."));

    }
}