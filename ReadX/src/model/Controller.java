package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

	/**
	 * This User Class ArrayList is for declare the program users
	 */
	ArrayList<User> users;
	ArrayList<Bibliographic> bibliographics;
	ArrayList<String> codeId;

	/**
	 * method builder of the Controller Class
	 */
	public Controller() {
		users = new ArrayList<User>();
		bibliographics = new ArrayList<Bibliographic>();
		codeId = new ArrayList<String>();
	}

	/**
	 * This control method is for register the users, according if they are premium
	 * or regular
	 * 
	 * @param name   user name
	 * @param id     user id
	 * @param option 1. User regular, 2. User Premium
	 */
	public String registerUser(String name, String id, int option) {
		String msg = "The user is correctly recorded " + name;
		// Generates the income date automatically
		Calendar dateLinkage = Calendar.getInstance();
		User user = null;
		switch (option) {
			case 1 -> user = new Regular(name, id, dateLinkage);
			case 2 -> user = new Premium(name, id, dateLinkage);
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
	public User searchUser(String id) {
		User user = null;
		boolean isFound = false;
		for (int i = 0; i < users.size() && !isFound; i++) {
			if (users.get(i).getID().equalsIgnoreCase(id)) {
				isFound = true;
				user = users.get(i);
			}
		}
		return user;
	}

	/**
	 * This method verify that not repeat the users
	 * 
	 * @param id
	 * @return false= id User no exist, true= id user exist
	 */
	public boolean verifyNoRepeatUser(String id) {
		boolean repeat = false;
		if ((searchUser(id) != null)) {
			repeat = true;
		}
		return repeat;
	}

	/**
	 * This control method create and add a new biblipgraphic product
	 * 
	 * @param option          Flag variable to know if 1.book or 2.Magazine is
	 *                        created
	 * @param codeId          Bibliographic product CodeId
	 * @param name            Bibliographic product name
	 * @param amountPag       Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url             Bibliographic product url
	 * @param value           Bibliographic product value of the price or
	 *                        subscrition of the product
	 * @param emission        The amount of duration in the emission of the magazine
	 * @param type            varFlag for know Book type or Mazazine type
	 * @return
	 */
	public String registerBibliographicProduct(int option, String name, int amountPag,
			Calendar datePublication, String url, double value, int emission, int type) {

		String codeId = generationAlfaAndHexaDecimal(option);
		String msg = "The " + name + " product was registered sucessfully";
		Bibliographic bibliographic = null;
		switch (option) {
			case 1 -> {
				TypeBook typeBook = null;

				switch (type) {
					case 1 -> typeBook = TypeBook.SCIENCE_FICTION;
					case 2 -> typeBook = TypeBook.FANTASY;
					case 3 -> typeBook = TypeBook.HISTORICAL_NOVEL;
				}
				bibliographic = new Book(codeId, name, amountPag, datePublication, url, value, typeBook);
				msg += " such as a Book. Aditional, its code Hexadecimal is " + codeId;
			}
			case 2 -> {
				TypeMagazine typeMagazine = null;
				switch (type) {
					case 1 -> typeMagazine = TypeMagazine.VARIETIES;
					case 2 -> typeMagazine = TypeMagazine.DESING;
					case 3 -> typeMagazine = TypeMagazine.SCIENTIST;
				}
				bibliographic = new Magazine(codeId, name, amountPag, datePublication, url, value, emission,
						typeMagazine);
				msg += " such as a Magazine. Aditional, its code Alphanumeric is " + codeId;
			}
		}
		this.bibliographics.add(bibliographic);

		return msg;
	}

	/**
	 * This method verify that not repeat the bibliographicProduct name
	 * 
	 * @param name bibliographicProduct name
	 * @return false= bibliographicProduct name no exist, true= bibliographicProduct
	 *         name exist
	 */
	public boolean verifyNoRepeatProduct(String name) {
		boolean repeat = false;
		if (searchBibliographic(name) != null) {
			repeat = true;
		}

		return repeat;
	}

	/**
	 * This control method is for search the bibliographicProduct by its name
	 * 
	 * @param wordKey bibliographicProduct name or identification id
	 * @return bibliographicProduct, if it exist
	 */
	public Bibliographic searchBibliographic(String wordKey) {
		Bibliographic product = null;
		boolean isFound = false;
		for (int i = 0; i < bibliographics.size() && !isFound; i++) {
			if ((bibliographics.get(i).getName().equalsIgnoreCase(wordKey))
					|| (bibliographics.get(i).getCodeId().equalsIgnoreCase(wordKey))) {
				product = bibliographics.get(i);
				isFound = true;
			}
		}
		return product;
	}

	/**
	 * This control method is for generate a unique code Hexadecimal or alphanumeric
	 * to bibliographic product
	 * 
	 * @param option Type product--1.Book (codeHexa)--2.Magazine(codeAlphanumeric)
	 * @return the code identification of the product
	 */
	public String generationAlfaAndHexaDecimal(int option) {
		String msg;
		boolean exist = false;
		Random random = new Random();
		int elecction = 0;
		char[] word = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		do {
			msg = "";

			for (int i = 0; i < 3; i++) {// Loop to create codeIdentification for the product
				elecction = random.nextInt(2) + 1;// Two numbers leave 1. to add letter or 2 to add number to the code
				switch (elecction) {
					case 1 -> {

						switch (option) {
							case 1 -> elecction = random.nextInt(6);// A-f Range-CodeHexadecimal
							case 2 -> elecction = random.nextInt(word.length);// A-Z range-CodeAlphanumeric
						}
						msg += word[elecction];
					}
					case 2 -> {
						int number = random.nextInt(10);
						msg += String.valueOf(number);
					}
				}
			}
			for (int i = 0; i < codeId.size() && !exist; i++) {
				if (codeId.get(i).equalsIgnoreCase(msg)) {
					exist = true;
				}
			}
		} while (exist);

		codeId.add(msg);
		return msg;

	}

	/**
	 * This control method is for decide the type intance the of bibliographic
	 * product
	 * @param wordKey bibliographicProduct name or identification id
	 * @return 1.Book intance or 2.Magazine Intance
	 */
	public int decideIntanceOfBibliographic(String wordKey) {
		int infaceOf = 0;
		Bibliographic product = null;
		product = searchBibliographic(wordKey);

		if (product instanceof Book) {
			infaceOf = 1;
		} else {
			infaceOf = 2;
		}
		return infaceOf;
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