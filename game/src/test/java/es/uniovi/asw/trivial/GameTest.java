package es.uniovi.asw.trivial;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class GameTest extends TestCase
{
	public GameTest(String testName) 
	{
		 super( testName );
	}
	
	public static Test suite()
    {
        return new TestSuite( GameTest.class );
    }
	
	public void testApp()
    {
        assertTrue( true );
    }

}