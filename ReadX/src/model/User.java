
package model;

import java.util.Calendar;
import java.util.ArrayList;

public abstract class User {

	private String name;
	private String ID;
	private Calendar dateLinkage;
	protected ArrayList<Bill> bills;

	/**
	 * this constructor method of the parent User class
	 * 
	 * @param name        User name
	 * @param id          User id
	 * @param dateLinkage User income date
	 */
	public User(String name, String id, Calendar dateLinkage) {
		bills = new ArrayList<Bill>();
		this.name = name;
		this.ID = id;
		this.dateLinkage = dateLinkage;
	}

	/**
	 * this view method
	 * @return the id user
	 */
	public String getID() {
		return ID;
	}

	/**
	 * this view method
	 * 
	 * @return the name user
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method verifies if there is already the bibliographic product in the
	 * user library
	 * 
	 * @param wordKey Bibliographic product name or id
	 * @return true=exist or false: no exist
	 */
	public boolean alreadyHasProduct(String wordKey) {
		boolean exist = false;
		for (int i = 0; i < bills.size() && !exist; i++) {
			Bibliographic product = bills.get(i).getProduct();
			if (product.getCodeId().equalsIgnoreCase(wordKey) || product.getName().equalsIgnoreCase(wordKey)) {
				exist = true;
			}
		}
		return exist;
	};

	/**
	 * This method is to add the purchasing invoices made and in force
	 * @return Bill ArrayList
	 */
	public ArrayList<Bill> getBills() {
		removeBillWihtProductDelete();
		return bills;
	}

	/**
	 * This method is responsible for eliminating products that are no
	 * longer in force in the market
	 */
	protected void removeBillWihtProductDelete() {
		boolean isFound=false;
		for (int i = 0; i < bills.size()&&!isFound; i++) {
			if (bills.get(i).getProduct() == null) {
				bills.remove(bills.get(i));
				isFound=true;
			}
		}
	}

	public String romoveMagazineSubscrition(String wordKey) {
		String msg = "";
		boolean isFound=false;
		for (int i = 0; i < bills.size()&&!isFound; i++) {
			if (bills.get(i).getProduct().getCodeId().equalsIgnoreCase(wordKey)
				|| bills.get(i).getProduct().getName().equalsIgnoreCase(wordKey)) {
				bills.remove(bills.get(i));
				isFound=true;
			}
		}
		return msg;
	}

}