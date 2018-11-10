package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testSuites.testModules.TestSuiteClass;

/**
 * This test suite starts all the tests
 * @author Yann-PC
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	TestSuiteClass.class,
				TestSuiteGameConsole.class,
				TestSuiteMapEditor.class})
public class TestSuiteAll {
}