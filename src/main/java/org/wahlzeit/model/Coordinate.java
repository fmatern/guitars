package org.wahlzeit.model;

public interface Coordinate {

	/**
	 *
	 */
	public double getDistance(Coordinate coord);
	
	public boolean isEqual(Coordinate coord);
	
}
