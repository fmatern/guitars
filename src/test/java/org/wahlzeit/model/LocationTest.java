package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * test cases of the class Coordinate
 */
public class LocationTest {

	
	private Location locDefault;
	private Location loc1;
	private Location loc2;
	
	private final static double MAXDELTA = 0.0001;
	
	private double lat1  = -80.2;  /*breitengrad*/
	private double long1 = 100.0;  /*laengengrad*/ 

	private double lat2  = 60.3;    /*breitengrad*/
	private double long2 = -150.4;  /*laengengrad*/ 
	
	private String name1 = "Nice Location";
	private String name2 = "Not so nice Location";

	
	@Before
	public void init(){
		locDefault = new Location();
		loc1 = new Location(name1, lat1, long1);
		loc2 = new Location(name2, lat2, long2);
	}
	
	/* test methods, calculations */
	@Test
	public void testConstructor(){
		assertNotNull(locDefault);

	    SphericCoordinate coord = new SphericCoordinate();
		//check properties after creation with default constructor
		assertEquals("name unknown", locDefault.getName());
		assertTrue(coord.isEqual(locDefault.getCoordinate()));
		
		//check properties after creation with values
		assertEquals("Not so nice Location", loc2.getName());
		assertEquals(lat1, loc1.getCoordinate().getLatitude(), MAXDELTA);
		
	}

	
	/* test null pointer */
	@Test (expected = IllegalArgumentException.class)
	public void nullArgumentShouldThrowExeption1(){
		SphericCoordinate testNull = null;
		
		Location testLoc = new Location("Test", testNull);
		
	}

	
}

