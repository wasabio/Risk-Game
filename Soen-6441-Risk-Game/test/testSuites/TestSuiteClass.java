package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import classTests.testDices;
import classTests.testPhase;
import classTests.testPlayer;
import classTests.testRandom;

@RunWith(Suite.class)
@SuiteClasses({	testDices.class,
				testPhase.class,
				testPlayer.class,
				testRandom.class})
public class TestSuiteClass {
}