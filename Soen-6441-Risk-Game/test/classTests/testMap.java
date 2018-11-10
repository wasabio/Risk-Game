package classTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gameConsoleTest.testMapFileSyntax;
import gameConsoleTest.testMapValidation;

@RunWith(Suite.class)
@SuiteClasses({	testMapFileSyntax.class,
				testMapValidation.class})
public class testMap {
}