package org.wahlzeit.model;


/**
 * A coordinate represents the location a photo was taken
 */
public class SphericCoordinate implements Coordinate{
	
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
	 *  returns shortest distance between two coordinates in kilometer
	 *  euclidean distance
	 */
	public double getDistance(Coordinate coord){
		
		assertArgumentNotNull(coord);
		
		SphericCoordinate coordSpheric;
		if( coord instanceof CartesianCoordinate) {
			return coord.getDistance(this);
		}else{
			coordSpheric = (SphericCoordinate) coord;
		}
		
		/* convert to cartesian */
		double xthis = this.radius*Math.sin(this.latitude)*Math.cos(this.longitude);
		double ythis = this.radius*Math.sin(this.latitude)*Math.sin(this.longitude);
		double zthis = this.radius*Math.cos(this.latitude);
		
		double xother = coordSpheric.getRadius()*Math.sin(coordSpheric.getLatitude())*Math.cos(coordSpheric.getLongitude());
		double yother = coordSpheric.getRadius()*Math.sin(coordSpheric.getLatitude())*Math.sin(coordSpheric.getLongitude());
		double zother = coordSpheric.getRadius()*Math.cos(coordSpheric.getLatitude());
		
		/*calc euclidean distance*/
		double xDiff = (xother - xthis)*(xother - xthis);
		double yDiff = (yother - ythis)*(yother - ythis);
		double zDiff = (zother - zthis)*(zother - zthis);
		
		double distance = Math.sqrt(xDiff+yDiff+zDiff);

		return distance;
		
	}

	/**
	 *  returns distance between two coordinates in kilometer
	 *  using Haversine formula  https://en.wikipedia.org/wiki/Haversine_formula
	 */
	public double getHaversineDistance(Coordinate coord){
		
		assertArgumentNotNull(coord);
		
		SphericCoordinate coordSpheric;
		if( coord instanceof CartesianCoordinate) {
			coordSpheric = asSpheric((CartesianCoordinate)coord);
		}else{
			coordSpheric = (SphericCoordinate) coord;
		}

	    double latDiffRadian = Math.toRadians(getLatitudialDistance(coordSpheric));
	    double lonDiffRadian = Math.toRadians(getLongitudialDistance(coordSpheric));

	    double h = Math.pow(Math.sin(latDiffRadian / 2), 2)+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coordSpheric.getLatitude())) * Math.pow(Math.sin(lonDiffRadian / 2), 2);
	    double d = 2 * EARTHRADIUS * Math.asin(Math.sqrt(h));

	    return Math.abs(d);
		
	}
	
	/**
	 * @methodtype conversion
	 */
	public SphericCoordinate asSpheric(CartesianCoordinate coord){
		
		SphericCoordinate sphericCoord = new SphericCoordinate();
		
		double radius = Math.sqrt((coord.getX()*coord.getX())+(coord.getY()*coord.getY())+(coord.getZ()*coord.getZ()));
		
		sphericCoord.setLatitude(Math.acos(coord.getZ() / radius));
		sphericCoord.setLongitude(Math.atan(coord.getY()/coord.getX()));
		sphericCoord.setRadius(radius);
		
		return sphericCoord;
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
	 * @methodtype assertion
	 */
	private void assertRadiusRange(double d) throws IllegalArgumentException {
		if( d <= 0.0){
			throw new IllegalArgumentException("Radius must be larger than 0");
		}
	}

	/**
	 * @methodtype boolean query method
	 */
	@Override
	public boolean isEqual(Coordinate coord) {
		if (this == coord)
			return true;
		if (coord == null)
			return false;
		if (!(coord instanceof SphericCoordinate))
			return false;
		SphericCoordinate other = (SphericCoordinate) coord;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
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
