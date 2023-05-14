package ui;

import model.Controller;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;

/**
 * The above code defines a Java class named "Main".
 */
public class Main {
	private static Controller controller;
	private static Scanner reader;
	private static SimpleDateFormat format;
	private static Tools tools;
	private static String init;

	/**
	 * method builder of the main Class
	 */
	public Main() {
		controller = new Controller();
		reader = new Scanner(System.in);
		format = new SimpleDateFormat("dd-MM-yyyy");
		tools = new Tools();
		init = controller.testInit();
	}

	public static void main(String[] args) {
		Main main = new Main();
		int option = -1;
		println(init);
		do {
			tools.lines();
			println("Welcome your are> 1.Manager, 2.Client or 0.Exit");
			do {
				print("\n\4Correctly type option: ");
				option = tools.validateInt();
			} while (option != 1 && option != 2 && option!=0);

			if (option == 1) {
				do {
					menu(1);
					do {
						print("\n\4Correctly Type option: ");
						option = tools.validateInt();
					} while (option < 0 && option > 4);
					main.executionManager(option);

				} while (option != 0);

				option = -1;
			} else if (option == 2) {

				do {
					menu(2);
					do {
						print("\n\4Correctly Type option: ");
						option = tools.validateInt();
					} while (option < 1 && option > 2);
					boolean allow = main.execution(option);

					if (allow) {
						do {
							menu(3);
							do {
								print("\n\4Correctly Type option: ");
								option = tools.validateInt();
							} while (option < 1 && option > 2);
							main.executionUser(option);

						} while (option != 0);
					} else if (option != 0) {
						println("¡No exits this user!");
					}
				} while (option != 0);
				option = -1;
			}
		} while (option != 0);
	}

	/**
	 * This method shows the different types of menus
	 * 
	 * @param option 1. Manager menu, 2. User Preringress Menu or 3. User Menu
	 */
	public static void menu(int option) {
		tools.lines();
		if (option == 1) {
			tools.title("Manager Section");
			println("\t\4 1. Register Bibliographic products\n\t\4 2.Modify Bibliograpic product\n\t\4 3.Delete Bibliographic Products");
			println("\t\4 4.Show the test Init\n\t\4 0.Back");
		} else if (option == 2) {
			tools.title("User section");
			println("\n\4 1. Register User\n\4 2.Login User\n\4 0.Back");
		} else if (option == 3) {
			tools.title("Welcome " + controller.getCurrentUser().getName());
			println("\4 1. Buy Bibliographic Products (Book or Magazine)\n\4 2.Eliminate Magazine subscription\n\4 3.Read a Bibliographic Products\n\4 0.Back ");
		}
	}

	/**
	 * User Income Control Method
	 * 
	 * @param option 1.Register User 2.Login User
	 * @return true: register user or exist user
	 */
	public boolean execution(int option) {
		boolean allow = false;
		switch (option) {
			case 1 -> allow = registerUser();
			case 2 -> allow = searchUser();
		}
		return allow;
	}
	/**
	 * METHOD FOR MANAGE OPTIONS
	 * @param option 1.Register 2.Modify 3.Delete 4.Show TestInit
	 */
	public void executionManager(int option) {
		switch (option) {
			case 1 -> registerBibliographicProduct();
			case 2 -> modifiedBibliographicProduct();
			case 3 -> deleteBibliographicProduct();
			case 4 -> {
				tools.lines();
				tools.title("Initial test");
				println(init);
			}
		}
	}
	/**
	 * Control method for user options
	 * 
	 * @param option 1.Buy Product 2.Eleminate Magazine
	 */
	public void executionUser(int option) {
		switch (option) {
			case 1 -> BuyBibliographicProduct();
			case 2 -> eliminateMagazineSubscription();
			case 3 -> read();
		}
	}
	/**
	 * println Method
	 * 
	 * @param println Object for print with line jump
	 */
	public static void println(Object println) {
		System.out.println(println);
	}
	/**
	 * print Method
	 * 
	 * @param print Object for print without line jump
	 */
	public static void print(Object print) {
		System.out.print(print);
	}
	
