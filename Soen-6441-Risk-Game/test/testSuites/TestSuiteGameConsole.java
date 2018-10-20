package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gameConsoleTest.testAddArmiesToCountry;
import gameConsoleTest.testCalculateArmyNum;
import gameConsoleTest.testCheckConnectedGraph;
import gameConsoleTest.testCheckNoEmptyContinent;
import gameConsoleTest.testDistributeCountries;
import gameConsoleTest.testMapFileSyntax;
import gameConsoleTest.testMapValidation;


@RunWith(Suite.class)
@SuiteClasses({	testAddArmiesToCountry.class,
				testCalculateArmyNum.class,
				testCheckConnectedGraph.class,
				testCheckNoEmptyContinent.class,
				testDistributeCountries.class,
				testMapFileSyntax.class,
				testMapValidation.class})
public class TestSuiteGameConsole {
}