package ui;

import model.Controller;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;
import model.Bibliographic;


import ui.Tools;

public class Main {

	private static Controller controller;
	private static Scanner reader;
	private static SimpleDateFormat format;
	private static Tools tools;

	/**
	 * method builder of the main Class
	 */
	public Main() {
		controller = new Controller();
		reader = new Scanner(System.in);
		format = new SimpleDateFormat("dd-MM-yyyy");
		tools=new Tools();
	}

	public static void main(String[] args) {

		Main main=new Main();

		main.registerBibliographicProduct();
		main.deleteBibliographicProduct();
		main.deleteBibliographicProduct();

	}


	
	public void menu() {
		// TODO - implement Main.menu
		throw new UnsupportedOperationException();
	}

	public void optionExecution() {
		// TODO - implement Main.optionExecution
		throw new UnsupportedOperationException();
	}
	/**
	 * println Method
	 * @param println Object for print with line jump
	 */
	public  void println(Object println) {
		System.out.println(println);
	}
	/**
	 * print Method
	 * @param print Object for print without line jump
	 */
	public void print(Object print) {
		System.out.print(print);
	}
	/**
	 * Decoration method to separate functionalities
	 */
	public void registerUser() {
		String name="", id="",msg="";
		boolean repeatId=false;
		int option=0;
		tools.lines();
		tools.title("Register User");
		println("What will you user type register?\n\t1. Regular\n\t2. Premium\n");

		do {
			print("\tConrrently type: ");
			option=tools.validateInt();
		} while ((option!=1&&option!=2));

		print("\n\tType name: ");
		name=tools.read(reader);
		do {
			print("\n\tType id exclusive: ");
			id=tools.read(reader);
			if((controller.searchUser(id)!=null)){
				repeatId=true;
			}

		} while (repeatId);
		msg=controller.registerUser(name, id, option);
		println(msg);
	}

	/**
	 * 
	 */
	public void registerBibliographicProduct() {
		int option=0,amountPag=0,type=0, emission=0;
		String product="",productName="",url="", msg="",review="";
		double  productValue=0;
		Calendar datePublication=null;
		Bibliographic bibliographic=null;

		tools.lines();
		tools.title("Register blibliographic products");
		println("Choose the type Product:\n\t1.Book\n\t2.Magazine\n");
		do {
			print("\t\4Correctly Type: ");
			option=tools.validateInt();
		} while ((option!=1&&option!=2));

		if(option==1){
			product=" Book ";
		}else{
			product=" Magazine ";
		}
		productName=assignName(product);
		amountPag=assignAmountPag(product);
		url=assignUrl();
		datePublication=assignDatePublication(product);
		productValue=assignProductValue(product);
		type=assignProductType(product, option);

		if(option==1){
			print("Type book review: ");
			review=tools.read(reader);
		}else{
			emission=assignEmission(product);
		}
		msg= controller.registerBibliographicProduct(option, productName, amountPag, datePublication, url, productValue, emission, type, review);
		println("\n\n"+msg);
	}

	/**
	 * This view  method assign the name of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product name
	 */
	public String assignName(String product){
		String productName="";
		boolean repeat=false;
		do {
			print("\n\4Exclusive! Type "+product+" name: ");
			productName=tools.read(reader);
			
			if((controller.searchBibliographic(productName)!=null)){
				repeat=true;
			}
		} while (repeat);
		return productName;
	}

