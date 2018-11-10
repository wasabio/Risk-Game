package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import classTests.testDices;
import classTests.testPhase;
import classTests.testPlayer;
import classTests.testRandom;

/**
 * test suite of suite class
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	testDices.class,
				testPhase.class,
				testPlayer.class,
				testRandom.class})
public class TestSuiteClass {
}