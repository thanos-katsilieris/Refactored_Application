package data;


import java.util.ArrayList;

import output.ReceiptUpdater;
import output.ReceiptUpdaterHTML;
import output.ReceiptUpdaterTXT;
import output.ReceiptUpdaterXML;


public class Salesman {
	private String name;
	private String afm;
	private ArrayList <Receipt> allReceipts;
	private ReceiptUpdater receiptUpdater;
	
	
	public Salesman(){
		allReceipts = new ArrayList<Receipt>();
	}
	
	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			receiptUpdater = new ReceiptUpdaterTXT();
		}	
		if(fileType.equals("XML")){
			receiptUpdater = new ReceiptUpdaterXML();
		}else{
			receiptUpdater = new ReceiptUpdaterHTML();
		}
	}
	public ArrayList<Receipt> getReceipts(){
		return allReceipts;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAfm() {
		return afm;
	}
	public void setAfm(String afm) {
		this.afm = afm;
	}

	
	public double calculateSalesData(String salesDataName) {
		double sum = 0;
		if(salesDataName == "Sales") {
			for(int i = 0; i< allReceipts.size(); i++){
				sum += allReceipts.get(i).getSales();
			}
		}else if(salesDataName == "Items") {
			for(int i = 0; i< allReceipts.size(); i++){
				sum += allReceipts.get(i).getItems();
			}
		}
		else {
			for (int i = 0; i< allReceipts.size(); i++){
				if(allReceipts.get(i).getKind().equals(salesDataName)){
					sum += allReceipts.get(i).getItems();
				}
			}
		}
		return sum;
	}

	public double calculateCommission(){
		double commission = 0;
		if( this.calculateSalesData("Sales") > 6000 && this.calculateSalesData("Sales")<= 10000){
			commission = 0.1*(calculateSalesData("Sales")-6000);
		}
		else if(this.calculateSalesData("Sales") > 10000 && this.calculateSalesData("Sales") <= 40000 ){
			commission = (((calculateSalesData("Sales") - 10000) * 0.15) + (10000*0.1));			
		}
		else if(this.calculateSalesData("Sales") > 40000 ) {
			commission = 10000*0.1 + 30000*0.15 + (calculateSalesData("Sales") - 40000)*0.2;			
		}
		return commission;
	}


	public ReceiptUpdater getReceiptUpdater() {
		return receiptUpdater;
	}


}
