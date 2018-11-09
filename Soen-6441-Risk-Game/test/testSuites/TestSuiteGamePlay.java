package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import GamePlayTests.TestAttack;
import GamePlayTests.TestPhase;
import GamePlayTests.TestPlayer;

@RunWith(Suite.class)
@SuiteClasses({	
				TestAttack.class,
				TestPhase.class,
				TestPlayer.class
				})

public class TestSuiteGamePlay {

}
