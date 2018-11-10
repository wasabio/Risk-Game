package classTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gameConsoleTest.testMapFileSyntax;
import gameConsoleTest.testMapValidation;

/**
 * This class is calling the test functions of the map. It check the map validation and the map syntax which call a lot of methods
 * from the map class
 * @author Yann-PC
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	testMapFileSyntax.class,
				testMapValidation.class})
public class testMap {
}