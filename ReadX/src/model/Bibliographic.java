package model;
import java.util.ArrayList;
import java.util.Calendar;
public abstract class Bibliographic {

	private String name;
	private int amountPag;
	private Calendar datePublication;
	private String url;
	private double value;
	private ArrayList<Bill> bills;

	private String codeId;

	/**
	 * this buiilder method of the Bibliographic class
	 * @param codeId Bibliographic product CodeId
	 * @param name Bibliographic product name
	 * @param amountPag Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url Bibliographic product url
	 * @param value Bibliographic product value of the price or subscrition of the product
	 */
	public Bibliographic(String codeId, String name, int amountPag, Calendar datePublication, String url, double value) {
		this.name=name;
		this.codeId=codeId;
		this.amountPag=amountPag;
		this.datePublication=datePublication;
		this.url=url;
		this.value=value;
		bills=new ArrayList<Bill>();
	}
	/**
	 * This control method 
	 * @return Bibliographic name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method save the new name
	 * @param name String: Bibliographic product name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method save the new AmountPage
	 * @param AmountPag int: Bibliographic product AmountPage
	 */
	public void setAmountPag(int amountPag) {
		this.amountPag = amountPag;
	}
	/**
	 * This method save the new datePublication
	 * @param datePublication Calendar: Bibliographic product datePublication
	 */
	public void setDatePublication(Calendar datePublication) {
		this.datePublication = datePublication;
	}
	/**
	 * This method save the new product or subscription value
	 * @param value double: Bibliographic product value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * This control method 
	 * @return Bibliographic identification value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * This control method 
	 * @return Bibliographic identification id
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * This control method save the new product url
	 * @param Bibliographic product url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * This control method
	 * @return Returns the arrangement to add current bill
	 */
	public ArrayList<Bill> getBills() {
		return bills;
	}
	/**
	 * This Medoto adds the current invoices related to this product
	 * @param bill Object Bill
	 */
	public void addBill(Bill bill){
		this.bills.add(bill);
	}


}