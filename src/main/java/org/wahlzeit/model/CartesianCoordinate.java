package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate{

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
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		//pre-condition
		assertNotNaN(x);
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
		//pre-condition
		assertNotNaN(y);
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
		//pre-condition
		assertNotNaN(z);
		this.z = z;
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
	
	/**
	 * @methodtype assertion
	*/
	protected void assertNotNaN(double d) throws IllegalArgumentException {
		if (Double.isNaN(d)) {
			throw new IllegalArgumentException("Argument was NaN!");
		}
	}
	

}
