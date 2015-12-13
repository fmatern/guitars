package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate{

	private final double x;
	private final double y;
	private final double z;
	
	
	/**
	 * @methodtype constructor
	 */
	private CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	/**
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		
		//pre-condition
		assertNotNaN(x);
		assertNotNaN(y);
		assertNotNaN(z);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		//class-invariants
		assertClassInvariants();
	}
	
	/**
	 * @methodtype convenience
	 */
	public static CartesianCoordinate getInstance(){
		return getInstance(0.0,0.0,0.0);
	}
	/**
	 * @methodtype factory method
	 */
	public static CartesianCoordinate getInstance(double x, double y, double z){
		CartesianCoordinate wantedCoord = new CartesianCoordinate(x, y, z);
		int hashCode = wantedCoord.hashCode();
		AbstractCoordinate result = instances.get(hashCode);
		
		if (result == null) {
			synchronized (instances) {
				result = instances.get(hashCode);
				if (result == null) {
					result = wantedCoord;
					instances.put(hashCode, result);
				}
			}
		}
		
		return (CartesianCoordinate)result;
	}
	
	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * @methodtype comparison
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * @methodtype assertion
	*/
	protected void assertNotNaN(double d) throws IllegalArgumentException {
		if (Double.isNaN(d)) {
			throw new IllegalArgumentException("Argument was NaN!");
		}
	}

}
