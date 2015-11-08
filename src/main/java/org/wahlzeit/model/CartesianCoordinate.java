package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate{

	private double x;
	private double y;
	private double z;
	
	
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * @methodtype 
	 */
	public double getHaversineDistance(Coordinate coord){
		
		//TODO transform to spheric call function, return
		
		return 0.0;
	}

	/**
	 * @methodtype 
	 */
	public double getDistance(Coordinate coord){
		
		assertArgumentNotNull(coord);
		
		CartesianCoordinate coordCart;
		if( coord instanceof SphericCoordinate) {
			coordCart = asCartesian((SphericCoordinate)coord);
		}else{
			coordCart = (CartesianCoordinate) coord;
		}
		
		double xDiff = (coordCart.getX()-this.x)*(coordCart.getX()-this.x);
		double yDiff = (coordCart.getY()-this.y)*(coordCart.getY()-this.y);
		double zDiff = (coordCart.getZ()-this.z)*(coordCart.getZ()-this.z);
		
		double distance = Math.sqrt(xDiff+yDiff+zDiff);
		
		return distance;
	}
	
	/**
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesian(SphericCoordinate coord){
		
		CartesianCoordinate cartCoord = new CartesianCoordinate();
		cartCoord.setX(coord.getRadius()*Math.sin(coord.getLatitude())*Math.cos(coord.getLongitude()));
		cartCoord.setY(coord.getRadius()*Math.sin(coord.getLatitude())*Math.sin(coord.getLongitude()));
		cartCoord.setZ(coord.getRadius()*Math.cos(coord.getLatitude()));
		
		return cartCoord;
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
	 * @methodtype boolean query method
	 */
	@Override
	public boolean isEqual(Coordinate coord) {
		if (this == coord)
			return true;
		if (coord == null)
			return false;
		if (!(coord instanceof CartesianCoordinate))
			return false;
		CartesianCoordinate other = (CartesianCoordinate) coord;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
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
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

}