	/**
	 * This function registers a user by prompting for their name, unique ID, and user type (regular or
	 * premium) and then calling the registerUser method in the controller class.
	 * 
	 * @return The method is returning a boolean value of true.
	 */
	public boolean registerUser() {
		String name = "", id = "", msg = "";
		boolean repeatId = false;
		int option = 0;
		tools.lines();
		tools.title("Register User");
		println("What will you user type register?\n\t1. Regular\n\t2. Premium\n");

		do {
			print("\tConrrently type: ");
			option = tools.validateInt();
		} while ((option != 1 && option != 2));

		print("\n\tType name: ");
		name = tools.read(reader);
		do {
			print("\n\tType id exclusive: ");
			id = tools.read(reader);
			if ((controller.searchUser(id) != null)) {
				repeatId = true;
			}
		} while (repeatId);
		msg = controller.registerUser(name, id, option);
		println(msg);
		return true;
	}

	/**
	 * This method records the bibliographic products, according to the type of book
	 * that the user wants to appear options for such own regitters
	 */
	public void registerBibliographicProduct() {
		int option = 0, amountPag = 0, type = 0, emission = 0;
		String product = "", productName = "", url = "", msg = "", review = "";
		double productValue = 0;
		Calendar datePublication = null;
		
		tools.lines();
		tools.title("Register blibliographic products");
		println("Choose the type Product:\n\t1.Book\n\t2.Magazine\n");
		do {
			print("\t\4Correctly Type: ");
			option = tools.validateInt();
		} while ((option != 1 && option != 2));

		if (option == 1) {
			product = " Book ";
		} else {
			product = " Magazine ";
		}
		productName = assignName(product);
		amountPag = assignAmountPag(product);
		url = assignUrl();
		datePublication = assingDate();
		productValue = assignProductValue(product);
		type = assignProductType(product, option);
		if (option == 1) {
			print("Type book review: ");
			review = tools.read(reader);
		} else {
			emission = assignEmission(product);
		}
		msg = controller.registerBibliographicProduct(option, productName, amountPag, datePublication, url,
				productValue, emission, type, review);
		println("\n\n" + msg);
	}

	/**
	 * This view method assign the name of the product 
	 * @param product Type product ("Book" or "Magazine")
	 * @return product name
	 */
	public String assignName(String product) {
		String productName = "";
		boolean repeat = false;
		do {
			print("\n\4Exclusive! Type " + product + " name: ");
			productName = tools.read(reader);

			if ((controller.searchBibliographic(productName) != null)) {
				repeat = true;
			}
		} while (repeat);
		return productName;
	}

