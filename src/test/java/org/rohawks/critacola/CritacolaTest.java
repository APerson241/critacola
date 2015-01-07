package org.rohawks.critacola;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.rohawks.critacola.Critacola;

/**
 * Unit test for simple app.
 */
public class CritacolaTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CritacolaTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( CritacolaTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testApp() {
        assertTrue( true );
    }
}
