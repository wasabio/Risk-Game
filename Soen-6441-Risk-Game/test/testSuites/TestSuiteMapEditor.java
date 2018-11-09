package testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import MapEditorTest.testAddContinent;
import MapEditorTest.testAddCountry;
import MapEditorTest.testDeleteContinent;
import MapEditorTest.testDeleteCountry;

@RunWith(Suite.class)
@SuiteClasses({	testAddContinent.class,
				testAddCountry.class,
				testDeleteContinent.class,
				testDeleteCountry.class})
public class TestSuiteMapEditor {
}
