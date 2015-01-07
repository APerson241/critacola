package org.rohawks.critacola.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.rohawks.critacola.beans.Competition;

/**
 * Unit test for the Competition bean.
 */
public class CompetitionTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CompetitionTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( CompetitionTest.class );
    }

    public void testNameProperty() {
        Competition competition = new Competition();
	assertEquals(competition.getName(), "");

	String sampleCompetitionName = "Electric Boogaloo Competition";
	competition.setName(sampleCompetitionName);
	assertEquals(competition.getName(), sampleCompetitionName);
    }
}
