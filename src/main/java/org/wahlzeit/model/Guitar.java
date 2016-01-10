package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;

public class Guitar{
	
	@Container
	protected GuitarType guitarType = null;
	
	private int buildYear = -1;
	private double price = -1.0;
	private boolean used = false;
	private String owner = null;

	private long id;
	protected static long ID = 0;
	
	/**
	 * @methodtype constructor
	 */
	public Guitar(GuitarType guitarType) {
		this.guitarType = guitarType;
		this.id = ID++;
	}
	
	/**
	 * @methodtype constructor
	 */
	public Guitar(GuitarType guitarType, int buildYear, float price, String owner, boolean used) {
		this.guitarType = guitarType;
		this.buildYear = buildYear;
		this.price = price;
		this.owner = owner;
		this.used = used;
		this.id = ID++;
	}

	/**
	 * @methodtype get
	 */
	public int getBuildYear() {
		return buildYear;
	}

	/**
	 * @methodtype set
	 */
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	/**
	 * @methodtype get
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @methodtype set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @methodtype get
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @methodtype set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * @methodtype get
	 */
	public boolean getUsed() {
		return used;
	}

	/**
	 * @methodtype set
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * @methodtype get
	 */
	public GuitarType getGuitarType() {
		return guitarType;
	}

	/**
	 * @methodtype set
	 */
	public void setGuitarType(GuitarType guitarType) {
		assert(guitarType != null) : "null argument not allowed";
		this.guitarType = guitarType;
	}
	
	/**
	 * @methodtype get
	 */
	public long getID() {
		return id;
	}

	
}
