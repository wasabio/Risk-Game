package classTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mapEditorTest.testAddContinent;
import mapEditorTest.testAddCountry;
import mapEditorTest.testDeleteContinent;
import mapEditorTest.testDeleteCountry;

@RunWith(Suite.class)
@SuiteClasses({	testAddContinent.class,
				testAddCountry.class,
				testDeleteContinent.class,
				testDeleteCountry.class})
public class testMapEditor {
}