	/**
	 * This view  method assign the amount pag of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product Amount page
	 */
	public int assignAmountPag(String product){
		int amountPag=0;
		do {
			print("\4Type "+product+" amount pages: ");
			amountPag=tools.validateInt();
		} while (amountPag<=0||amountPag==Integer.MAX_VALUE);
		return amountPag;
	}
	/**
	 * This view  method assign the date publication of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product date publication
	 */
	public Calendar assignDatePublication(String product){
		Calendar datePublication=Calendar.getInstance();
		boolean isBefore=false;
		do {
			Calendar now=Calendar.getInstance();
			datePublication=assingDate();
			isBefore=datePublication.before(now);
			if(!isBefore){
				println("\tYou must enter the date, at the moment you cannot place products without premiere");
			}
		} while (!isBefore);
		return datePublication;
	}
	/**
	 * This view  method assign the value of the product
	 * @param product Type product ("Book" or "Magazine")
	 * @return product value
	 */
	public double assignProductValue(String product){
		double productValue=0;
		do {
			print("\4Write the product value or subscription: ");
			productValue=tools.validateDouble();
		} while (productValue<0||productValue==Integer.MAX_VALUE);
		return productValue;
	}
	/**
	 * This view  method assign the each product type
	 * @param product "Book" or "Magazine"
	 * @param option 1.Book or 2.Magazine
	 * @return product type such as option number
	 */
	public int assignProductType(String product, int option){
		int type=0;
		println("Choose "+product+"  type\n");
		switch(option){
			case 1->{
				println("\t1.Ciencie fiction\n\t2.Fantasy\n\t3.Historical Novel");
			}
			case 2->{
				println("\t1.Varieties\n\t2.Desing\n\t3.Cientist");
			}
		}
		do {
			print("\n\t\4Correctly type: ");
			type=tools.validateInt();
		} while (type<=0||type>=4);
		return type;
	}
	/**
	 * This view  method assign the emission of the Magazine
	 * @param product Type product ("Book" or "Magazine")
	 * @return emission Type of the Magazine
	 */
	public int assignEmission(String product){
		int emission=0;
		println("the magazine Each amount month will have emssion ");
			println("\n\t1.Monthly\n\t2.Quarterly\n\t3.Biannual\n\t4.Annual\n");
			do {
				print("\4Correctly type: ");
				emission=tools.validateInt();
			} while (emission<=0||emission>=5);
		return emission;
	}
	public String assignUrl(){
		String url="";
		Random random=new Random();
		String[] typeIma={"jpeg","png","jpng","psd"}; 
		print("\4Type ulr exclusive: ");
		url=reader.next();
		url=url.toUpperCase()+"."+typeIma[random.nextInt(typeIma.length)];
		return url;
	}
	/**
	 * Prompts the user to enter a date in the format "dd/MM/yyyy",
	 * and tools.validates the input to ensure that it is a valid date. Returns
	 * a Calendar object representing the date entered by the user.
	 * @return A Calendar object representing the date entered by the user.
	 */
	public Calendar assingDate() {
		Calendar dateCal = Calendar.getInstance();
		boolean follow;
		println("\n\4Type date publication: dd-MM-yyyy ->Exa: 22-02-2023");

		do {
			follow = false;
			String date = "";
			print("\t\4Enter date: ");
			date = reader.next();
			try {
				dateCal.setTime(format.parse(date));
				follow = true;
			} catch (ParseException e) {
				println("\n\t¡Please enter date correctly!");
			}
		} while (!follow);
		return dateCal;
	}
	/**
	 * This view method is for Modify bibliographic products
	 */
	public void modifiedBibliographicProduct() {
		String wordKey="";//bibliographicProduct name or identification id
		int typeProduct=0,option=0,amountPag=0,type=0, emission=0;
		String product="",url="", msg="", review="",productName="";
		double  productValue=0;
		Calendar datePublication=null;
		tools.lines();
		tools.title("Modified Bibliographic Product");
		print("Search bibliographic product,\nType name or indetification code: ");
		wordKey=tools.read(reader);
		if(controller.searchBibliographic(wordKey)!=null){
			typeProduct=controller.decideIntanceOfBibliographic(wordKey);
			product=(typeProduct==1)?"Book":"Magazine";

			do {
				println("\nChoose "+wordKey+" "+product+" for change: ");
				println("\n\t\4 1.Name\n\t\4 2.Amount Page\n\t\4 3.Date publication");
				println("\t\4 4."+product+" value \n\t\4 5.Type "+product+"\n\t\4 6.Url");
				if(typeProduct==2){
					println("\t\4 7.Magazine Emission");
				}else{
					println("\t\4 7.Book review");
				}
				print("Type option (exit=0): ");
				option=tools.validateInt();
				switch(option){
					case 0->println("Exit");
					case 1->productName=assignName(product);
					case 2->amountPag=assignAmountPag(product);
					case 3->datePublication=assignDatePublication(product);
					case 4->productValue=assignProductValue(product);
					case 5->type=assignProductType(product, typeProduct);
					case 6->url=assignUrl();
					case 7->{
						if(typeProduct==2){
							emission=assignEmission(product);
						}else{
							print("\n\t\4Type book review: ");
							review=tools.read(reader);
						}
					}
					default->println("Option invalid");
				}
			} while (option!=0);	
		msg=controller.modifiedProductBibliographic(wordKey, productName, amountPag, datePublication, url, productValue,emission, type);
		}else{
			msg="The "+wordKey+" product no exist";
		}
		println("\n\n"+msg);	
	}
	/**
	 * This view method is for delete bibliographic products
	 */
	public void deleteBibliographicProduct() {
		String wordKey="", msg="";
		tools.lines();
		tools.title("Delete BibliographicProduct");
		print("Search bibliographic product,\nType name or indetification code: ");
		wordKey=tools.read(reader);
		if(controller.searchBibliographic(wordKey)!=null){
			msg=controller.deleteProduct(wordKey);
		}else{
			msg="The "+wordKey+" product no exist";
		}
		print(msg);
	}

	public void textUnit(){

	}

	public void BuyBibliographicProduct() {

	}

	public void read() {

	}


}