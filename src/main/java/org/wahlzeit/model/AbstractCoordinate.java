package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.services.EmailAddress;

public abstract class AbstractCoordinate implements Coordinate {
	
	public static double DELTA = 0.001;
	
	protected static final Map<Integer, AbstractCoordinate> instances = new HashMap<Integer, AbstractCoordinate>();

	/**
	 *  returns shortest distance between two coordinates in kilometer
	 *  euclidean distance
	 */
	public double getDistance(Coordinate coord) {
		//class-invariants
		assertClassInvariants();
		
		//pre-condition
		assertArgumentNotNull(coord);
		
		double xDiff = (coord.getX()-this.getX())*(coord.getX()-this.getX());
		double yDiff = (coord.getY()-this.getY())*(coord.getY()-this.getY());
		double zDiff = (coord.getZ()-this.getZ())*(coord.getZ()-this.getZ());
		
		double distance = Math.sqrt(xDiff+yDiff+zDiff);
		
		//post-condition
		assert (distance >= 0.0);
		
		//class-invariants
		assertClassInvariants();
		
		return distance;
	}

	/**
	 * @methodtype boolean query
	*/
	public boolean isEqual(Coordinate coord) {
		if (this == coord)
			return true;
		if (coord == null)
			return false;
		if (this.getDistance(coord) <= DELTA)
			return true;
		else
			return false;
	}
	
	/**
	 * @methodtype comparison
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(getX());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getY());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getZ());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * @methodtype boolean query
	*/
	public boolean equals(Coordinate coord) {
		return isEqual(coord);
	}
	
	/**
	 * @methodtype assertion
	*/
	protected void assertArgumentNotNull(Coordinate coord) throws IllegalArgumentException {
		if( coord == null ){
	        throw new IllegalArgumentException("Argument was null");
		}
	}
	
	/**
	 * @methodtype assertion
	*/
	protected void assertClassInvariants() throws IllegalStateException {
		if ( Double.isNaN(this.getX()) || Double.isNaN(this.getY()) || Double.isNaN(this.getZ()) ) {
			throw new IllegalStateException("Illegal class invariant: An Attribute is NaN!");
		}
	}
	
	
	
}
