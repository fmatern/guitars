package org.wahlzeit.model;


/**
 * A coordinate represents the location a photo was taken
 */
public class SphericCoordinate extends AbstractCoordinate{
	
	private double latitude;
	private double longitude;
	private double radius;
	private final static double EARTHRADIUS = 6371; /*Earth radius in km*/

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude) {
					
		assertLatitudeRange(latitude);
		assertLongitudeRange(longitude);
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = EARTHRADIUS;
	}
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
					
		assertLatitudeRange(latitude);
		assertLongitudeRange(longitude);
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {
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
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @methodtype set
	 */
	public void setRadius(double d) {
		assertRadiusRange(d);
		this.radius = d;	
	}
	
	/**
	 * @methodtype get, conversion
	 */
	public double getX(){
		return this.getRadius()*Math.sin(this.getLatitude())*Math.cos(this.getLongitude());
	}
	
	/**
	 * @methodtype get, conversion
	 */
	public double getY(){
		return this.getRadius()*Math.sin(this.getLatitude())*Math.sin(this.getLongitude());
	}
	
	/**
	 * @methodtype get, conversion
	 */
	public double getZ(){
		return this.getRadius()*Math.cos(this.getLatitude());
	}

	/** 
	 * returns difference in latitude 
	 */
	public double getLatitudialDistance(SphericCoordinate coord) {
		assertArgumentNotNull(coord);
		return Math.abs( this.latitude - coord.getLatitude() );
	}

	/**
	 *  returns difference in longitude 
	 */
	public double getLongitudialDistance(SphericCoordinate coord) {
		assertArgumentNotNull(coord);
		return Math.abs( this.longitude - coord.getLongitude() );		
	}
	
	
	/**
	 *  returns distance between two coordinates in kilometer, always assumes earth radius
	 *  using Haversine formula  https://en.wikipedia.org/wiki/Haversine_formula
	 */
	public double getHaversineDistance(SphericCoordinate coordSpheric){
		
		assertArgumentNotNull(coordSpheric);

	    double latDiffRadian = Math.toRadians(getLatitudialDistance(coordSpheric));
	    double lonDiffRadian = Math.toRadians(getLongitudialDistance(coordSpheric));

	    double h = Math.pow(Math.sin(latDiffRadian / 2), 2)+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coordSpheric.getLatitude())) * Math.pow(Math.sin(lonDiffRadian / 2), 2);
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
	private void assertRadiusRange(double d) throws IllegalArgumentException {
		if( d <= 0.0){
			throw new IllegalArgumentException("Radius must be larger than 0");
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
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
}
