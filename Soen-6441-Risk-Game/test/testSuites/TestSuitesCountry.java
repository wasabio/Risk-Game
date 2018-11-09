package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import CountryTests.TestAddOnTakenCountry;
import CountryTests.TestIsConnectedTo;

@RunWith(Suite.class)
@SuiteClasses({	
				TestAddOnTakenCountry.class,
				TestIsConnectedTo.class
				})

public class TestSuitesCountry {

}
