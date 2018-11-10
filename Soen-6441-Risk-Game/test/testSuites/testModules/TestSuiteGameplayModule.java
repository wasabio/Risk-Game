package testSuites.testModules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import classTests.testDices;
import classTests.testPhase;
import classTests.testPlayer;

/**
 * This test suite tests the gameplay module
 * @author Yann-PC
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	
				testDices.class,
				testPhase.class,
				testPlayer.class})
public class TestSuiteGameplayModule {
}