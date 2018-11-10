package testSuites.testModules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import classTests.testDices;
import classTests.testPhase;
import classTests.testPlayer;

/**
<<<<<<< HEAD:Soen-6441-Risk-Game/test/testSuites/TestSuiteClass.java
 * test suite of suite class
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 *
=======
 * This test suite tests the gameplay module
 * @author Yann-PC
>>>>>>> 65f3ad7b7d51280801d7ccc3c2ff83d6b7d9481a:Soen-6441-Risk-Game/test/testSuites/testModules/TestSuiteGameplayModule.java
 */
@RunWith(Suite.class)
@SuiteClasses({	
				testDices.class,
				testPhase.class,
				testPlayer.class})
public class TestSuiteGameplayModule {
}