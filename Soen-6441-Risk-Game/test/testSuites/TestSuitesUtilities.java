package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testUtilities.TestFileHandler;
import testUtilities.TestMessage;
import testUtilities.testRandom;
import testUtilities.testStringAnalyzer;

@RunWith(Suite.class)
@SuiteClasses({	
				TestFileHandler.class,
				TestMessage.class,
				testRandom.class,
				testStringAnalyzer.class
				})

public class TestSuitesUtilities {

}
