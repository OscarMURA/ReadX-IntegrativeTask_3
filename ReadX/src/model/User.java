
package model;
import java.util.Calendar;


public abstract class User {

	private String name;
	private String ID;
	private Calendar dateLinkage;

	/**
	 * 
	 * @param name
	 * @param id
	 * @param dateLinkage
	 */
	public User(String name, String id, Calendar dateLinkage) {
		this.name=name;
		this.ID=id;
		this.dateLinkage=dateLinkage;
	}

	/**
	 * 
	 * @param name
	 * @param hexa
	 */
	public abstract String addProduct(String name, String hexa);

	/**
	 * this view method
	 * @return the name user
	 */
	public String getID() {
		return ID;
	}

}