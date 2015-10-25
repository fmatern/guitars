package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * test cases of the class Coordinate
 */
public class CoordinateTest {

	
	private Coordinate coordinateDefault;
	private Coordinate coordinate1;
	private Coordinate coordinate2;
	
	private final static double MAXDELTA = 0.0001;
	
	private double long1 = 100.0;  /*laengengrad*/ 
	private double lat1  = -80.2;  /*breitengrad*/
	
	private double long2 = -150.4;  /*laengengrad*/ 
	private double lat2  = 60.3;    /*breitengrad*/
	
	
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
		
		Coordinate newCoord1 = coordinate1.getDistance(coordinate2);
		Coordinate newCoord2 = coordinate2.getDistance(coordinate1);
		
		assertEquals(newCoord1.getLatitude(), newCoord2.getLatitude(), MAXDELTA);		
		assertEquals(newCoord1.getLongitude(), newCoord2.getLongitude(), MAXDELTA);	
		
		assertEquals( Math.abs(lat1 - lat2), newCoord1.getLatitude(), MAXDELTA);
		assertEquals( Math.abs(long1 - long2), newCoord1.getLongitude(), MAXDELTA);		
		
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

