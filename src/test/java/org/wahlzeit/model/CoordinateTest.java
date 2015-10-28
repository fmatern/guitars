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
public class CoordinateTest {

	
	private Coordinate coordinateDefault;
	private Coordinate coordinate1;
	private Coordinate coordinate2;
	
	private final static double MAXDELTA = 0.0001;
	
	private double lat1  = -80.2;  /*breitengrad*/
	private double long1 = 100.0;  /*laengengrad*/ 

	private double lat2  = 60.3;    /*breitengrad*/
	private double long2 = -150.4;  /*laengengrad*/ 

	
	@Before
	public void initCoordinates(){
		coordinateDefault = new Coordinate();
		coordinate1 = new Coordinate(lat1, long1);
		coordinate2 = new Coordinate(lat2, long2);
	}
	
	/* test methods, calculations */
	@Test
	public void testConstructor(){
		assertNotNull(coordinateDefault);

		//check properties after creation with default constructor
		assertEquals(0, coordinateDefault.getLatitude(), MAXDELTA);
		assertEquals(0, coordinateDefault.getLongitude(), MAXDELTA);
		
		//check properties after creation with values
		assertEquals(lat1, coordinate1.getLatitude(), MAXDELTA);
		assertEquals(long1, coordinate1.getLongitude(), MAXDELTA);
		
	}
	
	@Test
	public void testLongLatDistanceFunctions(){
		
		assertEquals(Math.abs(lat1 - lat2), coordinate1.getLatitudialDistance(coordinate2), MAXDELTA);
		assertEquals(Math.abs(long1 - long2), coordinate1.getLongitudialDistance(coordinate2), MAXDELTA);		
		
	}
	
	@Test
	public void testGetDistanceFunction(){
		
		double distance1 = coordinate1.getDistance(coordinate2);
		double distance2 = coordinate2.getDistance(coordinate1);
		
		assertEquals(distance1, distance2, MAXDELTA);			
		assertEquals( 16919.28935 , distance1, MAXDELTA);
		
		Coordinate testCoord = new Coordinate(-55.56, 175.15);
		distance2 = testCoord.getDistance(coordinate2);
		
		assertEquals( 13235.88297, distance2, MAXDELTA );

	}

	@Test
	public void testEqualsFunction(){
		
		Coordinate equalCoord = new Coordinate(lat1, long1);
		
		assertFalse(coordinate1.equals(coordinate2));			
		assertTrue( equalCoord.equals(coordinate1));


	}
	
	/* test null pointer */
	@Test (expected = IllegalArgumentException.class)
	public void nullArgumentShouldThrowExeption1(){
		Coordinate testNull = null;
		coordinateDefault.getLatitudialDistance(testNull);	
		coordinateDefault.getLongitudialDistance(testNull);	
	}
	
	/* test value range */
	@Test (expected = IllegalArgumentException.class)
	public void latitudeOutOfRangeShouldThrowExeption1(){
		coordinateDefault.setLatitude(-100.0);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void latitudeOutOfRangeShouldThrowExeption2(){
		coordinateDefault.setLatitude(100.0);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void longitudeOutOfRangeShouldThrowExeption1(){	
		coordinateDefault.setLongitude(-200.0);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void longitudeOutOfRangeShouldThrowExeption2(){		
		coordinateDefault.setLongitude(200.0);		
	}
	
}

