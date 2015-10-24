package org.wahlzeit.model;


/**
 * A coordinate represents the location a photo was taken
 */
public class Coordinate {
	
	private double latitude;
	private double longitude;

	public Coordinate(double latitude, double longitude) {
		
		if( latitude > 90.0 || latitude < -90.0){
	        throw new IllegalArgumentException("Latitude must be between -90 and 90");
		}
		if( longitude > 180.0 || longitude < -180.0){
	        throw new IllegalArgumentException("Longitude must be between -180 and 180");
		}
		
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Coordinate() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double d) {
		if( d > 90.0 || d < -90.0){
		        throw new IllegalArgumentException("Latitude must be between -90 and 90");
		}
		this.latitude = d;	
	}

	public void setLongitude(double d) {
		if( d > 180.0 || d < -180.0){
	        throw new IllegalArgumentException("Longitude must be between -180 and 180");
	}
		this.longitude = d;	
	}

	public double getLatitudialDistance(Coordinate coord) {
		return ( this.latitude - coord.getLatitude() );
	}

	public double getLongitudialDistance(Coordinate coord) {
		return ( this.longitude - coord.getLongitude() );
	}

	public Coordinate getDistance(Coordinate coord){
		
		//TODO calculations, nullpointer exception
		Coordinate returnCoord = new Coordinate();
		
		return returnCoord;
	}
	
	
}
