
package model;

import java.util.Calendar;
import java.util.ArrayList;

/**
 * The above class is an abstract class called "User".
 */
public abstract class User {

	private String name;
	private String ID;
	private Calendar dateLinkage;
	/**
	 * This variable is an ArrayList that contains the invoices of the user, the
	 * bibliographic products.
	 */
	protected ArrayList<Bill> bills;
	/**
	 * This variable is a matrix that contains the bibliographic products of the
	 * user, organized by date of publication in library shelves of 5*5 the shelves  are
	 * stored in an ArrayList
	 */
	private ArrayList<Bibliographic[][]> myShelves;
	/**
	 * This variable indicates the position of the box where the User was located
	 * last time, in its library of bibliographic products
	 */
	private int posBox = 0;
	/**
	 * This variable keeps the last table 5*5 presented by the user
	 */
	private String boxTableProduct;

	/**
	 * this constructor method of the parent User class
	 * 
	 * @param name        User name
	 * @param id          User id
	 * @param dateLinkage User income date
	 */
	public User(String name, String id, Calendar dateLinkage) {
		bills = new ArrayList<Bill>();
		this.name = name;
		this.ID = id;
		this.dateLinkage = dateLinkage;
		this.boxTableProduct = "";
		myShelves=new ArrayList<Bibliographic[][]>();
	}

	/**
	 * this view method
	 * 
	 * @return the id user
	 */
	public String getID() {
		return ID;
	}

	/**
	 * this view method
	 * 
	 * @return the name user
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method is to add the purchasing invoices made and in force
	 * 
	 * @return Bill ArrayList
	 */
	public ArrayList<Bill> getBills() {
		
		return bills;
	}

	/**
	 * This method adds the new user's bill to the ArrayList
	 * @param bill
	 */
	public void addBill(Bill bill) {
		bills.add(bill);
	}

	/**
	 * This user's method is responsible for eliminating the subscription of a
	 * magazine that has in your library
	 * 
	 * @param magazine El objeto de la revista
	 * @return Elimination magazine subscription message
	 */
	public String eliminateMagazineSuscription(Bibliographic magazine) {
		String msg = "";
		Bill bill = getBill(magazine.getCodeId());
		msg = bill.eliminateProduct(magazine);
		return msg;
	}

	/**
	 * This method verifies if there is already the bibliographic product in the
	 * user library
	 * 
	 * @param wordKey Bibliographic product name or id
	 * @return Prduct!=null->Exits, product==null-> no exist
	 */
	public Bibliographic alreadyHasProduct(String wordKey) {
		Bibliographic product = null;
		Bill bill = getBill(wordKey);
		if (bill != null) {
			product = bill.alreadyHasProduct(wordKey);
		}
		return product;
	}

