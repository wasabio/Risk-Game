package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import MapTests.TestAddArmiesToCountry;
import MapTests.TestCalculateArmyNum;
import MapTests.TestCheckConnectedMap;
import MapTests.TestCheckNoEmptyContinent;
import MapTests.TestDistributeCountries;
import MapTests.TestMapValidation;
import testUtilities.TestFileHandler;


@RunWith(Suite.class)
@SuiteClasses({	TestAddArmiesToCountry.class,
				TestCalculateArmyNum.class,
				TestCheckConnectedMap.class,
				TestCheckNoEmptyContinent.class,
				TestDistributeCountries.class,
				TestFileHandler.class,
				TestMapValidation.class})
public class TestSuiteMap {
}