package org.wahlzeit.model;


/**
 * A coordinate represents the location a photo was taken
 */
public class Coordinate {
	
	private double latitude;
	private double longitude;
	private final static double EARTHRADIUS = 6371; /*Earth radius in km*/

	/**
	 * @methodtype constructor
	 */
	public Coordinate(double latitude, double longitude) {
					
		assertLatitudeRange(latitude);
		assertLongitudeRange(longitude);
		
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @methodtype constructor
	 */
	public Coordinate() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @methodtype set
	 */
	public void setLatitude(double d) {
		assertLatitudeRange(d);
		this.latitude = d;	
	}

	/**
	 * @methodtype set
	 */
	public void setLongitude(double d) { 
		assertLongitudeRange(d);
		this.longitude = d;	
		
	}

	/** 
	 * returns difference in latitude 
	 */
	public double getLatitudialDistance(Coordinate coord) {
		assertArgumentNotNull(coord);
		return Math.abs( this.latitude - coord.getLatitude() );
	}

	/**
	 *  returns difference in longitude 
	 */
	public double getLongitudialDistance(Coordinate coord) {
		assertArgumentNotNull(coord);
		return Math.abs( this.longitude - coord.getLongitude() );		
	}

	/**
	 *  returns distance between two coordinates in kilometer
	 *  using Haversine formula  https://en.wikipedia.org/wiki/Haversine_formula
	 */
	public double getDistance(Coordinate coord){
		
	    double latDiffRadian = Math.toRadians(getLatitudialDistance(coord));
	    double lonDiffRadian = Math.toRadians(getLongitudialDistance(coord));

	    double h = Math.pow(Math.sin(latDiffRadian / 2), 2)+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coord.getLatitude())) * Math.pow(Math.sin(lonDiffRadian / 2), 2);
	    double d = 2 * EARTHRADIUS * Math.asin(Math.sqrt(h));

	    return Math.abs(d);
		
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertLatitudeRange(double d) throws IllegalArgumentException {
		if( d > 90.0 || d < -90.0){
	        throw new IllegalArgumentException("Latitude must be between -90 and 90");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertLongitudeRange(double d) throws IllegalArgumentException {
		if( d > 180.0 || d < -180.0){
	        throw new IllegalArgumentException("Longitude must be between -180 and 180");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertArgumentNotNull(Coordinate coord) throws IllegalArgumentException {
		if( coord == null ){
	        throw new IllegalArgumentException("Argument was null");
		}
	}

	/**
	 * @methodtype comparison
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * @methodtype boolean query method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude)) {
			return false;
		}
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude)) {
			return false;
		}
		return true;
	}

}
