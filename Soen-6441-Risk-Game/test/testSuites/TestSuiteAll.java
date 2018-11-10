package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	TestSuiteClass.class,
				TestSuiteGameConsole.class,
				TestSuiteMapEditor.class})
public class TestSuiteAll {
}