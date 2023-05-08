package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;
import model.Book.TypeBook;
import model.Magazine.Emission;
import model.Magazine.TypeMagazine;

public class Controller {

	/**
	 * This User Class ArrayList is for declare the program users
	 */
	ArrayList<User> users;
	/**
	 * This Bibliographic Class Arralist is for declare the program users
	 */
	ArrayList<Bibliographic> bibliographics;
	private String msg;
	private Random random;
	private User currentUser;

	/**
	 * method builder of the Controller Class
	 */
	public Controller() {
		users = new ArrayList<User>();
		bibliographics = new ArrayList<Bibliographic>();
		msg = "";
		random = new Random();
		currentUser = null;
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
		msg = "The user is correctly recorded " + name;
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
			if (users.get(i).getID().equalsIgnoreCase(id) || users.get(i).getName().equalsIgnoreCase(id)) {
				isFound = true;
				user = users.get(i);
				currentUser = user;
			}
		}
		return user;
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
			Calendar datePublication, String url, double value, int emission, int type, String review) {
		msg = "The " + name + " product was registered sucessfully";

		String codeId = generationAlfaAndHexaDecimal(option);
		Bibliographic bibliographic = null;
		switch (option) {
			case 1 -> {
				TypeBook typeBook = null;
				typeBook = assignTypeBook(type);
				bibliographic = new Book(codeId, name, amountPag, datePublication, url, value, typeBook, review);
				msg += "\nsuch as a Book ";
			}
			case 2 -> {
				TypeMagazine typeMagazine = null;
				Emission typeEmmision = null;
				typeMagazine = assignTypeMagazine(type);
				typeEmmision = assignTypeEmission(emission);
				bibliographic = new Magazine(codeId, name, amountPag, datePublication, url, value, typeEmmision,
						typeMagazine);
				msg += "\nsuch as a Magazine ";
			}
		}
		this.bibliographics.add(bibliographic);
		msg += ". Aditional, its code Hexadecimal is " + codeId;
		return msg;
	}

	/**
	 * This method is for assign the type that is the Magazine
	 * 
	 * @param type varaible flag to indicate the type
	 * @return The Magazine type
	 */
	public TypeMagazine assignTypeMagazine(int type) {
		TypeMagazine typeMagazine = null;
		switch (type) {
			case 1 -> typeMagazine = TypeMagazine.VARIETIES;
			case 2 -> typeMagazine = TypeMagazine.DESING;
			case 3 -> typeMagazine = TypeMagazine.SCIENTIST;
		}
		return typeMagazine;
	}

	/**
	 * This method is for assign the type emission of the Magazine
	 * 
	 * @param type varaible flag to indicate the typeEmission
	 * @return The type Emission
	 */
	public Emission assignTypeEmission(int emission) {
		Emission typeEmmision = null;
		switch (emission) {
			case 1 -> typeEmmision = Emission.MONTHLY;
			case 2 -> typeEmmision = Emission.QUARTERLY;
			case 3 -> typeEmmision = Emission.BIANNUAL;
			case 4 -> typeEmmision = Emission.ANNUAL;
		}
		return typeEmmision;
	}

	public TypeBook assignTypeBook(int type) {
		TypeBook typeBook = null;

		switch (type) {
			case 1 -> typeBook = TypeBook.SCIENCE_FICTION;
			case 2 -> typeBook = TypeBook.FANTASY;
			case 3 -> typeBook = TypeBook.HISTORICAL_NOVEL;
		}
		return typeBook;
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
		String hexDigits = "0123456789ABCDEF", alphaDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String msg = "";
		boolean exist = false;
		String source = (option == 1) ? hexDigits : alphaDigits;
		String codeId = "";
		do {
			msg = "";
			for (int i = 0; i < 3; i++) {
				msg += source.charAt(random.nextInt(source.length()));
			}
			for (int i = 0; i < bibliographics.size() && !exist; i++) {
				codeId = bibliographics.get(i).getCodeId();
				if (codeId.equalsIgnoreCase(msg)) {
					exist = true;
				}
			}
		} while (exist);
		return msg;
	}

	/**
	 * This control method is for decide the type intance the of bibliographic
	 * product
	 * 
	 * @param wordKey bibliographicProduct name or identification id
	 * @return 1.Book intance or 2.Magazine Intance
	 */
	public int intanceOfBibliographic(String wordKey) {
		int infaceOf = -1;
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
	 * This control method create and add a new biblipgraphic product
	 * 
	 * @param wordKey         Bibliographic product codeIdentification or name
	 * @param name            Bibliographic product name
	 * @param amountPag       Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url             Bibliographic product url
	 * @param value           Bibliographic product value of the price or
	 *                        subscrition of the product
	 * @param emission        The amount of duration in the emission of the magazine
	 * @param typeProduct     varFlag for know Book type or Mazazine type
	 * @return
	 */
	public String modifiedProductBibliographic(String wordKey, String name, int amountPag, Calendar datePublication,
			String url, double value, int emission, int typeProduct) {

		msg = "";
		Bibliographic product = null;
		product = searchBibliographic(wordKey);

		if (!(name.equals(""))) {
			product.setName(name);
			msg += "\n Name \2";
		}
		if (amountPag != 0) {
			product.setAmountPag(amountPag);
			msg += "\n Amount page \2";
		}
		if (datePublication != null) {
			product.setDatePublication(datePublication);
			msg += "\n Date publication \2";
		}
		if (value != 0) {
			product.setValue(value);
			msg += "\n Product or suscription product \2";
		}
		if (!(url.equals(""))) {
			product.setUrl(url);
			msg += "\n Url \2";
		}
		if (emission != 0) {
			Emission typeEmission = assignTypeEmission(emission);;
			((Magazine) product).setEmission(typeEmission);
			msg += "\n Emission \2";
		}
		if (typeProduct != 0) {
			if (product instanceof Book) {
				TypeBook typeBook = assignTypeBook(typeProduct);
				((Book) product).setType(typeBook);

			} else if (product instanceof Magazine) {
				TypeMagazine typeMagazine = assignTypeMagazine(typeProduct);
				((Magazine) product).setType(typeMagazine);
			}
			msg += "\n Type product \2";
		}
		if (!(msg.equals(""))) {
			msg = "Update sucessfully of product: " + msg;
		} else {
			msg = "There are no changes";
		}
		return msg + "\n";
	}

	/**
	 * This cotrol method delete the me
	 * 
	 * @param wordKey
	 */
	public String deleteProduct(String wordKey) {
		Bibliographic product = searchBibliographic(wordKey);
		bibliographics.remove(product);
		return "The " + wordKey + " was delete";
	}

	/**
	 * This Control Method performs a start test to create 10 regular users and
	 * premium, also for books and magazines
	 * 
	 * @return The information of the object create
	 */
	public String testInit() {
		msg = "";
		int j = 0;
		int amountPag = 0, type = 0, emission = 0;
		String product = "", name = "", url = "", review = "NN", id = "";
		double productValue = 0;
		Calendar date = Calendar.getInstance();
		Bibliographic bibliographic = null;
		User user = null;

		for (j = 0; j < 10; j++) {
			name = "Regular " + (j);
			id = String.valueOf(random.nextInt((int) 1e6));
			user = new Regular(name, id, date);
			users.add(user);
			msg += "\4User: " + name + "\tid: " + id + "\n";

			name = "Premium " + j;
			id = String.valueOf(random.nextInt((int) 1e6));
			user = new Premium(name, id, date);
			users.add(user);
			msg += "\4User: " + name + "\tid: " + id + "\n";

			for (int i = 1; i <= 2; i++) {
				product = (i == 1) ? "Book" : "Magazine";
				name = product + " " + j;
				amountPag = random.nextInt(1000) + 1;
				type = random.nextInt(3) + 1;
				id = generationAlfaAndHexaDecimal(i);
				url = generationAlfaAndHexaDecimal(i);
				url = url + ".PNG";
				productValue = 10000 + random.nextInt(100000);
				// Generates dates since 1900
				int day = random.nextInt(31) + 1;
				int month = random.nextInt(12) + 1;
				int year = random.nextInt(123) + 1900;
				date.set(year, month, day);
				if (i == 1) {
					TypeBook typeBook = assignTypeBook(type);
					bibliographic = new Book(id, name, amountPag, date, url, productValue, typeBook, review);
				} else {
					emission = random.nextInt(4) + 1;
					Emission typeEmission = assignTypeEmission(emission);
					TypeMagazine typeMagazine = assignTypeMagazine(type);
					bibliographic = new Magazine(id, name, amountPag, date, url, productValue, typeEmission,
							typeMagazine);
				}
				msg += "\4" + name + "->  code: " + id + "\n";
				bibliographics.add(bibliographic);
			}
			msg += "\n\n";
		}
		return msg;
	}

	/**
	 * This control method
	 * 
	 * @return Returns to the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	/**
	 * 
	 * @param wordKey
	 */
	public String BuyProduct(String wordKey, double value) {
		Calendar buyDate = Calendar.getInstance();
		msg = "";
		int pos = 0;
		int intance = intanceOfBibliographic(wordKey);
		String typeBook = "";
		typeBook = (intance == 1) ? " Purchase of the book: " : " Subscription of the Magazine: ";
		Bibliographic product = searchBibliographic(wordKey);
		ArrayList<Bill> bill;

		msg = "We already registered you " + typeBook + product.getName() + " To your gallery";

		if ((intance == 1 && ((Regular) currentUser).counterProduct(1) < 5)
				|| (intance == 2 && ((Regular) currentUser).counterProduct(2) < 2)
				|| (currentUser instanceof Premium)) {
			bill = currentUser.getBills();
			bill.add(new Bill(String.valueOf(value), buyDate));
			pos = bill.size() + (-1);// Returns the position of the last added data
			pos = (pos == -1) ? 0 : pos;
			bill.get(pos).setProduct(product);
			product.addBill(bill.get(pos));
		} else {
			msg = "We couldn't record your" + typeBook + product.getName() + " By already you have full storage";
		}

		return msg;
	}

	public String deleteMagazineSubscrition(String wordKey){
		msg="";
		boolean isFound=false;
		ArrayList<Bill> bills=currentUser.getBills();
		for (int i = 0; i < bills.size()&&!isFound; i++) {
			Bibliographic magazine=bills.get(i).getProduct();
			if(magazine.getCodeId().equalsIgnoreCase(wordKey)|| magazine.getName().equalsIgnoreCase(wordKey)){
				bills.remove(bills.get(i));
				isFound=true;
				msg="The magazine program has already been subscribed: "+wordKey;
			}
		}
		return msg;
	}

}