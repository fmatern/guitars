package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


/**
 * test cases of the class Coordinate
 */
public class CoordinateTest {

	/*class attributes*/
	private Coordinate coordinateDefault;
	private Coordinate coordinate1;
	private Coordinate coordinate2;
	
	private final static double MAXDELTA = 0.0001;
	
	private double long1 = 100.0;  /*längengrad -180-180*/ 
	private double lat1  = -80.2;  /*breitengrad -90-90*/
	
	private double long2 = -150.4;  /*längengrad -180-180*/ 
	private double lat2  = 60.3;    /*breitengrad -90-90*/
	
	/*init*/
	@Before
	public void initCoordinates(){
		coordinateDefault = new Coordinate();
		coordinate1 = new Coordinate(lat1, long1);
		coordinate2 = new Coordinate(lat2, long2);
	}
	
	/*tests*/
	@Test
	public void testConstructor(){
		assertNotNull(coordinateDefault);

		// Check properties after creation with default constructor
		assertEquals(0, coordinateDefault.getLatitude(), MAXDELTA);
		assertEquals(0, coordinateDefault.getLongitude(), MAXDELTA);
		
		//check properties after creation with values
		assertEquals(lat1, coordinate1.getLatitude(), MAXDELTA);
		assertEquals(long1, coordinate1.getLongitude(), MAXDELTA);
		
	}
	
	@Test
	public void testLongLatDistanceFunctions(){
		
		assertEquals((lat1 - lat2), coordinate1.getLatitudialDistance(coordinate2), MAXDELTA);
		assertEquals((long1 - long2), coordinate1.getLongitudialDistance(coordinate2), MAXDELTA);		
		
	}
	
	@Test
	public void testdistanceFunction(){
			
		//TODO
		
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

