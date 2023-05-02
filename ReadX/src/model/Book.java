package model;
import java.util.Calendar;
public class Book extends Bibliographic {

	/**
	 * this buiilder method of the Book class
	 * @param codeId Bibliographic product CodeId
	 * @param name Bibliographic product name
	 * @param amountPag Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url Bibliographic product url
	 * @param value Bibliographic product value of the price or subscrition of the product
	 * @param typeBook book type
	 */
	public Book(String codeId, String name, int amountPag, Calendar datePublication, String url, double value, model.TypeBook typeBook) {
		super(codeId, name, amountPag, null, url, value);
	}

	public enum TypeBook{

		SCIENCE_FICTION("Science Fiction"),
		FANTASY("Fantasy"),
		HISTORICAL_NOVEL("Historical Novel");
	
		private String value;
	
		TypeBook(String value){
			this.value=value;
		}
		public String getValue(){
			return value;
		}
		
	}

}