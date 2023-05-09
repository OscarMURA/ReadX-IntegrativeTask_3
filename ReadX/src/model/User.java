
package model;
import java.util.Calendar;
import java.util.ArrayList;

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
	 * @return 
	 */
	public String eliminateMagazineSuscription(Bibliographic magazine){
		String msg="";
		boolean isFound=false;
		for (int i = 0; i < bills.size()&&!isFound; i++) {
			ArrayList<Bibliographic> product=bills.get(i).getProducts();
			for (int j = 0; j < bills.get(i).getProducts().size()&&!isFound; j++) {
				if(product.get(i).getName().equals(magazine.getName())){
					msg=bills.get(i).eleminateMagazine(magazine);
				}
			}
		}
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
		boolean isFound=false;
		for (int i = 0; i < bills.size() && !isFound; i++) {
			if(bills.get(i).alreadyHasProduct(wordKey)!=null){
				product=bills.get(i).alreadyHasProduct(wordKey);
				isFound=true;
			}	
		}
		return product;
	}

	public String romoveMagazineSubscrition(String wordKey) {
		String msg = "";
		boolean isFound=false;
		
		return msg;
	}

	

}