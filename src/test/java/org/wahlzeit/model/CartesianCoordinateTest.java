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
public class CartesianCoordinateTest {

	
	private CartesianCoordinate coordinateDefault;
	private CartesianCoordinate coordinate1;
	private CartesianCoordinate coordinate2;
	
	private final static double MAXDELTA = 0.0001;
	
	private double x1 = -80.2;
	private double y1 = 100.0;
	private double z1 = 15.0;

	private double x2 = -75.2;
	private double y2 = 125.0;
	private double z2 = 60.0;

	
	@Before
	public void initCoordinates(){
		coordinateDefault = CartesianCoordinate.getInstance();
		coordinate1 = CartesianCoordinate.getInstance(x1,y1,z1);
		coordinate2 = CartesianCoordinate.getInstance(x2,y2,z2);
	}
	
	/* test methods, calculations */
	@Test
	public void testConstructor(){
		assertNotNull(coordinateDefault);

		//check properties after creation with default constructor
		assertEquals(0.0, coordinateDefault.getX(), MAXDELTA);
		assertEquals(0.0, coordinateDefault.getY(), MAXDELTA);
		assertEquals(0.0, coordinateDefault.getZ(), MAXDELTA);
		
		//check properties after creation with values
		assertEquals(x1, coordinate1.getX(), MAXDELTA);
		assertEquals(y1, coordinate1.getY(), MAXDELTA);
		assertEquals(z1, coordinate1.getZ(), MAXDELTA);
		
	}
	
	
	@Test
	public void testGetDistanceFunction(){
		
		double distance1 = coordinate1.getDistance(coordinate2);
		double distance2 = coordinate2.getDistance(coordinate1);
		
		assertEquals(distance1, distance2, MAXDELTA);			
		assertEquals( 51.7204 , distance1, MAXDELTA);
		
		SphericCoordinate testCoord = SphericCoordinate.getInstance(0, 0, 100);
		distance2 = coordinate1.getDistance(testCoord);
		
		assertEquals( 153.808 , distance2, 0.1 );
	}

	@Test
	public void testEqualsFunction(){
		
		CartesianCoordinate equalCoord = CartesianCoordinate.getInstance(x1,y1,z1);
		
		assertFalse(coordinate1.isEqual(coordinate2));			
		assertTrue( equalCoord.isEqual(coordinate1));
	}
	
	/* test null pointer */
	@Test (expected = IllegalArgumentException.class)
	public void nullArgumentShouldThrowExeption1(){
		SphericCoordinate testNull = null;
		coordinateDefault.getDistance(testNull);	
		
	}
	
	
}