	/**
	 * This method focuses on searching and returning the Bill object where the
	 * bibliographic product has the user
	 * 
	 * @param wordKey Bibliographic product name or id
	 * @return Bill!=null->Exits, Bill==null-> no exist
	 */
	public Bill getBill(String wordKey) {
		Bill bill = null;
		boolean isFound = false;
		for (int i = 0; i < bills.size() && !isFound; i++) {
			if (bills.get(i).alreadyHasProduct(wordKey) != null) {
				bill = bills.get(i);
				isFound = true;
			}
		}
		return bill;
	}
	public void posBoxInit() {
		this.posBox = 0;
		this.boxTableProduct="";
	}
	/**
	 * This function organizes the user's bibliographic products by collecting them
	 * from bills, sorting them by date, and linking them in a matrix.
	 */
	private void organizeBibliographicProducts() {
		myShelves=new ArrayList<Bibliographic[][]>();
		ArrayList<Bibliographic> products = new ArrayList<Bibliographic>();
		Bibliographic[][] shelves=new Bibliographic[5][5];

		// This loop has the purpose of collecting all the products that the user
		for (int i = 0; i < bills.size(); i++) {
			for (int j = 0; j < bills.get(i).getProducts().size(); j++) {
				products.add(bills.get(i).getProducts().get(j));
			}
		}
		//This loop organizes the books from the first that was published to the last
		for (int i = 1; i < products.size(); i++) {
			for (int j = 0; j < products.size() - i; j++) {
				Bibliographic currentP = products.get(j);
				Bibliographic nextP = products.get(j + 1);
				if (currentP.compareTo(nextP) > 0) {
					products.set(j, nextP);
					products.set(j + 1, currentP);
				}
			}
		}
		//Amount Bibliographic products shelves (Matrix)
		int box=(int ) Math.ceil((double)products.size()/25);
		//Aux
		int l = 0;

		//This loop saves the books on shelves
		while (l<products.size() && !(box==myShelves.size())) {
			for (int i = 0; i <5 && l<products.size(); i++) {
				for (int j = 0; j < 5 && l<products.size() ; j++) {
						shelves[i][j] = products.get(l++);
				}
			}
			myShelves.add(shelves);
			shelves=new Bibliographic[5][5];
		} 
		if(products.size()==0){
			myShelves.add(shelves);
		}
	}
	/**
 	* This function displays a table of bibliographic products (books and magazines) organized in shelves
 	* and allows the user to navigate through them. 
 	* @param option A character that determines the action to be taken. 'S' for moving to the next page,
 	* 'A' for moving to the previous page, 'E' for exiting the method, and any other character for
 	* displaying the table of products.
	* @return The method is returning a String that represents a table with the products (books and
 	* magazines) organized in a bibliographic library. The table includes options for navigating through
 	* the pages, selecting a specific product by its coordinates, and viewing its code.
 	*/
	public String showProduct(char option){
		organizeBibliographicProducts();
		int posPast=posBox;
		String msg="";
		msg += "\t" + String.format("\033[48;5;225m\033[30m|%-10s|\033[0m\033[43m %-8s|\033[0m\n\n", " Magazine ", " Book ");
		msg += "\u001B[47m\u001B[30m"
				+ String.format("|%-5s|%-5s|%-5s|%-5s|%-5s|%-5s|", " y/x", "  0", "  1", "  2", "  3", "  4")
				+ "\u001B[0m\n";
				
		if (option == 'S') {
			this.posBox = ((posBox + 1) < myShelves.size()) ? posBox + 1 : posBox;
		} else if (option == 'A') {
			posBox = ((posBox) > 0) ? posBox - 1 : posBox;
		}
		int l=0;
		Bibliographic[][] myLibrary=myShelves.get(posBox);
		for (int i = 0; i < 5 && (option != 'E'); i++) {
			msg += "\u001B[47m\u001B[30m" + String.format("|%-5s|", "  " + (l++)) + "\u001B[0m";
			//Here verifies that the value of "i" is inside the amount of rows of the library
			for (int j = 0; j < 5 &&  i<myLibrary.length &&  j<myLibrary[i].length   ; j++) {
				if (myLibrary[i][j] instanceof Magazine) {
					msg += String.format("\033[48;5;225m\033[30m%-5s|\033[0m", " " + myLibrary[i][j].getCodeId());
				} else if (myLibrary[i][j] instanceof Book) {
					msg += String.format("\033[43m%-5s|\033[0m", " " + myLibrary[i][j].getCodeId());
				}
			}
			msg += "\n";
		}
		msg += "\n";
		msg+="\t\4Next Page(S)\n\t\4Back Page(A)\n\t\4Back menu(B)\n\t\4Coordinates x-y\n\t\4CODE";
		// This conditional defines that if I do not change box, it presents
		// nothing.Otherwise shows the changes
		if (msg.equalsIgnoreCase(boxTableProduct)) {
			msg = "";
		} else {
			boxTableProduct = msg;
		}
		return msg;
	}
	/**
 	*This function searches for a bibliographic product in a library based on a given word key or
 	*coordinates. 
 	* @param wordKey A string that represents either a coordinate in the library (in the format "x-y") or
 	* a bibliographic item's title.
 	* @return The method is returning a Bibliographic object that matches the search criteria specified by
 	* the wordKey parameter. If no matching object is found, the method returns null.
 	*/
	public Bibliographic productSearched(String wordKey) {
		Bibliographic product = null;
		int x = 0;
		int y = 0;		
		if (Character.isDigit(wordKey.charAt(0)) &&wordKey.length()>2 && wordKey.charAt(1)=='-'  &&Character.isDigit(wordKey.charAt(2))) {
			x = Character.getNumericValue( wordKey.charAt(0));
			y = Character.getNumericValue(wordKey.charAt(2));
			if (x < 5 && y<5) {
				product=myShelves.get(posBox)[y][x];
			}
		} else {
			product = alreadyHasProduct(wordKey);
		}
		return product;
	}

	

}