package model;
import java.util.Calendar;
import java.util.ArrayList;

public class Bill {

	private double totalValue;
	private Calendar dateBuy;
	private int readPag;
	private int currentPag;
	private ArrayList<Bibliographic> products;
	

	
	/**
	 * bill Builder method
	 * @param totalValue products value
	 * @param dateBuy buy date
	 * @param products all products buy for user
	 */
	public Bill(double totalValue, Calendar dateBuy, ArrayList<Bibliographic> products) {
		this.totalValue = totalValue;
		this.dateBuy = dateBuy;
		this.products=products;
		readPag=0;
		currentPag=0;
	}
	/**
	 * This method verifies if there is already the bibliographic product in the
	 * user library
	 * 
	 * @param wordKey Bibliographic product name or id
	 * @return true=exist or false: no exist
	 */
	public Bibliographic alreadyHasProduct(String wordKey){
		Bibliographic product=null;
		boolean isFound=false;
		for (int i = 0; i < products.size()&&!isFound; i++) {
			if(products.get(i).getName().equalsIgnoreCase(wordKey)||products.get(i).getCodeId().equalsIgnoreCase(wordKey)){
				product=products.get(i);
				isFound=true;
			}
		}
		return product;
	}

	/**
	 * This method returns a digital message from all bought books
	 */
	@Override
	public String toString(){
		String msg="";
		double price=0;
		msg="Successful purchase:\nPurchase Information\n\n ";
		for (int i = 0; i < products.size(); i++) {
			msg+="\t\4 Product: "+products.get(i).getName()+"-"+products.get(i).getCodeId()+"\tPrice: "+products.get(i).getValue();
			price+=products.get(i).getValue();
			msg+="\n";
		}
		msg+="Total to pay:\t$"+price;
		msg+="\nMoney that the user pays:\t"+totalValue;
		msg+="User returns:\t$"+(totalValue-price);
		return msg;
	}

	/**
	 * This method eliminates the subscription of a magazine
	 * @param Magazine
	 * @return Message that the magazine was eliminated
	 */
	public String eleminateMagazine(Bibliographic magazine){
		String msg="Se elimino correctamente la revista "+magazine.getName()+" code: "+magazine.getCodeId();
		products.remove(magazine);
		return msg;
	} 


	
	/**
	 * This method returns the bibliographic product that is related to the invoice
	 * @return bibliographic product
	 */
	public ArrayList<Bibliographic> getProducts() {
		return products;
	}

	public void setReadPag() {
		this.readPag = this.readPag+1;
	}

	public int getReadPag() {
		return readPag;
	}


	

}