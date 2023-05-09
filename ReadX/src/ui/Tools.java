package ui;

import java.util.Scanner;

public class Tools {
    
    private static Scanner reader;


    public Tools(){
        reader = new Scanner(System.in);
    }
    /**
	 * Control method used to verify text strings with spaces for any issues.
	 * This was implemented to prevent reading problems.
	 * 
	 * @param scanner Scanner object
	 * @return Returns the entire line as a String
	 */
	public String read(Scanner scanner) {
		String line = "";
		do {
			scanner.useDelimiter(System.lineSeparator());
			line = scanner.next();
			scanner.useDelimiter("\\p{javaWhitespace}+");
		} while (line.equalsIgnoreCase(""));

		return line;
	}

	
	public void lines() {
		System.out.println("\033[47;35m"
				+ "\n\3\3------------\4\4------------\3\3------------\4\4------------\3\3------------\4\4------------\3\3"
				+ "\033[0m \n");
	}
	/**
	 * Decoration method for title
	 * @param title title
	 */
	public  void title(Object title) {
		System.out.println("\t\033[47;35m \3 "+title+" \3 \033[0m \n");
	}
	/**
	 * Control/View method that verifies if the input is a number, and can also be
	 * used to verify options with necessary restrictions.
	 * @return The validated double value
	 */
	public double validateDouble() {
		double option = 0;
		do {
			if (reader.hasNextDouble()) {
				option = reader.nextDouble();
			} else {
				
				reader.next();// limpiar el scanner
				System.out.println("\tInvalid number!");
				System.out.print("\tConrrently Type: ");
				option=Integer.MAX_VALUE;
			}
			
		} while (option==Integer.MAX_VALUE);
			
		return option;
	}

	/**
	 * Control/View method that verifies if the input is a number, and can also be
	 * used to verify options with necessary restrictions.
	 * @return The validated Int value
	 */
	public int validateInt() {
		int option = 0;
		do {
			if (reader.hasNextInt()) {
				option = reader.nextInt();
			} else {
				reader.next();// limpiar el scanner
				System.out.println("\tInvalid number!");
				System.out.print("\tConrrently Type: ");
				option=Integer.MAX_VALUE;
			}
		} while (option==Integer.MAX_VALUE);
		return option;
	}

	
	
}
