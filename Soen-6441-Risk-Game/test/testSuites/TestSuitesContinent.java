package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ContinentTests.TestGetowner;

@RunWith(Suite.class)
@SuiteClasses({	
				TestGetowner.class
				})
public class TestSuitesContinent {

}
