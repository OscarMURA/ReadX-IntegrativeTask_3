package model;
import java.util.Calendar;
public abstract class Bibliographic {

	private String name;
	private int amountPag;
	private Calendar datePublication;
	private String url;
	private double value;

	private String codeId;

	/**
	 * this buiilder method of the Bibliographic class
	 * @param codeId Bibliographic product CodeId
	 * @param name Bibliographic product name
	 * @param amountPag Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url Bibliographic product url
	 * @param value Bibliographic product value of the price or subscrition of the product
	 */
	public Bibliographic(String codeId, String name, int amountPag, Calendar datePublication, String url, double value) {
		this.name=name;
		this.codeId=codeId;
		this.amountPag=amountPag;
		this.datePublication=datePublication;
		this.url=url;
		this.value=value;
	}
	/**
	 * This control method 
	 * @return Bibliographic name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This control method
	 * @return Bibliographic identification id
	 */
	public String getCodeId() {
		return codeId;
	}

}