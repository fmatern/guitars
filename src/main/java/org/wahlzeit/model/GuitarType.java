package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GuitarType{
	
	private String type; /* e.g. electric or acoustic. Use super / subtypes to build hierachry e.g. acoustic->steel string->dreadnought */ 
	private String model; /* specify guitar model */
	private String manufacturer; /* specify manufacturer */
	
	private GuitarType superType = null;
	private Set<GuitarType> subTypes = new HashSet<GuitarType>();

	/**
	 * @methodtype constructor
	 */
	public GuitarType(GuitarType superType, String type, String model, String mf) {
		this.superType = superType;
		this.type = type;
		this.model = model;
		this.manufacturer = mf;
	}
	
	/**
	 * @methodtype constructor
	 */
	public GuitarType(String type, String model, String mf) {
		this.type = type;
		this.model = model;
		this.manufacturer = mf;
	}
	
	/**
	 * @methodtype constructor
	 */
	public GuitarType(String type) {
		this.type = type;
		this.model = null;
		this.manufacturer = null;
	}
	
	/**
	 * @methodtype factory
	 */
	public Guitar createInstance(){
		
		return new Guitar(this);
	}
	
	/**
	 * @methodtype set
	 */
	public void setSuperType(GuitarType gt){
		this.superType = gt;
	}
	
	/**
	 * @methodtype get
	 */
	public GuitarType getSuperType(){
		return superType;
	}
	
	/**
	 * @methodtype get
	 */
	public Iterator<GuitarType> getSubTypeIterator() {
		return subTypes.iterator();
	}

	/**
	 * @methodtype set
	 */
	public void addSubType(GuitarType gt){
		assert ( gt != null ) : "null subType is not allowed";
		gt.setSuperType(this);
		subTypes.add(gt);
	}
	
	/**
	 * @methodtype query
	 */
	public boolean hasInstance( Guitar g ) {
		assert( g != null) : "argument was null";
		
		if( g.getGuitarType() == this ){
			return true;
		}
		
		for( GuitarType type : subTypes) {
			if (type.hasInstance(g)){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @methodtype get
	 */
	public String getType() {
		return type;
	}

	/**
	 * @methodtype set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @methodtype get
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @methodtype set
	 */
	public void setModel(String model) {
		this.model = model;
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
	 * @methodtype conversion
	 */
	@Override
	public String toString() {
		return type +model + manufacturer;
	}
	
}
