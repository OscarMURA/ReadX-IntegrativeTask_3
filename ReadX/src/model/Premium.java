package model;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * The Premium class extends the User class and has a constructor method that takes in the user's name,
 * id, and income date.
 */

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

}
