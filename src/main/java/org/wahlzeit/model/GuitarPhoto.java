package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class GuitarPhoto extends Photo {
	
	/**
	 * Guitar attributes
	 */
	private String manufacturer = "Unknown";
	private String guitarType = "Unknown";
	private int buildYear = 0;
	
	/**
	 * @methodtype constructor
	 */
	public GuitarPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public GuitarPhoto(PhotoId myId) {
		super(myId);
	}
	
	/**
	 * @methodtype constructor
	 */
	public GuitarPhoto(PhotoId myId, String manufacturer, int year) {
		super(myId);
		this.manufacturer = manufacturer;
		this.buildYear = year;
	}
	
	
	/**
	 * @methodtype get
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @methodtype set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @methodtype get
	 */
	public int getYear() {
		return buildYear;
	}

	/**
	 * @methodtype set
	 */
	public void setYear(int year) {
		this.buildYear = year;
	}

	/**
	 * @methodtype get
	 */
	public String getGuitarType() {
		return guitarType;
	}

	/**
	 * @methodtype set
	 */
	public void setGuitarType(String guitarType) {
		this.guitarType = guitarType;
	}

}
