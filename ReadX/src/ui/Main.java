package ui;

import model.Controller;
import java.util.Scanner;

public class Main {

	private static Controller controller;
	private static Scanner reader;

	/**
	 * method builder of the main Class
	 */
	public Main() {
		controller = new Controller();
		reader = new Scanner(System.in);
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
	 */
	public static void println(Object println) {
		System.out.println(println);
	}
	/**
	 * print Method
	 */
	public static void print(Object print) {
		System.out.print(print);
	}
	/**
	 * Decoration method to separate functionalities
	 */
	public static void lines() {
		System.out.println("\033[47;35m"
				+ "\3\3------------\4\4------------\3\3------------\4\4------------\3\3------------\4\4------------\3\3"
				+ "\033[0m \n");
	}
	/**
	 * Decoration method for title
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

		double option=0;
		lines();
		title("Register User");
		println("What will you user type register?\n\t1. Regular\n\t2. Premium\n");

		do {
			print("\tConrrently type: ");
			option=validateDouble();
		} while ((option!=1&&option!=2)||option!=Math.floor(option));
		print("\nType name: ");
		name=read(reader);
		do {
			print("\nType id exclusive: ");
			id=read(reader);
			repeatId=controller.verifyNoRepeatUser(id);
		} while (repeatId);

		msg=controller.registerUser(name, id, (int)option);
		println(msg);
	}

	public void registerBibliographicProduct() {
		
	}

	public void modifiedBibliographicProduct() {
		// TODO - implement Main.modifiedBibliographicProduct
		throw new UnsupportedOperationException();
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