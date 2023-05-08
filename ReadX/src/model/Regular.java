package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Regular extends User {

	// private ArrayList<Bill> billMagazine;
	// private ArrayList<Bill> billBook;

	/**
	 * this constructor method of the regular class
	 * 
	 * @param name        User name
	 * @param id          User id
	 * @param dateLinkage User income date
	 */
	public Regular(String name, String id, Calendar dateLinkage) {
		super(name, id, dateLinkage);
	}

	// /**
	// * This method is to add the purchasing invoices made and in force
	// * @return Book ArrayList
	// */
	// public ArrayList<Bill> getBillBook() {
	// return billBook;
	// }

	// /**
	// * This method is to add the purchasing invoices made and in force
	// * @return Magazine ArrayList
	// */
	// public ArrayList<Bill> getBillMagazine() {
	// return billMagazine;
	// }

	/**
	 * This method is responsible for counting the products bought from the regular
	 * user, with the purpose of not getting more of their parameters * @param
	 * option
	 * @param option 1.Book product or 2.Magazine Products
	 * @return Book or Magazine amount
	 */
	public int counterProduct(int option) {
		int amount = 0;
		removeBillWihtProductDelete();
		for (int i = 0; i < bills.size(); i++) {
			if (option == 1 && bills.get(i).getProduct() instanceof Book) {
				amount++;
			}
			if (option == 2 && bills.get(i).getProduct() instanceof Magazine) {
				amount++;
			}
		}
		return amount;
	}

}