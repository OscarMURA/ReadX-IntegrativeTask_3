package model;

import java.util.Calendar;
import java.util.ArrayList;

/**
 * The User class is an abstract class that represents a user in a library system and contains methods
 * for managing bills and bibliographic products.
 */
public class Bill {

	private double totalValue;
	private Calendar dateBuy;
	
	/**
	 * This arraylist keeps the current page where the user goes in each book, the
	 * positions of the two arraylist are vinvulated
	 */
	private ArrayList<Integer> readPage;
	// `private ArrayList<Bibliographic> products;` is declaring a private instance variable `products` of
	// type `ArrayList<Bibliographic>`. This variable is used to store a list of bibliographic products
	// that were bought by the user.
	private ArrayList<Integer> ultimePag;
	private ArrayList<Bibliographic> products;

	/**
	 * bill Builder method
	 * 
	 * @param totalValue products value
	 * @param dateBuy    buy date
	 * @param products   all products buy for user
	 */
	public Bill(double totalValue, Calendar dateBuy, ArrayList<Bibliographic> products) {
		this.totalValue = totalValue;
		this.dateBuy = dateBuy;
		this.products = products;
		readPage=new ArrayList<Integer>(products.size());
		ultimePag=new ArrayList<Integer>(products.size());
		initPages();
	}
	/**
	 * This method verifies if there is already the bibliographic product in the
	 * user library in this BIll
	 * @param wordKey Bibliographic product name or id
	 * @return true=exist or false: no exist
	 */
	public Bibliographic alreadyHasProduct(String wordKey) {
		Bibliographic product = null;
		boolean isFound = false;
		for (int i = 0; i < products.size() && !isFound; i++) {
			if (products.get(i).getName().equalsIgnoreCase(wordKey)
					|| products.get(i).getCodeId().equalsIgnoreCase(wordKey)) {
				product = products.get(i);
				isFound = true;
			}
		}
		return product;
	}
	/**
	 * This method initializes the pages of each added product
	 */
	private void initPages() {
		for (int i = 0; i < products.size(); i++) {
			readPage.add(i, 0);
			ultimePag.add(i, 0);
		}
	}
	/**
	 * This method returns a digital message from all bought books
	 */
	@Override
	public String toString() {
		String msg = "";
		double price = 0;
		msg = "Successful purchase:\nPurchase Information\n\n ";
		for (int i = 0; i < products.size(); i++) {
			msg += "\t\4 Product: " + products.get(i).getName() + "-" + products.get(i).getCodeId() + "\tPrice: "
					+ products.get(i).getValue();
			price += products.get(i).getValue();
			msg += "\n";
		}
		msg += "\nTotal to pay:\t$" + price;
		msg += "\nMoney that the user pays:\t" + totalValue;
		msg += "\nUser returns:\t$" + (totalValue - price);
		msg += "\nBuy date: "+dateBuy.getTime()+"\n";
		return msg;
	}
	/**
	 * This method eliminates the subscription of a magazine 
	 * @param magazine
	 * @return Message that the magazine was eliminated
	 */
	public String eleminateMagazine(Bibliographic magazine) {
		String msg = "Se elimino correctamente la revista " + magazine.getName() + " code: " + magazine.getCodeId();
		int i = positionProduct(magazine);
		products.remove(i);
		readPage.remove(i);
		ultimePag.remove(i);
		return msg;
	}
	/**
	 * This Java function returns the position of a bibliographic product in a list
	 * based on its name.
	 * 
	 * @param product The parameter "product" is an object of the class
	 *                "Bibliographic" which is being
	 *                passed as an argument to the method "positionProduct".
	 * @return The method `positionProduct` returns an integer value which
	 *         represents the position of a
	 *         `Bibliographic` product in the `products` list. If the product is not
	 *         found in the list, the method
	 *         returns -1.
	 */
	public int positionProduct(Bibliographic product) {
		int j = 0;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equals(product.getName())) {
				j = i;
			}
		}
		return j;
	}
	/**
	 * This method returns the bibliographic product that is related to the invoice
	 * @return bibliographic product
	 */
	public ArrayList<Bibliographic> getProducts() {
		return products;
	}

/**
 * The function increases or decreases the page number of a given bibliographic product based on the
 * user's input and returns the updated page number.
 * 
 * @param option A character representing the user's choice of action ('S' for advancing to the next
 * page, 'A' for going back to the previous page, or 'B' for finishing reading the book).
 * @param product A Bibliographic object representing the product  being read.
 * @return The method is returning an integer value, which is the number of the page that the user is
 * currently reading.
 */
	public int increasePages(char option, Bibliographic product) {
		int i = positionProduct(product);
		int pag = ultimePag.get(i);//Is to return the number of the page
		if (option == 'S') {
			if(readPage.get(i)<products.get(i).getAmountPag()){//Check if it is less than the total book pages
				readPage.add(i, readPage.get(i)+1 );
				if(readPage.get(i)>ultimePag.get(i)){//Check if it is the biggest page
					ultimePag.add(i, readPage.get(i));//Save the biggest page
				}	
			}	
		} else if(option == 'A' && (readPage.get(i)>0)){//Para devolver a la pagina anterior
			readPage.add(i, readPage.get(i)-1);
		} else if(option == 'B'){
			ultimePag.add(i, readPage.get(i));//Save the present page that is reading with the last read, to finish reading simple	
		}
		pag=readPage.get(i);
		return pag;
	}

	/**
	* This method makes a backup when the disagreement system a product, so the product that the user bought was not deleted from the system	
	* @param oldproduct The bibliographic product that will be eliminated	
	* @param copyProduct Copia de seguridad del producto bibliografico
	*/
	public void setProduct(Bibliographic oldProduct, Bibliographic copyProduct){
		int position=positionProduct(oldProduct);
		products.set(position, copyProduct);
	}
	

}