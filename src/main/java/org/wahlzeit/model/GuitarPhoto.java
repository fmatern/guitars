package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class GuitarPhoto extends Photo {
	
	/**
	 * Guitar attributes
	 */
	private Guitar guitar;
	
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
	public GuitarPhoto(PhotoId myId, Guitar guitar) {
		super(myId);
		this.guitar = guitar;
	}
	
	/**
	 * @methodtype get
	 */
	public Guitar getGuitar(){
		return guitar;
	}
	
	/**
	 * @methodtype set
	 */
	public void setGuitar(Guitar g){
		this.guitar = g;
	}

}
