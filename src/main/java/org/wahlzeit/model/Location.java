package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;

public class Location {

	private String name = "";
	@Container
	private Coordinate coordinate = null;
	
	
	/**
	 * @methodtype constructor
	 */
	public Location() {
		this.name = "name unknown";
		this.coordinate = SphericCoordinate.getInstance();
	}
	
	/**
	 * @methodtype constructor
	 */
	public Location(String name, SphericCoordinate coord) {
		
		assertArgumentNotNull(coord);
		this.name = name;
		this.coordinate = coord;
	}
	
	/**
	 * @methodtype constructor
	 */
	public Location(String name, double lat, double lon) {
		this.name = name;
		this.coordinate = SphericCoordinate.getInstance(lat,lon);
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @methodtype set
	 */
	public void setCoordinate(SphericCoordinate locationCoord) {
		
		assertArgumentNotNull(locationCoord);
		this.coordinate = locationCoord;
	}

	/**
	 * @methodtype assertion
	 */
	private void assertArgumentNotNull(SphericCoordinate coord) throws IllegalArgumentException {
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
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @methodtype boolean query method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
