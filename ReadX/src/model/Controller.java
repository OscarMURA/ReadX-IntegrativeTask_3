package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;
/**
 * It is the controller from the border and the program, and who performs all the functions in the system
 */
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
		currentUser=users.get(users.size()-1);
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
	 * This control method is for search the bibliographicProduct by its name or id
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
			Emission typeEmission = assignTypeEmission(emission);
			;
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
	 * This control method eliminates the product in the main library, but not the users who already bought it
	 * 
	 * @param wordKey
	 */
	public String deleteProduct(String wordKey) {
		Bibliographic product = searchBibliographic(wordKey);
		Bibliographic copyProduct= null;
		ArrayList<Bill> bills=product.getBills();
		if(product instanceof Book){
			copyProduct=new Book(product);
		}else if(product instanceof Magazine){
			copyProduct=new Magazine(product);
		}
		// This loop is for users who had already bought the product, 
		//do not lose them alone that it will no longer be available to buy	
		for (int i = 0; i < bills.size(); i++) {
			bills.get(i).setProduct(product, copyProduct);
		}
		bibliographics.remove(product);
		return "The " + wordKey + " was delete";
	}

	
	/**
	 * The function initializes and populates various objects such as users, bibliographic items, and
	 * bills, and returns a formatted string displaying the information.
	 * 
	 * @return The method `testInit()` returns a `String` containing a formatted table with information
	 * about users and bibliographic products, as well as a message indicating that all premium users have
	 * bought two products with suffixes less than 30 and a note at the end.
	 */
	public String testInit() {
		int j = 0;
		int amountPag = 0, type = 0, emission = 0;
		String product = "",  url = "", id = "";
		double productValue = 0;
		Calendar date = Calendar.getInstance();
		Bibliographic bibliographic = null;
		User user = null;
		Bill bill=null;
		ArrayList<Bibliographic> products=new ArrayList<Bibliographic>();

		msg ="\n"+String.format("\033[43m|%-4s| %-12s|%-10s | %-11s| %-10s| %-7s| %-5s| %-11s |%-6s|\033[0m\n", "Number", "Regular ","> Id ", "Premium ","> Id", "Book","> Id", "Magazine","> Id");
		msg += String.format("\033[43m|%-6s|\033[0m %-12s| %-10s| %-11s| %-10s| %-7s| %-5s| %-11s |%-6s|\n", "", "", "", "", "","","","","","");
		for (j = 1; j <= 35; j++) {
			user = new Regular("Regular "+j, String.valueOf(random.nextInt((int) 1e6)), date);
			users.add(user);
			msg+=String.format("\033[43m|%-6s|\033[0m %-12s| %-10s|", j," "+user.getName()," "+user.getID());

			user = new Premium("Premium "+j, String.valueOf(random.nextInt((int) 1e6)), date);
			users.add(user);
			msg+=String.format("%-12s| %-10s|"," "+user.getName()," "+user.getID());

			for (int i = 1; i <= 2; i++) {
				product = (i == 1) ? "Book" : "Magazine";
				amountPag = random.nextInt(1000) + 1;
				type = random.nextInt(3) + 1;
				id = generationAlfaAndHexaDecimal(i);
				url = generationAlfaAndHexaDecimal(i)+ ".PNG";
				productValue = 10000 + random.nextInt((int) 1e5);
				// Generates dates since 1900
				int day = random.nextInt(31) + 1;
				int month = random.nextInt(12) + 1;
				int year = random.nextInt(123) + 1900;
				date.set(year, month, day);
				if (i == 1) {
					TypeBook typeBook = assignTypeBook(type);
					bibliographic = new Book(id, product+" "+j, amountPag, date, url, productValue, typeBook, "NN");
					msg+=String.format("%-8s| %-5s|"," "+bibliographic.getName()," "+bibliographic.getCodeId());

				} else {
					emission = random.nextInt(4) + 1;
					Emission typeEmission = assignTypeEmission(emission);
					TypeMagazine typeMagazine = assignTypeMagazine(type);
					bibliographic = new Magazine(id, product+" "+j, amountPag, date, url, productValue, typeEmission,
							typeMagazine);
					msg+=String.format("%-12s | %-5s|"," "+bibliographic.getName()," "+bibliographic.getCodeId());

				}
				bibliographics.add(bibliographic);
				if(j<31){//Save the first 30 products for purchase
					products.add(bibliographic);
				}
			}
			msg+="\n";
		}
		bill=new Bill((int) 1e10, date, products);
		for (int i = 1; i < users.size(); i++) {
			if(users.get(i) instanceof Premium){
				users.get(i).addBill(bill);
			}
		}
		msg+="\nPD: All premium users has bought to products with suffixes less than 30\n\n";
		
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
	 * This control method verifies the type of book that is the book, and only the
	 * purchase of products will be recorded if you are an user, or if you are
	 * regular you can register books if you have less than 5 or magazines less than
	 * 2. Aditional, this method verify that the regular user no do a buy bug, for
	 * buy more of your limit
	 * @param wordKey Name or ID of bibliographic products
	 */
	public String BuyProduct(ArrayList<String> wordKeys, double totalValue) {
		int book=0, magazine=0;
		Calendar buyDate = Calendar.getInstance();
		String noSave="";
		int pos = 0;
		ArrayList<Bibliographic> products = new ArrayList<Bibliographic>();
		Bibliographic product = null;
		Bill bill = null;
		msg="";
		
		if(currentUser instanceof Regular){//
			book=((Regular) currentUser).counterProduct(1);
			magazine=((Regular) currentUser).counterProduct(2);
		}
		for (int i = 0; i < wordKeys.size(); i++) {
			product = searchBibliographic(wordKeys.get(i));
			if(currentUser instanceof Regular){
				if(product instanceof Book){
					book++;
				}else{
					magazine++;
				}
			}
			if( (currentUser instanceof Premium ) || (product instanceof Book &&  book<=5) || (product instanceof Magazine &&  book<=2)){
				products.add(product);
			}
			else{
				noSave+="\nThis product: "+product.getName()+". It is not added by overcoming the purchase limit of this type.";
			}
		}
		if(wordKeys.size()==0){
			msg = "You don't buy products";
		}else{
			products.remove(null);
			bill = new Bill(totalValue, buyDate, products);
			currentUser.addBill(bill);
			msg = bill.toString();
		}
		
		return msg+noSave;
	}
	/**
	 * This method verifies if the user can buy 1.Boor or Magazine if it is a
	 * regular user, if it is Premium, it passes the test, it means that it
	 * can*
	 * @param option 1.Book or 2.Magazine
	 * @return true: The user can Buy Prodcut bibliographic
	 */
	public boolean CheckingCheck(int option) {
		boolean canBuy = false;
		if((currentUser instanceof Premium)){
			canBuy=true;
		}
		else if( option==1 && ((Regular) currentUser).counterProduct(1) < 5){
			canBuy=true;
		}else if( option==2&& ((Regular) currentUser).counterProduct(2) < 2){
			canBuy=true;
		}
		return canBuy;
	}
	/**
	 * This method is responsible for eliminating the subscription of a magazine 
	 * @param wordKey Name or ID of bibliographic products
	 * @return Message that subscription stood out
	 */
	public String eliminateMagazineSubscrition(String wordKey) {
		boolean isFound = false;
		Bibliographic product=currentUser.alreadyHasProduct(wordKey);
		return currentUser.eliminateMagazineSuscription(product);
	}
	
	/**
	 * The function reads a section of a product and returns a message with options for the user.
	 * 
	 * @param option a character representing the user's choice of action while reading a product (either
	 * moving to the next page, going back to the previous page, or returning to the user menu)
	 * @param wordKey The wordKey parameter is a String that represents the unique identifier of a
	 * bibliographic product. It is used to retrieve the corresponding product and bill from the current
	 * user's account.
	 * @return The method is returning a String message that includes information about the current
	 * reading section, the name of the product being read, the current page being read, and options for
	 * the user to navigate to the next page, go back to the previous page, or return to the user menu.
	 */
	public String read(char option, String wordKey) {
		Bibliographic product = currentUser.alreadyHasProduct(wordKey);
		Bill bill=currentUser.getBill(wordKey);
		int pos=bill.increasePages(option, product);
		msg = "\nReading section in process: ";
		msg +="\nReading: " + product.getName();
		msg+="\nReading page "+pos+" of "+product.getAmountPag()+"\n";
		msg+="\nS.Next Page\nA.Back Page\nB.Back Menu User \n";	
		
		return msg;

	}
}