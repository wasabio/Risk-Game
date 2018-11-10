package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * the test suite of all tests
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	TestSuiteClass.class,
				TestSuiteGameConsole.class,
				TestSuiteMapEditor.class})
public class TestSuiteAll {
}