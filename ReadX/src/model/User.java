
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
	protected ArrayList<Bill> bills;

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
	}

	/**
	 * this view method
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
	 * @return Bill ArrayList
	 */
	public ArrayList<Bill> getBills() {
		removeBillWihtProductDelete();
		return bills;
	}
	/**
	 * This method adds the new user's bill
	 * @param bill
	 */
	public void addBill( Bill bill){
		bills.add(bill);
	}
	/**
	 * This method is responsible for eliminating products that are no
	 * longer in force in the market
	 */
	protected void removeBillWihtProductDelete() {
		boolean isFound=false;
		for (int i = 0; i < bills.size()&&!isFound; i++) {
			if (bills.get(i).getProducts() == null) {
				bills.remove(bills.get(i));
				isFound=true;
			}
		}
	}
	/**
	 * This user's method is responsible for eliminating the subscription of a magazine that has in your library
	 * @param magazine El objeto de la revista
	 * @return Elimination magazine subscription message
	 */
	public String eliminateMagazineSuscription(Bibliographic magazine){
		String msg="";
		boolean isFound=false;
		Bill bill=getBill(magazine.getCodeId());
		bill.eleminateMagazine(magazine);
		return msg;
	}
	/**
	 * This method verifies if there is already the bibliographic product in the
	 * user library
	 * 
	 * @param wordKey Bibliographic product name or id
	 * @return Prduct!=null->Exits, product==null-> no exist
	 */
	public Bibliographic alreadyHasProduct(String wordKey){
		Bibliographic product=null;
		Bill bill=getBill(wordKey);
		if(bill!=null){
			product= bill.alreadyHasProduct(wordKey);
		}
		return product;
	}
	/**
	 * This method focuses on searching and returning the Bill object where the bibliographic product has the user
	 * @param wordKey Bibliographic product name or id
	 * @return Bill!=null->Exits, Bill==null-> no exist
	 */
	public Bill getBill(String wordKey){
		Bill bill=null;
		boolean isFound=false;
		for (int i = 0; i < bills.size() && !isFound; i++) {
			if( bills.get(i).alreadyHasProduct(wordKey) != null ){
				bill=bills.get(i);
				isFound=true;
			}	
		}
		return bill;
	}

	/**
	 * This function generates a formatted string displaying the products (magazines and books) in the
	 * bills list.
	 * 
	 * @return The method `showProductUser` returns a String that represents a table of the products
	 * (Magazines and Books) that have been purchased in the bills list. The table has a header with the
	 * titles "Magazine" and "Book", and each product is represented by its codeId. The products are
	 * arranged in sections of 5, and the table is formatted with different colors and styles using escape
	 */
	public String showProductUser(){
		String msg="\033[30m\n";
		int section=0;
		msg+=String.format("|\033[48;5;225m %-10s|\033[0m\033[43m %-8s|\033[0m\n\n|"," Magazine "," Book ");
		for (int i = 0; i < bills.size(); i++) {
			for (int j = 0; j < bills.get(i).getProducts().size(); j++) {
				Bibliographic product= bills.get(i).getProducts().get(j);
				if(product instanceof Magazine){
					msg+=String.format("\033[48;5;225m\033[30m %-6s|\033[0m", product.getCodeId());
				}else{
					msg+=String.format("\033[43m %-6s|\033[0m", product.getCodeId());
				}
				
				section++;
				if(section==5){
					section=0;
					msg+="\n|";
				}
			}
		}
		msg+="\n";
		return msg;
	}
	

}