package model;
import java.util.Calendar;
public class Premium extends User {

	/**
	 * this constructor method of the premium class
	 * @param name User name
	 * @param id User id
	 * @param dateLinkage User income date
	 */
	public Premium(String name, String id, Calendar dateLinkage){
		super(name, id, dateLinkage);
	}

	/**
	 * 
	 * @param name
	 * @param hexa
	 */
	public String addProduct(String name, String hexa) {
		// TODO - implement Premium.addProduct
		throw new UnsupportedOperationException();
	}

}