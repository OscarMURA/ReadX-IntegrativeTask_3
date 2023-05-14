package model;

import java.util.Calendar;
import java.util.ArrayList;

/**
 * The Regular class extends the User class and has a method to count the number of products bought by
 * the user.
 */
public class Regular extends User {


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
		for (int i = 0; i < bills.size(); i++) {//Loop to tour Bills's arrangement
			for (int j = 0; j < bills.get(i).getProducts().size(); j++) {//Loop to tour the arrangement of bibliographic products
				if (option==1 && bills.get(i).getProducts().get(j) instanceof Book){
					amount++;
				}
				if (option==2 && bills.get(i).getProducts().get(j) instanceof Magazine){
					amount++;
				}
			}
			
		}
		return amount;
	}

}