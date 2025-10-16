package org.example.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;


/**
 * Cucumber test runner for executing feature files with JUnit 5 (JUnit Platform).
 * <p>
 * It scans the classpath resource directory "features" for .feature files and uses
 * step definitions located under the specified glue package.
 */
@Suite
@SelectPackages("org.example.steps")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "org.example.steps,org.example.hooks"
)
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, summary")
public class TestRunner {
}
