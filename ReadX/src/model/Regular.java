package model;
import java.util.Calendar;

public class Regular extends User {

	/**
	 * this constructor method of the regular class
	 * @param name User name
	 * @param id User id
	 * @param dateLinkage User income date
	 */
	public Regular(String name, String id, Calendar dateLinkage) {
		super(name, id, dateLinkage);
	}

	/**
	 * 
	 * @param name
	 * @param hexa
	 */
	public String addProduct(String name, String hexa) {
		// TODO - implement Regular.addProduct
		throw new UnsupportedOperationException();
	}

}