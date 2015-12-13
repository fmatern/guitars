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
public class SphericCoordinateTest {

	
	private SphericCoordinate coordinateDefault;
	private SphericCoordinate coordinate1;
	private SphericCoordinate coordinate2;
	
	private final static double MAXDELTA = 0.0001;
	
	private double lat1  = -80.2;  /*breitengrad*/
	private double long1 = 100.0;  /*laengengrad*/ 

	private double lat2  = 60.3;    /*breitengrad*/
	private double long2 = -150.4;  /*laengengrad*/ 

	
	@Before
	public void initCoordinates(){
		coordinateDefault = SphericCoordinate.getInstance();
		coordinate1 = SphericCoordinate.getInstance(lat1, long1);
		coordinate2 = SphericCoordinate.getInstance(lat2, long2);
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
		
		//haversine
		double distance1 = coordinate1.getHaversineDistance(coordinate2);
		double distance2 = coordinate2.getHaversineDistance(coordinate1);
		
		assertEquals(distance1, distance2, MAXDELTA);			
		assertEquals( 16919.28935 , distance1, MAXDELTA);
		
		
		//euclidean
		CartesianCoordinate cartCoord = CartesianCoordinate.getInstance(-80.2, 100.0, 15.0);
		
		distance1 = cartCoord.getDistance(coordinate2);
		distance2 = coordinate2.getDistance(cartCoord);
		
		assertEquals(distance1, distance2, MAXDELTA);
	}

	@Test
	public void testEqualsFunction(){
		
		SphericCoordinate equalCoord = SphericCoordinate.getInstance(lat1, long1);
		
		assertFalse(coordinate1.isEqual(coordinate2));			
		assertTrue( equalCoord.isEqual(coordinate1));


	}
	
	/* test null pointer */
	@Test (expected = IllegalArgumentException.class)
	public void nullArgumentShouldThrowExeption1(){
		SphericCoordinate testNull = null;
		coordinateDefault.getLatitudialDistance(testNull);	
		coordinateDefault.getLongitudialDistance(testNull);	
	}
	
	/* test value range */
	@Test (expected = IllegalArgumentException.class)
	public void latitudeOutOfRangeShouldThrowExeption1(){
		coordinateDefault = SphericCoordinate.getInstance(-100.0,0.0);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void latitudeOutOfRangeShouldThrowExeption2(){
		coordinateDefault = SphericCoordinate.getInstance(+100.0,0.0);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void longitudeOutOfRangeShouldThrowExeption1(){	
		coordinateDefault = SphericCoordinate.getInstance(0.0,-200.0);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void longitudeOutOfRangeShouldThrowExeption2(){		
		coordinateDefault = SphericCoordinate.getInstance(0.0, 200.0);	
	}
	
}

