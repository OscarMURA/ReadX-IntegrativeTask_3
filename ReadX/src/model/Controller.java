package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
/**
 * It is the controller from the border and the program, and who performs all
 * the functions in the system
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
		currentUser = users.get(users.size() - 1);
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
				msg += ". Aditional, its code Hexadecimal is " + codeId;

			}
			case 2 -> {
				TypeMagazine typeMagazine = null;
				Emission typeEmmision = null;
				typeMagazine = assignTypeMagazine(type);
				typeEmmision = assignTypeEmission(emission);
				bibliographic = new Magazine(codeId, name, amountPag, datePublication, url, value, typeEmmision,
						typeMagazine);
				msg += "\nsuch as a Magazine ";
				msg += ". Aditional, its code Alphanumeric is " + codeId;

			}
		}
		this.bibliographics.add(bibliographic);
		return msg;
	}

	/**
	 * This method is for assign the type that is the Magazine
	 * 
	 * @param type varaible flag to indicate the type. 1.Varieties, 2.Desing and
	 *             3.Scientis
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

	/**
	 * This method is for assign the type that is the Book
	 *
	 * @param type varaible flag to indicate the type. 1.Ciencie fiction, 2.Fantasy
	 *             and 3.Historical novel
	 * @return The Book type
	 */
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
	 * This control method eliminates the product in the main library, but not the
	 * users who already bought it
	 * 
	 * @param wordKey
	 */
	public String deleteProduct(String wordKey) {
		Bibliographic product = searchBibliographic(wordKey);
		ArrayList<Bill> bills = product.getBills();

		// This loop is for users who had already bought the product,
		// do not lose them alone that it will no longer be available to buy
		for (int i = 0; i < bills.size(); i++) {
			Bill bill = bills.get(i);
			bill.eliminateProduct(product);
		}

		bibliographics.remove(product);
		return "The " + wordKey + " was delete";
	}

	/**
	 * The function initializes and populates various objects such as users,
	 * bibliographic items, and
	 * bills, and returns a formatted string displaying the information.
	 * 
	 * @return The method `testInit()` returns a `String` containing a formatted
	 *         table with information
	 *         about users and bibliographic products, as well as a message
	 *         indicating that all premium users have
	 *         bought two products with suffixes less than 30 and a note at the end.
	 */
	public String testInit() {
		int j = 0;
		int amountPag = 0, type = 0, emission = 0;
		String product = "", url = "", id = "";
		double productValue = 0;
		Calendar date = Calendar.getInstance();
		Bibliographic bibliographic = null;
		User user = null;
		Bill bill = null;
		ArrayList<String> products = new ArrayList<String>();

		msg = "\n" + String.format("\033[43m|%-4s| %-12s|%-10s | %-11s| %-10s| %-7s| %-5s| %-11s |%-6s|\033[0m\n",
				"Number", "Regular ", "> Id ", "Premium ", "> Id", "Book", "> Id", "Magazine", "> Id");
		msg += String.format("\033[43m|%-6s|\033[0m %-12s| %-10s| %-11s| %-10s| %-7s| %-5s| %-11s |%-6s|\n", "", "", "",
				"", "", "", "", "", "", "");
		for (j = 1; j <= 35; j++) {

			users.add(new Regular("Regular " + j, String.valueOf(random.nextInt((int) 1e6)), date));
			user = users.get(users.size() - 1);
			msg += String.format("\033[43m|%-6s|\033[0m %-12s| %-10s|", j, " " + user.getName(), " " + user.getID());

			users.add(new Premium("Premium " + j, String.valueOf(random.nextInt((int) 1e6)), date));
			user = users.get(users.size() - 1);
			msg += String.format("%-12s| %-10s|", " " + user.getName(), " " + user.getID());

			for (int i = 1; i <= 2; i++) {
				product = (i == 1) ? "Book" : "Magazine";
				amountPag = random.nextInt(1000) + 1;
				type = random.nextInt(3) + 1;
				id = generationAlfaAndHexaDecimal(i);
				url = generationAlfaAndHexaDecimal(i) + ".PNG";
				productValue = 10000 + random.nextInt((int) 1e5);
				// Generates dates since 1900
				int day = random.nextInt(31) + 1;
				int month = random.nextInt(12) + 1;
				int year = random.nextInt(123) + 1900;
				date.set(year, month, day, 0, 0, 0);
				if (i == 1) {
					TypeBook typeBook = assignTypeBook(type);
					bibliographic = new Book(id, product + " " + j, amountPag, (Calendar) date.clone(), url,
							productValue, typeBook, "NN");
					bibliographics.add(bibliographic);
					msg += String.format("%-8s| %-5s|", " " + bibliographic.getName(), " " + bibliographic.getCodeId());

				} else {
					emission = random.nextInt(4) + 1;
					Emission typeEmission = assignTypeEmission(emission);
					TypeMagazine typeMagazine = assignTypeMagazine(type);
					bibliographic = new Magazine(id, product + " " + j, amountPag, (Calendar) date.clone(), url,
							productValue, typeEmission,
							typeMagazine);
					bibliographics.add(bibliographic);
					msg += String.format("%-12s | %-5s|", " " + bibliographic.getName(),
							" " + bibliographic.getCodeId());
				}

				if (j < 33) {// Save the first 30 products for purchase
					products.add(bibliographics.get(bibliographics.size() - 1).getName());
				}
			}
			msg += "\n";
		}

		for (int i = 0; i < users.size(); i++) {
			currentUser=users.get(i);
			if(currentUser instanceof Premium ){
				BuyProduct(products, 1000000);
			}	
		}		
		msg += "\nPD: All premium users has bought to products with suffixes less than 30\n\n";

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
	 * 
	 * @param wordKey Name or ID of bibliographic products
	 */
	public String BuyProduct(ArrayList<String> wordKeys, double totalValue) {
		int book = 0, magazine = 0;
		Calendar buyDate = Calendar.getInstance();
		String noSave = "";
		int pos = 0;
		ArrayList<Bibliographic> products = new ArrayList<Bibliographic>();
		Bibliographic product = null;
		Bill bill = null;
		String msg = "";

		if (currentUser instanceof Regular) {//
			book = ((Regular) currentUser).counterProduct(1);
			magazine = ((Regular) currentUser).counterProduct(2);
		}
		for (int i = 0; i < wordKeys.size(); i++) {
			product = searchBibliographic(wordKeys.get(i));
			if (currentUser instanceof Regular) {

				if (product instanceof Book) {
					book++;
				} else if (product instanceof Magazine) {
					magazine++;
				}
			}
			if ((currentUser instanceof Premium)) {
				products.add(product);
			} else if (product instanceof Book && book <= 5) {
				products.add(product);
			} else if ((product instanceof Magazine && magazine <= 2)) {
				products.add(product);
			} else {
				noSave += "\nThis product: " + product.getName()
						+ ". It is not added by overcoming the purchase limit of this type.";
			}
		}
		
		if (wordKeys.size() == 0) {
			msg = "You don't buy products";
		} else {
			
			//This method removes the repeated products
			HashSet<Bibliographic> set=new HashSet<>(products);
			List<Bibliographic> list=new ArrayList<>(set);
			products.clear();
			products.addAll(list);

			products.remove(null);
			bill = new Bill(totalValue, buyDate, products, currentUser);
			// products.get(0).addBill(bill);
			for (int i = 0; i < products.size(); i++) {
				products.get(i).addBill(bill);
			}
			currentUser.addBill(bill);
			msg = bill.toString();
		}
		return msg + noSave;
	}
	/**
	 * This method verifies if the user can buy 1.Boor or Magazine if it is a
	 * regular user, if it is Premium, it passes the test, it means that it
	 * can buy
	 * 
	 * @param option 1.Book or 2.Magazine
	 * @return true: The user can Buy Prodcut bibliographic
	 */
	public boolean CheckingCheck(int option) {
		boolean canBuy = false;
		if ((currentUser instanceof Premium)) {
			canBuy = true;
		} else if (option == 1 && ((Regular) currentUser).counterProduct(1) < 5) {
			canBuy = true;
		} else if (option == 2 && ((Regular) currentUser).counterProduct(2) < 2) {
			canBuy = true;
		}
		return canBuy;
	}

	/**
	 * This method is responsible for eliminating the subscription of a magazine
	 * 
	 * @param wordKey Name or ID of bibliographic products
	 * @return Message that subscription stood out
	 */
	public String eliminateMagazineSubscrition(String wordKey) {
		Bill billProduct = null;
		boolean isFound = false;
		Bibliographic product = currentUser.alreadyHasProduct(wordKey);
		// Here the current invoice that the magazine with the user is eliminated
		billProduct = currentUser.getBill(wordKey);
		product.getBills().remove(billProduct);
		msg = currentUser.eliminateMagazineSuscription(product);
		return msg;
	}

	/**
	 * The function reads a section of a product and returns a message with options
	 * for the user.
	 * 
	 * @param option  a character representing the user's choice of action while
	 *                reading a product (either
	 *                moving to the next page, going back to the previous page, or
	 *                returning to the user menu)
	 * @param wordKey The wordKey parameter is a String that represents the unique
	 *                identifier of a bibliographic product. It is used to retrieve the
	 *                corresponding product and bill from the current
	 *                user's account.
	 * @return The method is returning a String message that includes information
	 *         about the current
	 *         reading section, the name of the product being read, the current page
	 *         being read, and options for
	 *         the user to navigate to the next page, go back to the previous page,
	 *         or return to the user menu.
	 */
	public String read(char option, String wordKey) {
		msg = "";
		Bibliographic product = currentUser.alreadyHasProduct(wordKey);
		Bill bill = currentUser.getBill(wordKey);
		int pos = bill.increasePages(option, product), init = bill.getInitPag(product);
		int ultimePag = bill.getUltimePag(product);
		int actualPag = bill.getReadPage(product);
		// This conditional verifies if the user is regular, to show advertising in:
		boolean start = (init == ultimePag); // start
		boolean marketingBook = (actualPag - init) % 20 == 0 && product instanceof Book;// Each 20 pages of a book
		boolean marketingMaga = ((actualPag - init) % 5 == 0 && product instanceof Magazine);// Each 5 pages of a
																								// magazine
		if (currentUser instanceof Regular) {
			if (start || marketingMaga || marketingBook) {
				String marketing[] = { "¡Suscríbete al Combo Plus y llévate Disney+ y Star+ a un precio increíble!",
						"Ahora tus mascotas tienen una app favorita: Laika. Los mejores productos para tu peludito.",
						"¡Estamos de aniversario! Visita tu Éxito más cercano y sorpréndete con las mejores ofertas."
				};
				Random random = new Random();
				msg += "\n " + "\1 \2" + marketing[random.nextInt(3)] + "\2 \1 \n";
			}
		}
		msg += "\nReading section in process: ";
		msg += "\nReading: " + product.getName() + " - " + product.getCodeId();
		msg += "\nReading page " + pos + " of " + product.getAmountPag() + "\n";
		msg += "\nS.Next Page\nA.Back Page\nB.Back Menu User \n";
		return msg;
	}

	
	/**
	 * The function calculates and returns the total number of pages read in books and magazines.
	 * 
	 * @return The method is returning a String message that displays the total number of pages read in
	 * magazines and books.
	 */
	private String allPageRead() {
		int allReadBook = 0;
		int allReadMagazine = 0;
		msg="";
		for (int i = 0; i < bibliographics.size(); i++) {
			if (bibliographics.get(i) instanceof Book) {
				allReadBook+=((Book) bibliographics.get(i)).getPageRead();
			}else if(bibliographics.get(i) instanceof Magazine) {
				allReadMagazine+=((Magazine) bibliographics.get(i)).getPageRead();
			}
		}
		msg += "The total number of pages read in the Magazines is: " + allReadMagazine + "\n";
		msg += "The total number of pages read in the books is: " + allReadBook + "\n";
		return msg;
	}

	
	/**
	 * This function counts the number of magazines in a list that match a specified type.
	 * @param option ( 1.Varieties, 2.Desing and 3.Scientis) The option parameter is an integer value that is used to determine the type of
	 * magazine to count. It is passed to the assignTypeMagazine() method to assign the appropriate
	 * TypeMagazine enum value.
	 * @return The method `counterTypeMagazine` returns an integer value, which represents the number of
	 * magazines in the `bibliographics` list that match the specified `option` parameter.
	 */
	private int counterTypeMagazine(int option) {
		int i = 0;// var counter
		TypeMagazine type = assignTypeMagazine(option);
		for (int j = 0; j < bibliographics.size(); j++) {
			if (bibliographics.get(j) instanceof Magazine && type == ((Magazine) bibliographics.get(j)).getType()) {
				i+=bibliographics.get(j).getPageRead();
			}
		}
		return i;
	}

	/**
	 * This function counts the number of books in a list of bibliographic items based on a specified
	 * type.
	 * 
	 * @param option (1.Ciencie fiction, 2.Fantasy and 3.Historical novel)
	 * The option parameter is an integer that represents the type of book to be counted. It
	 * is used to assign a TypeBook enum value to the type variable, which is then used to compare with
	 * the type of each Book object in the bibliographics list.
	 * @return The method is returning an integer value, which represents the number of books in the
	 * bibliographics list that match the specified type.
	 */
	private int counterTypeBook(int option) {
		int i = 0;// var counter
		TypeBook type = assignTypeBook(option);
		for (int j = 0; j < bibliographics.size(); j++) {
			if (bibliographics.get(j) instanceof Book && type == ((Book) bibliographics.get(j)).getType()) {
				i+=bibliographics.get(j).getPageRead();
			}
		}
		return i;
	}
	/**
	 * This function determines the most popular type of magazine based on the
	 * number of magazines of each type in a list of bibliographic items.
	 * 
	 * @return The method is returning a String message indicating the most popular
	 *         type of magazine based
	 *         on the number of magazines of each type in the bibliographics list.
	 */
	private String magazineMorePopular() {
		msg = "";
		int varieties = 0, science = 0, desing = 0, maxValue = 0;
		varieties = counterTypeMagazine(1);
		desing = counterTypeMagazine(2);
		science = counterTypeMagazine(3);
		maxValue = varieties;
		if (maxValue < science) {
			maxValue = science;
			msg = "The most popular type of magazine is the science magazine with an amount of " + maxValue;
		} else if (maxValue < desing) {
			maxValue = desing;
			msg = "The most popular type of magazine is the design magazine with an amount of" + maxValue;
		} else {
			msg = "The most popular type of magazine is the varieties magazine with an amount of " + maxValue;
		}
		return msg;
	}
	/**
	 * The function determines the most popular type of book in a list of
	 * bibliographic items.
	 * @return The method is returning a String message indicating the most popular
	 *         type of book in the bibliographic list and the amount of books of that type.
	 */
	private String bookMorePopular() {
		msg = "";
		int science = 0, fantasy = 0, novel = 0, maxValue = 0;
		science = counterTypeBook(1);
		fantasy = counterTypeBook(2);
		novel = counterTypeBook(3);
		maxValue = novel;
		if (maxValue < science) {
			maxValue = science;
			msg = "The most popular type of book is the science fiction with an amount of " + maxValue;
		} else if (maxValue < fantasy) {
			maxValue = fantasy;
			msg = "The most popular type of book is the fantasy with an amount of " + maxValue;
		} else {
			msg = "The most popular type of book is the novel with an amount of " + maxValue;
		}
		return msg;
	}

	/**
	 * The function sorts a list of bibliographic items based on the amount of read pages, filtered by
	 * type of product.
	 * 
	 * @param typeProduct The type of product to be sorted. It can be either 1 for books or 2 for
	 * magazines.
	 * @return The method is returning an ArrayList of Bibliographic objects sorted by the amount of read
	 * pages, filtered by the type of product specified by the parameter "typeProduct".
	 */
	private ArrayList<Bibliographic> bubbleSortProduct(int typeProduct){
		ArrayList<Bibliographic> topBibliographic = new ArrayList<Bibliographic>();

		// This cycle collects products according to its type in an arraylist
		for (int i = 0; i < bibliographics.size(); i++) {
			if (1 == typeProduct && bibliographics.get(i) instanceof Book) {
				topBibliographic.add(bibliographics.get(i));
			} else if (2 == typeProduct && bibliographics.get(i) instanceof Magazine) {
				topBibliographic.add(bibliographics.get(i));
			}
		}
		// This cycle orders the products based on the amount of read pages
		for (int i = 0; i < topBibliographic.size(); i++) {
			for (int j = 0; j < topBibliographic.size() - i-1; j++) {
				Bibliographic current = topBibliographic.get(j);
				Bibliographic next = topBibliographic.get(j + 1);
				if (current.getPageRead() < next.getPageRead()) {
					topBibliographic.set(j, next);
					topBibliographic.set(j + 1, current);
				}
			}
		}
		
		return topBibliographic;
	}

	/**
	 * The function returns a formatted string displaying the top 5 books and magazines based on their
	 * page reads and code IDs.
	 * 
	 * @return The method is returning a String that contains the top 5 books and top 5 magazines sorted
	 * by the number of pages read.
	 */
	private String top5BibliographicProduct(){
		msg="";
		ArrayList<Bibliographic> book = bubbleSortProduct(1);
		ArrayList<Bibliographic> magazine = bubbleSortProduct(2);
		msg+="\t\4Top 5 Books\n\n";
		msg+=String.format("| %-3s|%-12s|%-3s|%-8s|", "Top","  Book",">Id","  Reads " );
		msg+="\n";
		for (int i = 0; i < 5; i++) {
			msg+=String.format("| %-3s|%-12s|%-3s|%-8s|\n",i+1," "+book.get(i).getName(),book.get(i).getCodeId(),book.get(i).getPageRead());
		}
		msg+="\n\t\4Top 5 Magazines\n\n";
		msg+=String.format("| %-3s|%-12s|%-3s|%-8s|\n", "Top"," Magazine",">Id","  Reads " );
		for (int i = 0; i < 5; i++) {
			msg+=String.format("| %-3s|%-12s|%-3s|%-8s|\n",i+1," "+magazine.get(i).getName(),magazine.get(i).getCodeId(),magazine.get(i).getPageRead());
		}
		return msg;

	}
	/**
	 * The function informs about the total sales and value of each type of book in
	 * a list of bibliographic items.
	 * @return The method is returning a String containing information about the
	 *         sales and total value of each type of book in the bibliographic list.
	 */
	private String informTypeBook() {
		TypeBook typeBook = null;
		int sales = 0;
		double valueTotal = 0;
		msg = "\n\n";
		for (int i = 1; i <= 3; i++) {
			typeBook = assignTypeBook(i);
			for (int j = 0; j < bibliographics.size(); j++) {
				if (bibliographics.get(j) instanceof Book) {
					if (typeBook == ((Book) bibliographics.get(j)).getType()) {
						sales += ((Book) bibliographics.get(j)).getBills().size();
						valueTotal += ((Book) bibliographics.get(j)).getValue();
					}
				}
			}
			msg += "\t\4 Information about the type of book: " + typeBook + "\3\n";
			msg += "\4The total sales of this type of book is: " + sales + "\n";
			msg += "\4The total value of this type of book is: " + valueTotal + "\n";
			msg += "\n\n";
			sales = 0;
			valueTotal = 0;
		}
		return msg;
	}

	/**
	 * This function calculates and returns information about the total sales and
	 * value of each type of
	 * magazine in a list of bibliographic items.
	 * 
	 * @return The method is returning a String containing information about the
	 *         total sales and value of
	 *         each type of magazine in the bibliographics list.
	 */
	private String informTypeMagazine() {
		msg = "\n\n";
		int sales = 0;
		double valueTotal = 0;
		for (int j = 1; j <= 3; j++) {
			TypeMagazine typeMagazine = assignTypeMagazine(j);
			for (int i = 0; i < bibliographics.size(); i++) {
				if (bibliographics.get(i) instanceof Magazine) {
					if (typeMagazine == ((Magazine) bibliographics.get(i)).getType()) {
						sales += ((Magazine) bibliographics.get(i)).getBills().size();
						valueTotal += ((Magazine) bibliographics.get(i)).getValue();
					}
				}
			}
			msg += "\t\3 Information about the type of magazine: " + typeMagazine + " \3\n";
			msg += "\4The total sales of this type of magazine is: " + sales + "\n";
			msg += "\4The total value of this type of magazine is: " + valueTotal + "\n";
			msg += "\n\n";
			sales = 0;
			valueTotal = 0;
		}
		return msg;
	}
	/**
 	* The function generates a report based on the option selected and returns a message.
 	* 
 	* @param option The parameter "option" is an integer value that is used to determine which report to
 	* generate. The value of "option" is passed as an argument to the "reportGeneration" method, and based
 	* on its value, the method generates a specific report. The possible values of "option" are
 	* @return The method `reportGeneration` returns a `String` value.
 	*/
	public String reportGeneration(int option){
		msg="";
		switch (option) {
			case 1->msg=allPageRead();
			case 2->msg=bookMorePopular()+"\n\n"+magazineMorePopular();
			case 3->msg=top5BibliographicProduct();
			case 4->msg=informTypeBook();
			case 5->msg=informTypeMagazine();
			case 0->msg="\n...\n";
		}
		return msg;
	}
	
	

}
