package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Controller {

	/**
	 * This User Class ArrayList is for declare the program users
	 */
	ArrayList<User> users;

	/**
	 * method builder of the Controller Class
	 */
	public Controller() {
		users = new ArrayList<User>();

	}

	/**
	 * This control method is for register the users, according if they are premium or regular
	 * @param name user name
	 * @param id user id
	 * @param option 1. User regular, 2. User Premium
	 */
	public String registerUser(String name, String id, int option) {
		String msg="The user is correctly recorded "+name;
		//Generates the income date automatically
		Calendar dateLinkage=Calendar.getInstance();
		User user=null;
		switch(option){
			case 1->user=new Regular(name, id, dateLinkage);
			case 2->user=new Premium(name, id, dateLinkage);
		}
		users.add(user);
		return msg;
	}

	/**
	 * This control method is for search User by his id
	 * 
	 * @param id user id
	 * @return User 
	 */
	public User searchUser(String id){
		User user=null;
		boolean isFound=false;
		for (int i = 0; i < users.size()&&!isFound; i++) {
			if(users.get(i).getID().equalsIgnoreCase(id)){
				isFound=true;
			}
		}
		return user;
	}
	/**
	 * This method verify that not repeat the users
	 * @param id
	 * @return false= id User no exist, true= id user exist
	 */
	public boolean verifyNoRepeatUser(String id) {
		boolean repeat=false;
		if((searchUser(id)!=null)){
			repeat=true;
		}
		return repeat;
	}



	/**
	 * 
	 * @param hexa
	 * @param name
	 * @param amountPag
	 * @param datePublication
	 * @param url
	 * @param value
	 * @param emission
	 * @param type
	 */
	public String registerMagazine(String hexa, String name, int amountPag, Calendar datePublication, String url,
			double value, int emission, TypeMagazine type) {
		// TODO - implement Controller.registerMagazine
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hexa
	 * @param name
	 * @param amountPag
	 * @param datePublication
	 * @param url
	 * @param value
	 * @param type
	 */
	public String registerBook(String hexa, String name, int amountPag, Calendar datePublication, String url,
			double value, TypeBook type) {
		// TODO - implement Controller.registerBook
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public boolean verifyNoRepeatBibliographicProduct(String name) {
		// TODO - implement Controller.verifyNoRepeatBibliographicProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param amountPag
	 * @param datePublication
	 * @param url
	 * @param value
	 * @param typeProduct
	 * @param typeOption
	 */
	public String modifiedProductBibliographic(String name, int amountPag, Calendar datePublication, String url,
			double value, int typeProduct, int[] typeOption) {
		// TODO - implement Controller.modifiedProductBibliographic
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param emission
	 * @param type
	 */
	public String modifiedMagazine(int emission, TypeMagazine type) {
		// TODO - implement Controller.modifiedMagazine
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param type
	 */
	public String modifiedBook(TypeBook type) {
		// TODO - implement Controller.modifiedBook
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public String deleteProduct(String name) {
		// TODO - implement Controller.deleteProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public Bibliographic searchProduct(String name) {
		// TODO - implement Controller.searchProduct
		throw new UnsupportedOperationException();
	}

	public String testInit() {
		// TODO - implement Controller.testInit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param identificationProduct
	 */
	public String BuyProduct(String identificationProduct) {
		// TODO - implement Controller.BuyProduct
		throw new UnsupportedOperationException();
	}

}