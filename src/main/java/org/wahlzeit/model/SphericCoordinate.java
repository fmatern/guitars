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
				
		//pre-condition
		assertLatitudeRange(latitude);
		assertLongitudeRange(longitude);
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = EARTHRADIUS;
		
		//class-invariants
		assertClassInvariants();
	}
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
			
		//pre-condition
		assertLatitudeRange(latitude);
		assertLongitudeRange(longitude);
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		//class-invariants
		assertClassInvariants();
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
		//pre-condition
		assertLatitudeRange(d);
		this.latitude = d;	
	}

	/**
	 * @methodtype set
	 */
	public void setLongitude(double d) { 
		//pre-condition
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
		//pre-condition
		assertRadiusRange(d);
		this.radius = d;	
	}
	
	/**
	 * @methodtype get, conversion
	 */
	public double getX(){
		return this.getRadius()*Math.sin(Math.toRadians(this.getLatitude()))*Math.cos(Math.toRadians(this.getLongitude()));
	}
	
	/**
	 * @methodtype get, conversion
	 */
	public double getY(){
		return this.getRadius()*Math.sin(Math.toRadians(this.getLatitude()))*Math.sin(Math.toRadians(this.getLongitude()));
	}
	
	/**
	 * @methodtype get, conversion
	 */
	public double getZ(){
		return this.getRadius()*Math.cos(Math.toRadians(this.getLatitude()));
	}

	/** 
	 * returns difference in latitude 
	 */
	public double getLatitudialDistance(SphericCoordinate coord) {
		//pre-condition
		assertArgumentNotNull(coord);
		return Math.abs( this.latitude - coord.getLatitude() );
	}

	/**
	 *  returns difference in longitude 
	 */
	public double getLongitudialDistance(SphericCoordinate coord) {
		//pre-condition
		assertArgumentNotNull(coord);
		return Math.abs( this.longitude - coord.getLongitude() );		
	}
	
	
	/**
	 *  returns distance between two coordinates in kilometer, always assumes earth radius
	 *  using Haversine formula  https://en.wikipedia.org/wiki/Haversine_formula
	 */
	public double getHaversineDistance(SphericCoordinate coordSpheric){
		
		//class-invariants
		assertClassInvariants();
		
		//pre-condition
		assertArgumentNotNull(coordSpheric);

	    double latDiffRadian = Math.toRadians(getLatitudialDistance(coordSpheric));
	    double lonDiffRadian = Math.toRadians(getLongitudialDistance(coordSpheric));

	    double h = Math.pow(Math.sin(latDiffRadian / 2), 2)+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coordSpheric.getLatitude())) * Math.pow(Math.sin(lonDiffRadian / 2), 2);
	    double d = Math.abs( 2 * EARTHRADIUS * Math.asin(Math.sqrt(h)) );
	    
	    //post-condition
	    assert( d >= 0.0);
	    
		//class-invariants
		assertClassInvariants();

	    return d;
		
	}

	/**
	 * @methodtype assertion
	 */
	private void assertLatitudeRange(double d) throws IllegalArgumentException {
		if( d > 90.0 || d < -90.0 || Double.isNaN(d)){
	        throw new IllegalArgumentException("Latitude must be between -90 and 90");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertLongitudeRange(double d) throws IllegalArgumentException {
		if( d > 180.0 || d < -180.0 || Double.isNaN(d)){
	        throw new IllegalArgumentException("Longitude must be between -180 and 180");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertRadiusRange(double d) throws IllegalArgumentException {
		if( d <= 0.0 || Double.isNaN(d)){
			throw new IllegalArgumentException("Radius must be larger than 0");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() throws IllegalStateException {
		super.assertClassInvariants();
		if( this.latitude > 90.0   || this.latitude < -90.0   || 
			this.longitude > 180.0 || this.longitude < -180.0 ||
			this.radius <= 0.0){
			throw new IllegalStateException("Illegal class invariant: Spheric attribute out of range!");	
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
