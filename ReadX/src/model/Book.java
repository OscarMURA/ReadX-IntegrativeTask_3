package model;
import java.util.Calendar;
/**
 * The Book class extends the Bibliographic class and adds properties such as review and type, and
 * methods for getting and setting these properties.
 */
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
	 * This method has the functionality of cloning the object of the class
	 * @param product  object to copy
	 */
	public Book(Bibliographic product){
		super(product.getCodeId(),product.getName(),product.getAmountPag(),product.getDatePublication(),product.getUrl(),product.getValue());
		this.review=((Book)product).getReview();
		this.type=((Book)product).getTypeBook();
	}

	/**
	 * The function returns the type of a book.
	 * 
	 * @return The method `getType()` is returning an object of type `TypeBook`.
	 */
	public TypeBook getType() {
		return type;
	}
	
	/**
	 * @return the book type
	 */
	public TypeBook getTypeBook(){
		return type;
	}
	/**
	 * This method save the new type of the book
	 * @param type typeBook
	 */
	public void setType(TypeBook type) {
		this.type = type;
	}

	public String getReview() {
		return review;
	}
	@Override
	public String getData(){
		String msg="";
		msg+="\n\t\033[47;35m \3 "+name+"    Code: "+codeId+" \3 \033[0m \n";
		msg+="\n\4Pag: "+amountPag;
		msg+="\n\4Date: "+datePublication.getTime();
		msg+="\n\4Url: "+url;
		msg+="\n\4Type: "+type;
		msg+="\n\4Review: "+review+"\n";
		return msg;
	}

}