package model;
import java.util.Calendar;

public class Bill {

	private String valueProduct;
	private Calendar dateBuy;
	private Bibliographic product;

	
	/**
	 * bill Builder method
	 * @param valueProduct product value
	 * @param dateBuy buy date
	 */
	public Bill(String valueProduct, Calendar dateBuy) {
		this.valueProduct = valueProduct;
		this.dateBuy = dateBuy;
	}

	/**
	 * This method for add the product buy
	 * @param product Blibliographic product (Magazine or Book)
	 */
	public void setProduct(Bibliographic product) {
		this.product = product;
	}
	
	/**
	 * This method returns the bibliographic product that is related to the invoice
	 * @return bibliographic product
	 */
	public Bibliographic getProduct() {
		return product;
	}

	

}