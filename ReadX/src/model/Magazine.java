package model;
import java.util.Calendar;

public class Magazine extends Bibliographic {
	private TypeMagazine type;
	private Emission emission;

	/**
	 * this buiilder method of the Magazine class
	 * @param codeId Bibliographic product CodeId
	 * @param name Bibliographic product name
	 * @param amountPag Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url Bibliographic product url
	 * @param value Bibliographic product value of the price or subscrition of the product
	 * @param emission The amount of duration in the emission of the magazine
	 * @param type Mazazine type
	 */
	public Magazine(String codeId, String name, int amountPag, Calendar datePublication, String url, double value, Emission emission, TypeMagazine type) {
		super(codeId, name, amountPag, datePublication, url, value);
		this.type=type;
		this.emission=emission;
	}
	
	/**
	 * This control method save the new TypeMagazine of the Magazine
	 * @param type TypeMagazine
	 */
	public void setType(TypeMagazine type) {
		this.type = type;
	}

	/**
	 * The control method save the new emission type
	 * @param emission
	 */
	public void setEmission(Emission emission) {
		this.emission = emission;
	}

}