package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class GuitarPhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(GuitarPhotoFactory.class.getName());
	
	private static GuitarPhotoFactory instance = null;

	public GuitarPhotoFactory() {

	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	
	public static synchronized GuitarPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting GuitarPhotoFactory").toString());
			setInstance(new GuitarPhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(GuitarPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new GuitarPhoto();
	}
	
	/**
	 * Creates a new photo with the specified id
	 */
	public Photo createPhoto(PhotoId id) {
		return new GuitarPhoto(id);
	}
	
	/**
	 * Creates a new photo with the specified id
	 */
	public Photo createPhoto(PhotoId id, Guitar guitar) {
		return new GuitarPhoto(id, guitar);
	}
	

}
