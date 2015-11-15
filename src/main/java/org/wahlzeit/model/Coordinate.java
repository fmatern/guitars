package org.wahlzeit.model;

public interface Coordinate {
	
	/**
	 * @methodtype get
	 */
	public double getX();
	
	/**
	 * @methodtype get
	 */
	public double getY();
	
	/**
	 * @methodtype get
	 */
	public double getZ();

	/**
	 *  returns shortest distance between two coordinates in kilometer
	 *  euclidean distance
	 */
	public double getDistance(Coordinate coord);
	
	/**
	 * @methodtype boolean query
	*/
	public boolean isEqual(Coordinate coord);
	
}
