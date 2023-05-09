package model;
import java.util.Calendar;
public class Book extends Bibliographic {

	private String review;
	private TypeBook type;

	/**
	 * this buiilder method of the Book class
	 * @param codeId Bibliographic product CodeId
	 * @param name Bibliographic product name
	 * @param amountPag Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url Bibliographic product url
	 * @param value Bibliographic product value of the price or subscrition of the product
	 * @param type book type
	 */
	public Book(String codeId, String name, int amountPag, Calendar datePublication, String url, double value, TypeBook type, String review) {
		super(codeId, name, amountPag, datePublication, url, value);
		this.review=review;
		this.type=type;
	}

	
	/**
	 * @return the book type
	 */
	public TypeBook getTypeBook(){
		return this.type;
	}
	/**
	 * This method save the new type of the book
	 * @param type typeBook
	 */
	public void setType(TypeBook type) {
		this.type = type;
	}

	/**
	 * This enum
	 */


}