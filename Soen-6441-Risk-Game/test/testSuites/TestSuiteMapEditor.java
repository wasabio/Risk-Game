package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import MapEditorTests.TestAddContinent;
import MapEditorTests.TestAddCountry;
import MapEditorTests.TestDeleteContinent;
import MapEditorTests.TestDeleteCountry;


@RunWith(Suite.class)
@SuiteClasses({	
				TestAddContinent.class,
				TestAddCountry.class,
				TestDeleteCountry.class,
				TestDeleteContinent.class
				})

public class TestSuiteMapEditor {

}
