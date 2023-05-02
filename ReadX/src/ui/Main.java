package ui;

import model.Controller;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

	private static Controller controller;
	private static Scanner reader;
	private static SimpleDateFormat format;
	/**
	 * This storage variable(ArrayList) the hexadecimal and alphanumeric codes, with the purpose of not repeat
	 */
	
	/**
	 * method builder of the main Class
	 */
	public Main() {
		controller = new Controller();
		reader = new Scanner(System.in);
		format = new SimpleDateFormat("dd-MM-yyyy");
	}

	public static void main(String[] args) {

		Main main=new Main();
		main.registerUser();
		
	}

	/**
	 * Control method used to verify text strings with spaces for any issues.
	 * This was implemented to prevent reading problems.
	 * 
	 * @param scanner Scanner object
	 * @return Returns the entire line as a String
	 */
	public static String read(Scanner scanner) {
		String line = "";
		do {
			scanner.useDelimiter(System.lineSeparator());
			line = scanner.next();
			scanner.useDelimiter("\\p{javaWhitespace}+");
		} while (line.equalsIgnoreCase(""));
		return line;
	}

	/**
	 * println Method
	 * @param println Object for print with line jump
	 */
	public static void println(Object println) {
		System.out.println(println);
	}
	/**
	 * print Method
	 * @param print Object for print without line jump
	 */
	public static void print(Object print) {
		System.out.print(print);
	}
	/**
	 * Decoration method to separate functionalities
	 */
	public static void lines() {
		System.out.println("\033[47;35m"
				+ "\n\3\3------------\4\4------------\3\3------------\4\4------------\3\3------------\4\4------------\3\3"
				+ "\033[0m \n");
	}
	/**
	 * Decoration method for title
	 * @param title title
	 */
	public static void title(Object title) {
		System.out.println("\t\033[47;35m \3 "+title+" \3 \033[0m \n");
	}
	/**
	 * Control/View method that verifies if the input is a number, and can also be
	 * used to verify options with necessary restrictions.
	 * @return The validated double value
	 */
	public double validateDouble() {
		double option = 0;
			if (reader.hasNextDouble()) {
				option = reader.nextDouble();
			} else {
				reader.next();// limpiar el scanner
				System.out.println("\tInvalid number!");
			}
		return option;
	}

	/**
	 * Control/View method that verifies if the input is a number, and can also be
	 * used to verify options with necessary restrictions.
	 * @return The validated Int value
	 */
	public int validateInt() {
		int option = 0;
			if (reader.hasNextInt()) {
				option = reader.nextInt();
			} else {
				reader.next();// limpiar el scanner
				System.out.println("\tInvalid number!");
			}
		return option;
	}
	
	public void menu() {
		// TODO - implement Main.menu
		throw new UnsupportedOperationException();
	}

	public void optionExecution() {
		// TODO - implement Main.optionExecution
		throw new UnsupportedOperationException();
	}

	public void registerUser() {
		String name="", id="",msg="";
		boolean repeatId=false;
		int option=0;
		lines();
		title("Register User");
		println("What will you user type register?\n\t1. Regular\n\t2. Premium\n");

		do {
			print("\tConrrently type: ");
			option=validateInt();
		} while ((option!=1&&option!=2));
		print("\nType name: ");
		name=read(reader);
		do {
			print("\nType id exclusive: ");
			id=read(reader);
			repeatId=controller.verifyNoRepeatUser(id);
		} while (repeatId);

		msg=controller.registerUser(name, id, option);
		println(msg);
	}

	public void registerBibliographicProduct() {
		int option=0,amountPag=0,type=0, emission=0;
		String product="",productName="",url="", codeId="";
		double  productValue=0;
		Calendar datePublication=null;
		lines();
		title("Register blibliographic products");
		println("Choose the type Product:\n\t1.Book\n\t2.Magazine\n");

		do {
			print("\t\4Correctly Type: ");
			option=validateInt();
		} while (option!=1&&option!=2);
		if(option==1){
			product=" Book ";
		}else{
			product=" Magazine ";
		}
		product=assignName(product);
		
		amountPag=assignAmountPag(product);

		datePublication=assignDatePublication(product);

		println("Generating url...");
		url="https://readX.com/bibliographic-product/"+productName+"/pag-"+amountPag+"cod-"+codeId+".com.eg";
		println("\4Url= "+url);
		
		productValue=assignProductValue(product);

		type=assignProductType(product, option);
		
		if(option==2){
			emission=assignEmission(product);
		}
		controller.registerBibliographicProduct(option, productName, amountPag, datePublication, url, productValue, emission, type);
	}

	/**
	 * This view  method assign the name of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product name
	 */
	public String assignName(String product){
		String productName="";
		boolean repeat=false;
		do {
			print("\t\4Exclusive! Type"+product+"name: ");
			productName=read(reader);
			repeat=controller.verifyNoRepeatProduct(productName);
		} while (repeat);

		return productName;
	}

	/**
	 * This view  method assign the amount pag of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product Amount page
	 */
	public int assignAmountPag(String product){
		int amountPag=0;
		do {
			print("\t\4Type"+product+"amount pages: ");
			amountPag=validateInt();
		} while (amountPag<=0);
		return amountPag;
	}

	/**
	 * This view  method assign the date publication of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product date publication
	 */
	public Calendar assignDatePublication(String product){
		Calendar datePublication=Calendar.getInstance();
		boolean isBefore=false;
		do {
			Calendar now=Calendar.getInstance();
			datePublication=assingDate();
			isBefore=datePublication.before(now);
			if(!isBefore){
				println("You must enter the date, at the moment you cannot place products without premiere");
			}
		} while (!isBefore);
		return datePublication;
	}

	/**
	 * This view  method assign the value of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product value
	 */
	public double assignProductValue(String product){
		double productValue=0;
		do {
			print("\4Write the product value or subscription: ");
			productValue=validateDouble();
		} while (productValue<0);
		return productValue;
	}
	/**
	 * This view  method assign the each product type
	 * @param product "Book" or "Magazine")
	 * @param option 1.Book or 2.Magazine
	 * @return product type such as option number
	 * 
	 * 
	 */
	public int assignProductType(String product, int option){
		int type=0;
		println("Choose"+product+"type\n");

		switch(option){
			case 1->{
				println("\t1.Ciencie fiction\n\t2.Fantasy\n\t3.Historical Novel");
			}
			case 2->{
				println("\t1.Varieties\n\t2.Desing\n\t3.Cientist");
			}
		}
		do {
			print("\t\4Correctly type: ");
			type=validateInt();
		} while (type<=0||type>=4);
		return type;
	}
	/**
	 * This view  method assign the emission of the Magazine
	 * @param product Type product ("Book" or "Magazine")
	 * @return emission Type of the Magazine
	 */
	public int assignEmission(String product){
		int emission=0;
		println("the magazine Each amount month will have emssion ");
			println("\n\t1.Monthly\n\t2.Quarterly\n\t3.Biannual\n\t4.Annual\n");
			do {
				print("\t\4Correctly type: ");
				emission=validateInt();
			} while (emission<=0||emission>=5);
		return emission;
	}
	/**
	 * Prompts the user to enter a date in the format "dd/MM/yyyy",
	 * and validates the input to ensure that it is a valid date. Returns
	 * a Calendar object representing the date entered by the user.
	 * 
	 * @return A Calendar object representing the date entered by the user.
	 */
	public Calendar assingDate() {
		Calendar dateCal = Calendar.getInstance();
		boolean follow;
		println("\4Type date publication: dd-MM-yyyy ->Exa: 22-02-2023");

		do {
			follow = false;
			String date = "";
			print("\t\4Enter date: ");
			date = reader.next();
			try {
				dateCal.setTime(format.parse(date));
				follow = true;
			} catch (ParseException e) {
				//e.printStackTrace();
				println("\tPlease enter date correctly");
			}
		} while (!follow);
		return dateCal;
	}


	public void deleteBibliographicProduct() {
		// TODO - implement Main.deleteBibliographicProduct
		throw new UnsupportedOperationException();
	}

	public void BuyBibliographicProduct() {
		// TODO - implement Main.BuyBibliographicProduct
		throw new UnsupportedOperationException();
	}

	public void read() {
		// TODO - implement Main.read
		throw new UnsupportedOperationException();
	}

	private String generatorHexadecimal() {
		// TODO - implement Main.generatorHexadecimal
		throw new UnsupportedOperationException();
	}

}