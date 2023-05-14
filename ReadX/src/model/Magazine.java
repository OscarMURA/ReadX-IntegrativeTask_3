package model;
import java.util.Calendar;
import java.util.jar.Attributes.Name;

/**
 * The Magazine class extends the Bibliographic class and adds properties such as review and type, and
 * methods for getting and setting these properties.
 */
public class Magazine extends Bibliographic {
	private TypeMagazine type;
	private Emission emission;

	/**
	 * this buiilder method of the Magazine class
	 * @param codeId Bibliographic product CodeId
	 * @param name Bibliographic product name
	 * @param amountPag Bibliographic product amount pages
	 * @param datePublication Bibliographic product date publication
	 * @param url Bibliographic product url
	 * @param value Bibliographic product value of the price or subscrition of the product
	 * @param emission The amount of duration in the emission of the magazine
	 * @param type Mazazine type
	 */
	public Magazine(String codeId, String name, int amountPag, Calendar datePublication, String url, double value, Emission emission, TypeMagazine type) {
		super(codeId, name, amountPag, datePublication, url, value);
		this.type=type;
		this.emission=emission;
	}
	/**
	 * 
	 * This method has the functionality of cloning the object of the class
	 * @param product  object to copy
	 */
	public Magazine( Bibliographic product){
		super(product.getCodeId(),product.getName(),product.getAmountPag(),product.getDatePublication(),product.getUrl(),product.getValue());
		this.type=((Magazine) product).getType();
		this.emission=((Magazine) product).getEmission();
	}
	
	/**
	 * This control method save the new TypeMagazine of the Magazine
	 * @param type TypeMagazine
	 */
	public void setType(TypeMagazine type) {
		this.type = type;
	}

	/**
	 * The control method save the new emission type
	 * @param emission
	 */
	public void setEmission(Emission emission) {
		this.emission = emission;
	}
	public Emission getEmission() {
		return emission;
	}
	public TypeMagazine getType() {
		return type;
	}
	
	@Override
	public String getData(){
		String msg="";
		msg+="\n\t\033[47;35m \3 "+name+"\tCode: "+codeId+" \3 \033[0m \n";
		msg+="\n\4Pag: "+amountPag;
		msg+="\n\4Date: "+datePublication.getTime();
		msg+="\n\4Url: "+url;
		msg+="\n\4Type: "+type;
		msg+="\n\4Emission: "+emission+"\n";
		return msg;
	}

}