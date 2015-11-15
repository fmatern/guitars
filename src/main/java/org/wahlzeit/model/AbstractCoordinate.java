package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 *  returns shortest distance between two coordinates in kilometer
	 *  euclidean distance
	 */
	public double getDistance(Coordinate coord) {
		
		assertArgumentNotNull(coord);
		
		double xDiff = (coord.getX()-this.getX())*(coord.getX()-this.getX());
		double yDiff = (coord.getY()-this.getY())*(coord.getY()-this.getY());
		double zDiff = (coord.getZ()-this.getZ())*(coord.getZ()-this.getZ());
		
		double distance = Math.sqrt(xDiff+yDiff+zDiff);
		
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
		if (this.getDistance(coord) == 0.0)
			return true;
		else
			return false;
	}
	
	/**
	 * @methodtype assertion
	*/
	protected void assertArgumentNotNull(Coordinate coord) throws IllegalArgumentException {
		if( coord == null ){
	        throw new IllegalArgumentException("Argument was null");
		}
	}
	
}
