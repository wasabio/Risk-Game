package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gameConsoleTest.testAddArmiesToCountry;
import gameConsoleTest.testCalculateArmyNum;
import gameConsoleTest.testCheckConnectedMap;
import gameConsoleTest.testCheckNoEmptyContinent;
import gameConsoleTest.testDistributeCountries;
import gameConsoleTest.testMapFileSyntax;
import gameConsoleTest.testMapValidation;
/**
 * 
 * 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */

@RunWith(Suite.class)
@SuiteClasses({	testAddArmiesToCountry.class,
				testCalculateArmyNum.class,
				testCheckConnectedMap.class,
				testCheckNoEmptyContinent.class,
				testDistributeCountries.class,
				testMapFileSyntax.class,
				testMapValidation.class})
public class TestSuiteGameConsole {
}