	/**
	 * This view method assign the amount pag of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product Amount page
	 */
	public int assignAmountPag(String product) {
		int amountPag = 0;
		do {
			print("\4Correctly Type " + product + " amount pages (Pag>0): ");
			amountPag = tools.validateInt();
		} while (amountPag <= 0);
		return amountPag;
	}
	/**
	 * This view method assign the value of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product value
	 */
	public double assignProductValue(String product) {
		double productValue = 0;
		do {
			print("\4Write the product value or subscription: ");
			productValue = tools.validateDouble();
		} while (productValue < 0 || productValue == Integer.MAX_VALUE);
		return productValue;
	}
	/**
	 * This view method assign the each product type
	 * @param product "Book" or "Magazine"
	 * @param option  1.Book or 2.Magazine
	 * @return product type such as option number
	 */
	public int assignProductType(String product, int option) {
		int type = 0;
		println("Choose " + product + "  type\n");
		switch (option) {
			case 1 -> {
				println("\t1.Ciencie fiction\n\t2.Fantasy\n\t3.Historical Novel");
			}
			case 2 -> {
				println("\t1.Varieties\n\t2.Desing\n\t3.Cientist");
			}
		}
		do {
			print("\n\t\4Correctly type: ");
			type = tools.validateInt();
		} while (type <= 0 || type >= 4);
		return type;
	}
	/**
	 * This view method assign the emission of the Magazine
	 * @param product Type product ("Book" or "Magazine")
	 * @return emission Type of the Magazine
	 */
	public int assignEmission(String product) {
		int emission = 0;
		println("the magazine Each amount month will have emssion ");
		println("\n\t1.Monthly\n\t2.Quarterly\n\t3.Biannual\n\t4.Annual\n");
		do {
			print("\4Correctly type: ");
			emission = tools.validateInt();
		} while (emission <= 0 || emission >= 5);
		return emission;
	}
	public String assignUrl() {
		String url = "";
		Random random = new Random();
		String[] typeIma = { "jpeg", "png", "jpng", "psd" };
		print("\4Type ulr exclusive: ");
		url = reader.next();
		url = url.toUpperCase() + "." + typeIma[random.nextInt(typeIma.length)];
		println("Your url: " + url);
		return url;
	}
	/**
	 * Prompts the user to enter a date in the format "dd/MM/yyyy",
	 * and tools.validates the input to ensure that it is a valid date. Returns
	 * a Calendar object representing the date entered by the user. 
	 * @return A Calendar object representing the date entered by the user.
	 */
	public Calendar assingDate() {
		Calendar dateCal = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		boolean follow;
		println("\n\4Type date publication: dd-MM-yyyy ->Exa: 22-02-2023");
		do {
			follow = false;
			String date = "";
			print("\t\4Enter date: ");
			date = reader.next();
			try {
				dateCal.setTime(format.parse(date));
				follow = true;
			} catch (ParseException e) {
				println("\n\t¡Please enter date correctly!");
			}
			if (!(dateCal.before(now)) && follow) {
				println("\tYou must enter the date, at the moment you cannot place products without premiere");
			}
		} while (!follow);
		return dateCal;
	}
	/**
	 * This view method is for Modify bibliographic products
	 */
	public void modifiedBibliographicProduct() {
		String wordKey = "";// bibliographicProduct name or identification id
		int typeProduct = 0, option = 0, amountPag = 0, type = 0, emission = 0;
		String product = "", url = "", msg = "", review = "", productName = "";
		double productValue = 0;
		Calendar datePublication = null;
		tools.lines();
		tools.title("Modified Bibliographic Product");
		print("Search bibliographic product,\nType name or indetification code: ");
		wordKey = tools.read(reader);
		if (controller.searchBibliographic(wordKey) != null) {
			typeProduct = controller.intanceOfBibliographic(wordKey);
			product = (typeProduct == 1) ? "Book" : "Magazine";
			do {
				println("\nChoose " + wordKey + " " + product + " for change: ");
				println("\n\t\4 1.Name\n\t\4 2.Amount Page\n\t\4 3.Date publication");
				println("\t\4 4." + product + " value \n\t\4 5.Type " + product + "\n\t\4 6.Url");
				if (typeProduct == 2) {
					println("\t\4 7.Magazine Emission");
				} else {
					println("\t\4 7.Book review");
				}
				print("Type option (save=0): ");
				option = tools.validateInt();
				switch (option) {
					case 0 -> println("Exit");
					case 1 -> productName = assignName(product);
					case 2 -> amountPag = assignAmountPag(product);
					case 3 -> datePublication = assingDate();
					case 4 -> productValue = assignProductValue(product);
					case 5 -> type = assignProductType(product, typeProduct);
					case 6 -> url = assignUrl();
					case 7 -> {
						if (typeProduct == 2) {
							emission = assignEmission(product);
						} else {
							print("\n\t\4Type book review: ");
							review = tools.read(reader);
						}
					}
					default -> println("Option invalid");
				}
			} while (option != 0);
			msg = controller.modifiedProductBibliographic(wordKey, productName, amountPag, datePublication, url,
					productValue, emission, type);
		} else {
			msg = "The " + wordKey + " product no exist";
		}
		println("\n\n" + msg);
	}
	/**
	 * This view method is for delete bibliographic products
	 */
	public void deleteBibliographicProduct() {
		String wordKey = "", msg = "";
		tools.lines();
		tools.title("Delete BibliographicProduct");
		print("Search bibliographic product,\nType name or indetification code: ");
		wordKey = tools.read(reader);
		if (controller.searchBibliographic(wordKey) != null) {
			msg = controller.deleteProduct(wordKey);
		} else {
			msg = "The " + wordKey + " product no exist";
		}
		println(msg);
	}
	/**
	 * This method focuses on looking for the user, to start in a registered account 
	 * @return True: the user exists, or false: the user does not exist
	 */
	public static boolean searchUser() {
		String name = "", id = "";
		boolean isFound = false;
		println("");
		tools.title("Search User");
		print("Type your id: ");
		id = tools.read(reader);
		if (controller.searchUser(id) != null) {
			isFound = true;
		} else {
			println("There are not id");
		}
		return isFound;
	}
	/**
	 * This method of view focuses on receiving and entering the information to proceed to the purchase of bibliographic products from a specific user
	 */
	public void BuyBibliographicProduct() {
		ArrayList<String> wordKeys = new ArrayList<String>();
		String wordKey = "", type = "", msg = "";
		int intance = 0;
		double value = 0, realValue = 0, totalValue = 0;
		
		tools.lines();
		tools.title("Buy Product Bibliographic");
		do {
			print("Correctly typoe code or name (Save buy=\"0\"): ");
			wordKey = tools.read(reader);
			//Conditional that verifies that this product exists and that not save buy
			if (controller.searchBibliographic(wordKey) != null && !(wordKey.equals("0"))) {
				// conditional not to buy a product twice
				if (controller.getCurrentUser().alreadyHasProduct(wordKey) != null || wordKeys.contains(wordKey.toUpperCase())) {
					println("You already have this book");
				} else {
					intance = controller.intanceOfBibliographic(wordKey);
					type = (intance == 1) ? "Book value" : "Magazine subcription";
					// conditional that verifies if you can buy that type of product
					if (controller.CheckingCheck(intance)) {
						println(controller.searchBibliographic(wordKey).getData());
						realValue = controller.searchBibliographic(wordKey).getValue();
						println(type + " is: " + realValue + "\n");
						do {
							print("Correctly " + type + " to pay: ");
							value = tools.validateDouble();
							if (value < realValue) {
								println("You must enter a value equal to or greater than " + realValue);
							}
						} while (!(value >= realValue));
						totalValue += value;
						wordKeys.add(wordKey.toUpperCase());// Add the names of the products to names
					} else {
						println("You can't buy this product: "+wordKey+" "+type+" The library of this type is filled");
					}
				}
				
			} else if (!(wordKey.equals("0"))) {
				println("No exist the product: " + wordKey);
			}
		} while (!(wordKey.equals("0")));
		msg=controller.BuyProduct(wordKeys, totalValue);
		println(msg);
	}
	/**
	 * The view method is to eliminate subscriptions with user magazines
	 */
	public void eliminateMagazineSubscription() {
		String wordKey = "";
		tools.lines();
		tools.title("Cancel Magazine Subscription");
		print("Type name or id: ");
		wordKey = tools.read(reader);
		if (controller.getCurrentUser().alreadyHasProduct(wordKey) != null) {
			if (controller.intanceOfBibliographic(wordKey) == 2) {
				controller.eliminateMagazineSubscrition(wordKey);
			} else {
				println("This product not is a Magazine");
			}
		} else {
			println("No exist this Product in your library");
		}
	}

	/**
	 *This method is responsible for simulating the sensation of reading 
	 any bibliographic product that the user possesses
	 */
	public void read() {
		String wordKey = "";
		char option = ' ';
		tools.lines();
		tools.title("Read Bibliographic Product");
		println("Product: ");
		print(controller.getCurrentUser().showProductUser());
		print("\nType the product (Back=B): ");
		wordKey = tools.read(reader);
		if (controller.getCurrentUser().alreadyHasProduct(wordKey) != null) {
			do {
				String msg=controller.read(option, wordKey);
				println(msg);
				do {
					print("Correctly type: ");
					option = reader.next().toUpperCase().charAt(0);
				} while (option!='A' && option != 'S' && option !='B');

			} while (option != 'B');
		} else if(wordKey.toUpperCase().charAt(0)!='B') {
			println("Product not exits in your library :C");
		}
	}
}