package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * The Bibliographic class is an abstract class that contains attributes and
 * methods related to bibliographic products.
 */
public abstract class Bibliographic implements Comparable<Bibliographic> {

	/**
	 * This variable is the code of the product
	 */
	protected String name;
	/**
	 * This variable is the amount of pages of the product
	 */
	protected int amountPag;
	/**
	 * This variable is the date of publication of the product
	 */
	protected Calendar datePublication;
	/**
	 * This variable is the url of the product
	 */
	protected String url;
	/**
	 * This variable is the value of the price or subscrition of the product
	 */
	protected double value;
	/**
	 * Accumulated of Leidas pages
	 */
	private int pageRead;
	/**
	 * It is an arrangement of invoices linked to the object purchasedurchased
	 */
	private ArrayList<Bill> bills;
	protected String codeId;

	/**
	 * this buiilder method of the Bibliographic class
	 * 
	 * @param codeId          Bibliographic product CodeId
	 * @param name            Bibliographic product name
	 * @param amountPag       Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url             Bibliographic product url
	 * @param value           Bibliographic product value of the price or
	 *                        subscrition of the product
	 */
	public Bibliographic(String codeId, String name, int amountPag, Calendar datePublication, String url,
			double value) {
		this.name = name;
		this.codeId = codeId;
		this.amountPag = amountPag;
		this.datePublication = datePublication;
		this.url = url;
		this.value = value;
		bills = new ArrayList<Bill>();
	}

	/**
	 * This control method has the functionality of increasing the pages read, from
	 * the last to the next
	 */
	public void pageRead() {
		this.pageRead++;
	}

	public int getPageRead() {
		return pageRead;
	}

	/**
	 * This control method
	 * 
	 * @return Bibliographic name
	 */
	public String getName() {
		return name;
	}

	public int getAmountPag() {
		return amountPag;
	}

	/**
	 * This method save the new name
	 * 
	 * @param name String: Bibliographic product name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This function returns the date of publication as a Calendar object.
	 * 
	 * @return The method `getDatePublication()` is returning a `Calendar` object.
	 */
	public Calendar getDatePublication() {
		return datePublication;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * This method save the new AmountPage
	 * 
	 * @param AmountPag int: Bibliographic product AmountPage
	 */
	public void setAmountPag(int amountPag) {
		this.amountPag = amountPag;
	}

	/**
	 * This method save the new datePublication
	 * 
	 * @param datePublication Calendar: Bibliographic product datePublication
	 */
	public void setDatePublication(Calendar datePublication) {
		this.datePublication = datePublication;
	}

	/**
	 * This method save the new product or subscription value
	 * 
	 * @param value double: Bibliographic product value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * This control method
	 * 
	 * @return Bibliographic identification value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * This control method
	 * 
	 * @return Bibliographic identification id
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * This control method save the new product url
	 * 
	 * @param Bibliographic product url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * This control method
	 * 
	 * @return Returns the arrangement to add current bill
	 */
	public ArrayList<Bill> getBills() {
		return bills;
	}

	/**
	 * This Medoto adds the current invoices related to this product
	 * 
	 * @param bill Object Bill
	 */
	public void addBill(Bill bill) {
		this.bills.add(bill);
	}

	/**
	 * This method has the function of obtaining the information of the
	 * bibliographic product
	 * 
	 * @return A message with your basic information
	 */
	public abstract String getData();

	@Override
	/**
	 * This function compares the date of publication of two Bibliographic objects
	 * and returns an integer
	 * value indicating their relative order.
	 * 
	 * @param product The parameter "product" is an object of the class
	 *                "Bibliographic" that is being
	 *                compared to the current object.
	 * @return An integer value is being returned, which represents the result of
	 *         the comparison between
	 *         the date of publication of the current Bibliographic product and the
	 *         date of publication of the
	 *         Bibliographic product passed as a parameter. The value returned will
	 *         be 1 if the current product's
	 *         date of publication is after the parameter product's date of
	 *         publication, -1 if it is before, and 0
	 *         if they are the same
	 */
	public int compareTo(Bibliographic product) {
		int i = 0;
		if (this.datePublication.after(product.getDatePublication())) {
			i = 1;// "La fecha 1 es posterior a la fecha 2"
		} else if (this.datePublication.before(product.getDatePublication())) {
			i = -1;// La fecha 1 es anterior a la fecha 2"
		} else {
			i = 0;// la fecha son iguales
		}
		return i;
	}
}