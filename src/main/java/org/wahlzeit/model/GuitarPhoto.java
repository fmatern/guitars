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
	

	public GuitarPhoto() {
		super();
	}

	public GuitarPhoto(PhotoId myId) {
		super(myId);
	}
	
	public GuitarPhoto(PhotoId myId, String manufacturer, int year) {
		super(myId);
		this.manufacturer = manufacturer;
		this.buildYear = year;
	}
	
	
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return buildYear;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.buildYear = year;
	}

	/**
	 * @return the guitarType
	 */
	public String getGuitarType() {
		return guitarType;
	}

	/**
	 * @param guitarType the guitarType to set
	 */
	public void setGuitarType(String guitarType) {
		this.guitarType = guitarType;
	}

}
