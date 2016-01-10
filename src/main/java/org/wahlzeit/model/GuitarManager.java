package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

public class GuitarManager {

	/* Singleton Manager, to handle GuitarTypes */
	protected static final GuitarManager instance = new GuitarManager();
	protected Map<String, GuitarType> guitarTypes = new HashMap<String, GuitarType>();
	protected Map<Long, Guitar> guitars = new HashMap<Long, Guitar>();
	
	/**
	 * @methodtype get
	 */
	public static final GuitarManager getInstance() {
		return instance;
	}
	
	/**
	 * @methodtype convenience
	 */
	public Guitar createGuitar(String type){
		return createGuitar(type,null,null);
	}
	
	/**
	 * @methodtype factory method
	 */
	public Guitar createGuitar(String type, String model, String mf){
		GuitarType gt = getGuitarType(type, model, mf);
		assert (gt != null) : "invalid GuitarType type";
		Guitar result = gt.createInstance();
		guitars.put(result.getID(), result);
		return result;
	}
	
	/**
	 * @methodtype convenience
	 */
	public GuitarType getGuitarType(String type){
		return getGuitarType(type,null,null);
	}
	
	/**
	 * @methodtype factory method
	 */
	public GuitarType getGuitarType(String type, String model, String mf){
		
		GuitarType result = guitarTypes.get(type+model+mf);	
		if (result == null) {
			synchronized (guitarTypes) {
				result = guitarTypes.get(type+model+mf);
				if (result == null) {
					result = new GuitarType(type,model,mf);
					guitarTypes.put(result.toString(), result);
				}
			}
		}
		return result;
	}
	

}
