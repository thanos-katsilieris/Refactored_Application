package data;

import java.util.ArrayList;

public class Receipt {
	protected int receiptId;
	protected String date;
	protected double sales;
	protected int items;
	protected Company company;
	protected String kind;
	protected ArrayList <String> acceptableKindNames = new ArrayList<String>();
	
	public Receipt(int receiptId, String date, String kind, double sales, int items, String companyName, String country, String city, String street, int number){
		
		initializeAcceptableKindNames();
		
		this.receiptId = receiptId;
		this.date = date;
		this.kind = checkKind(kind);
		this.sales = sales;
		this.items = items;
		company  = new Company(companyName, country, city, street, number);
		
	}
	
	public void initializeAcceptableKindNames() {
		acceptableKindNames.add("Trousers");
		acceptableKindNames.add("Skirts");
		acceptableKindNames.add("Shirts");
		acceptableKindNames.add("Coats");
	}
	
	public String checkKind(String kind) {
		if(acceptableKindNames.contains(kind)) {
			return kind;
		}else {
			return "No specific kind";
		}
	}
	
	public Company getCompany(){
		return company;
	}

	public String getKind() {
		return kind;	
	}

	public double getSales() {
		return sales;
	}

	public int getItems() {
		return this.items;
	}

	public int getReceiptID() {
		return receiptId;
	}

	public String getDate() {
		return date;			
	}

}
