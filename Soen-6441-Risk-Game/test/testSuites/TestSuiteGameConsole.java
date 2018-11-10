package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gameConsoleTest.testAddArmiesToCountry;
import gameConsoleTest.testAttack;
import gameConsoleTest.testCalculateArmyNum;
import gameConsoleTest.testCanSendTroopsToAlly;
import gameConsoleTest.testCheckConnectedMap;
import gameConsoleTest.testCheckNoEmptyContinent;
import gameConsoleTest.testDistributeCountries;
import gameConsoleTest.testIsConnectedTo;
import gameConsoleTest.testMapFileSyntax;
import gameConsoleTest.testMapValidation;
import gameConsoleTest.testTrade;

/**
 * test suite of gameconsole
 * @author skyba
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	testAddArmiesToCountry.class,
				testAttack.class,
				testCalculateArmyNum.class,
				testCanSendTroopsToAlly.class,
				testCheckConnectedMap.class,
				testCheckNoEmptyContinent.class,
				testDistributeCountries.class,
				testIsConnectedTo.class,
				testMapFileSyntax.class,
				testMapValidation.class,
				testTrade.class})
public class TestSuiteGameConsole {
}